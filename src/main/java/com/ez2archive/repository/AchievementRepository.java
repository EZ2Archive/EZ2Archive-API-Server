package com.ez2archive.repository;

import com.ez2archive.entity.KeyType;
import com.ez2archive.dto.achieve.AchieveDTO;
import com.ez2archive.dto.achieve.OverallDTO;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@SuppressWarnings({ "StringBufferMayBeStringBuilder", "StringBufferReplaceableByString" })
@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AchievementRepository
{
  private final EntityManager em;
  private final JpaResultMapper resultMapper;

  @SuppressWarnings("SqlDialectInspection")
  public List<AchieveDTO> findAchieveListByUserIdWithKeyTypeWithLevel(String userId, KeyType keyType, int level)
  {
    StringBuffer nativeQuery = new StringBuffer();
    nativeQuery.append("SELECT COALESCE(rd.RECORD_DETAIL_ID, -1) ");
    nativeQuery.append(",      mi.MUSIC_INFO_ID ");
    nativeQuery.append(",      mi.NAME ");
    nativeQuery.append(",      COALESCE(rd.SCORE, -1) ");
    nativeQuery.append(",      -1  as avgScore ");
    nativeQuery.append(",      COALESCE(rd.point, -1) ");
    nativeQuery.append(",      -1  as avgPoint ");
    nativeQuery.append(",      COALESCE(rd.PERCENTAGE, -1) ");
    nativeQuery.append(",      mi.RANKS ");
    nativeQuery.append(",      mi.DIFFICULTY ");
    nativeQuery.append(",      COALESCE(rd.GRADE, '') ");
    nativeQuery.append(",      COALESCE(rd.IS_ALL_COOL, FALSE) ");
    nativeQuery.append(",      COALESCE(rd.IS_NO_MISS, FALSE) ");
    //nativeQuery.append(",      (SELECT f.FILEUUID FROM FILE f WHERE mi.FILE_ID = f.FILE_ID) ");
    nativeQuery.append("FROM MUSIC_INFO mi ");
    nativeQuery.append("  LEFT JOIN ( ");
    nativeQuery.append("    SELECT tr2.RECORD_DETAIL_ID, tr2.MUSIC_INFO_ID, tr2.MEMBER_ID, tr2.ADD_TIME ");
    nativeQuery.append("    FROM ( ");
    nativeQuery.append("      SELECT MUSIC_INFO_ID, MEMBER_ID, MAX(ADD_TIME) as ADD_TIME ");
    nativeQuery.append("      FROM RECORD tr2 ");
    nativeQuery.append("      GROUP BY MUSIC_INFO_ID, MEMBER_ID ");
    nativeQuery.append("      HAVING MEMBER_ID = ( ");
    nativeQuery.append("        SELECT MEMBER_ID ");
    nativeQuery.append("        FROM MEMBER ");
    nativeQuery.append("        WHERE USER_ID = :userId ");
    nativeQuery.append("      ) ");
    nativeQuery.append("    ) tr ");
    nativeQuery.append("    LEFT JOIN RECORD tr2 ON tr2.MUSIC_INFO_ID = tr.MUSIC_INFO_ID AND tr2.ADD_TIME = tr.ADD_TIME AND tr2.MEMBER_ID = tr.MEMBER_ID ");
    nativeQuery.append("  ) r ON mi.MUSIC_INFO_ID = r.MUSIC_INFO_ID   ");
    nativeQuery.append("  LEFT JOIN RECORD_DETAIL rd ON rd.RECORD_DETAIL_ID = r.RECORD_DETAIL_ID ");
    nativeQuery.append("WHERE mi.KEY_TYPE = :keyType ");
    nativeQuery.append("AND   mi.LEVEL = :level ");
    nativeQuery.append("ORDER BY mi.RANKS DESC, mi.NAME ");
    
    return resultMapper.list(em.createNativeQuery(nativeQuery.toString())
        .setParameter("userId", userId)
        .setParameter("keyType", keyType.name())
        .setParameter("level", level)
      , AchieveDTO.class
    );
  }
  
  public OverallDTO findOverallByUserIdWithKeyTypeWithLevel(String userId, KeyType keyType, int level)
  {
    Optional<OverallDTO> findOverallVO;

    StringBuffer nativeQuery = new StringBuffer();
    nativeQuery.append("SELECT AVG(rd.PERCENTAGE) rateAvg ");
    nativeQuery.append(",      0  ");
    nativeQuery.append(",      SUM(CASE WHEN rd.IS_ALL_COOL = TRUE THEN 1 ELSE 0 END) allCoolCnt  ");
    nativeQuery.append(",      SUM(CASE WHEN rd.IS_NO_MISS  = TRUE THEN 1 ELSE 0 END) noMissCnt  ");
    nativeQuery.append(",      SUM(CASE WHEN rd.GRADE       = 'SPPP' THEN 1 ELSE 0 END) spppCnt  ");
    nativeQuery.append(",      SUM(CASE WHEN rd.GRADE       = 'SPP' THEN 1 ELSE 0 END) sppCnt  ");
    nativeQuery.append(",      SUM(CASE WHEN rd.GRADE       = 'SP' THEN 1 ELSE 0 END) spCnt  ");
    nativeQuery.append("FROM MUSIC_INFO mi    ");
    nativeQuery.append("  JOIN (  ");
    nativeQuery.append("    SELECT tr2.RECORD_DETAIL_ID, tr2.MUSIC_INFO_ID, tr2.MEMBER_ID, tr2.ADD_TIME ");
    nativeQuery.append("    FROM ( ");
    nativeQuery.append("      SELECT MUSIC_INFO_ID, MEMBER_ID, MAX(ADD_TIME) as ADD_TIME ");
    nativeQuery.append("      FROM RECORD tr2 ");
    nativeQuery.append("      GROUP BY MUSIC_INFO_ID, MEMBER_ID ");
    nativeQuery.append("      HAVING MEMBER_ID = ( ");
    nativeQuery.append("        SELECT MEMBER_ID ");
    nativeQuery.append("        FROM MEMBER ");
    nativeQuery.append("        WHERE USER_ID = :userId ");
    nativeQuery.append("      ) ");
    nativeQuery.append("    ) tr ");
    nativeQuery.append("    LEFT JOIN RECORD tr2 ON tr2.MUSIC_INFO_ID = tr.MUSIC_INFO_ID AND tr2.ADD_TIME = tr.ADD_TIME AND tr2.MEMBER_ID = tr.MEMBER_ID ");
    nativeQuery.append("  ) r ON mi.MUSIC_INFO_ID = r.MUSIC_INFO_ID    ");
    nativeQuery.append("  JOIN RECORD_DETAIL rd ON rd.RECORD_DETAIL_ID = r.RECORD_DETAIL_ID  ");
    nativeQuery.append("WHERE mi.KEY_TYPE = :keyType  ");
    nativeQuery.append("AND   mi.LEVEL = :level  ");
    nativeQuery.append("GROUP BY r.MEMBER_ID");

    try
    {
      findOverallVO = Optional.of(
        resultMapper.uniqueResult(
          em.createNativeQuery(nativeQuery.toString())
            .setParameter("userId", userId)
            .setParameter("keyType", keyType.name())
            .setParameter("level", level)
          , OverallDTO.class
        )
      );
    }
    catch( NoResultException e )
    {
      findOverallVO = Optional.of(new OverallDTO());
    }

    return findOverallVO.get();
  }
}
