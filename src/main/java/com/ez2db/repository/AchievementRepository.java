package com.ez2db.repository;

import com.ez2db.vo.AchieveVO;
import com.ez2db.entity.KeyType;
import com.ez2db.vo.OverallVO;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AchievementRepository
{
  private final EntityManager em;
  private final JpaResultMapper resultMapper;

  @SuppressWarnings("SqlDialectInspection")
  public List<AchieveVO> findAchieveListByUserIdWithKeyTypeWithLevel(String userId, KeyType keyType, int level)
  {
//    String query = "SELECT new com.ez2db.vo.AchieveVO(r.id, mi.name, r.score, mi.level, mi.rank, r.grade, concat(mi.imageFile.filePath, mi.imageFile.fileOriginName))   " +
//      "FROM Member m JOIN m.recordList r JOIN r.music mi   " +
//      "WHERE mi.keyType = :keyType   " +
//      "AND   mi.level   = :level   " +
//      "AND   m.userId   = :userId ";

//    String query = "  " +
//      "select new com.ez2db.vo.AchieveVO(  " +
//      "         (select coalesce(r.id, '')  FROM Record r WHERE r.music.id = mi.id AND r.member.userId = :userId )   " +
//      ",        mi.id   " +
//      ",        mi.name   " +
//      ",        (select r.score             FROM Record r WHERE r.music.id = mi.id AND r.member.userId = :userId )   " +
//      ",        mi.rank   " +
//      ",        (select concat(r.grade, '') FROM Record r WHERE r.music.id = mi.id AND r.member.userId = :userId )   " +
//      ",        (select r.isAllCool         FROM Record r WHERE r.music.id = mi.id AND r.member.userId = :userId )   " +
//      ",        (select r.isNoMiss          FROM Record r WHERE r.music.id = mi.id AND r.member.userId = :userId )   " +
//      ",        concat(mi.imageFile.filePath, mi.imageFile.fileOriginName)   " +
//      ")   " +
//      "FROM MusicInfo mi   " +
//      "WHERE mi.keyType = :keyType   " +
//      "AND mi.level = :level ";

//    return em.createQuery(query, AchieveVO.class)
//      .setParameter("userId", userId)
//      .setParameter("keyType", keyType)
//      .setParameter("level", level)
//      .getResultList();
    final String nativeQuery = " " +
      "SELECT NVL(rd.RECORD_DETAIL_ID, -1) " +
      ",      mi.MUSIC_INFO_ID " +
      ",      mi.NAME " +
      ",      NVL(rd.SCORE, -1) " +
      ",      NVL(rd.PERCENTAGE, -1) " +
      ",      mi.RANK " +
      ",      mi.DIFFICULTY " +
      ",      NVL(rd.GRADE, '') " +
      ",      NVL(rd.IS_ALL_COOL, FALSE) " +
      ",      NVL(rd.IS_NO_MISS, FALSE) " +
      ",      (SELECT f.FILE_ORIGIN_NAME FROM FILE f WHERE mi.FILE_ID = f.FILE_ID) " +
      "FROM MUSIC_INFO mi " +
      "  LEFT JOIN ( " +
      "    SELECT tr2.RECORD_DETAIL_ID, tr2.MUSIC_INFO_ID, tr2.MEMBER_ID, tr2.ADD_TIME " +
      "    FROM ( " +
      "      SELECT MUSIC_INFO_ID, MEMBER_ID, MAX(ADD_TIME) as ADD_TIME " +
      "      FROM RECORD tr2 " +
      "      GROUP BY MUSIC_INFO_ID, MEMBER_ID " +
      "      HAVING MEMBER_ID = ( " +
      "        SELECT MEMBER_ID " +
      "        FROM MEMBER " +
      "        WHERE USER_ID = :userId " +
      "      ) " +
      "    ) tr " +
      "    LEFT JOIN RECORD tr2 ON tr2.MUSIC_INFO_ID = tr.MUSIC_INFO_ID AND tr2.ADD_TIME = tr.ADD_TIME AND tr2.MEMBER_ID = tr.MEMBER_ID " +
      "  ) r ON mi.MUSIC_INFO_ID = r.MUSIC_INFO_ID   " +
      "  LEFT JOIN RECORD_DETAIL rd ON rd.RECORD_DETAIL_ID = r.RECORD_DETAIL_ID " +
      "WHERE mi.KEY_TYPE = :keyType " +
      "AND   mi.LEVEL = :level " +
      "ORDER BY mi.RANK DESC, mi.NAME ";
    
    return resultMapper.list(em.createNativeQuery(nativeQuery)
        .setParameter("userId", userId)
        .setParameter("keyType", keyType.name())
        .setParameter("level", level)
      , AchieveVO.class
    );
  }
  
  public OverallVO findOverallByUserIdWithKeyTypeWithLevel(String userId, KeyType keyType, int level)
  {
    Optional<OverallVO> findOverallVO;

    String nativeQuery = " " +
      "SELECT AVG(rd.PERCENTAGE) rateAvg  " +
      ",      0  " +
      ",      SUM(CASE WHEN rd.IS_ALL_COOL = TRUE THEN 1 ELSE 0 END) allCoolCnt  " +
      ",      SUM(CASE WHEN rd.IS_NO_MISS  = TRUE THEN 1 ELSE 0 END) noMissCnt  " +
      ",      SUM(CASE WHEN rd.GRADE       = 'SPPP' THEN 1 ELSE 0 END) spppCnt  " +
      ",      SUM(CASE WHEN rd.GRADE       = 'SPP' THEN 1 ELSE 0 END) sppCnt  " +
      ",      SUM(CASE WHEN rd.GRADE       = 'SP' THEN 1 ELSE 0 END) spCnt  " +
      "FROM MUSIC_INFO mi    " +
      "  JOIN (  " +
      "      SELECT MUSIC_INFO_ID, MEMBER_ID, RECORD_DETAIL_ID, MAX(ADD_TIME)  " +
      "      FROM RECORD  " +
      "      GROUP BY MUSIC_INFO_ID, MEMBER_ID, RECORD_DETAIL_ID  " +
      "      HAVING MEMBER_ID = (    " +
      "          SELECT MEMBER_ID    " +
      "          FROM MEMBER    " +
      "          WHERE USER_ID = :userId  " +
      "      )    " +
      "  ) r ON mi.MUSIC_INFO_ID = r.MUSIC_INFO_ID    " +
      "  JOIN RECORD_DETAIL rd ON rd.RECORD_DETAIL_ID = r.RECORD_DETAIL_ID  " +
      "WHERE mi.KEY_TYPE = :keyType  " +
      "AND   mi.LEVEL = :level  " +
      "GROUP BY r.MEMBER_ID";

    try
    {
      findOverallVO = Optional.of(
        resultMapper.uniqueResult(
          em.createNativeQuery(nativeQuery)
            .setParameter("userId", userId)
            .setParameter("keyType", keyType.name())
            .setParameter("level", level)
          , OverallVO.class
        )
      );
    }
    catch( NoResultException e )
    {
      findOverallVO = Optional.of(new OverallVO());
    }

    return findOverallVO.get();
  }
}
