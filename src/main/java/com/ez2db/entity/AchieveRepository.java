package com.ez2db.entity;

import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AchieveRepository
{
  private final EntityManager em;
  private final JpaResultMapper resultMapper;

  public List<AchieveVO> findByUserIdWithKeyTypeWithLevel(String userId, KeyType keyType, int level)
  {
//    String query = "SELECT new com.ez2db.entity.AchieveVO(r.id, mi.name, r.score, mi.level, mi.rank, r.grade, concat(mi.imageFile.filePath, mi.imageFile.fileOriginName))  " +
//      "FROM Member m JOIN m.recordList r JOIN r.music mi  " +
//      "WHERE mi.keyType = :keyType  " +
//      "AND   mi.level   = :level  " +
//      "AND   m.userId   = :userId ";

//    String query = " " +
//      "select new com.ez2db.entity.AchieveVO( " +
//      "         (select coalesce(r.id, '')  FROM Record r WHERE r.music.id = mi.id AND r.member.userId = :userId )  " +
//      ",        mi.id  " +
//      ",        mi.name  " +
//      ",        (select r.score             FROM Record r WHERE r.music.id = mi.id AND r.member.userId = :userId )  " +
//      ",        mi.rank  " +
//      ",        (select concat(r.grade, '') FROM Record r WHERE r.music.id = mi.id AND r.member.userId = :userId )  " +
//      ",        (select r.isAllCool         FROM Record r WHERE r.music.id = mi.id AND r.member.userId = :userId )  " +
//      ",        (select r.isNoMiss          FROM Record r WHERE r.music.id = mi.id AND r.member.userId = :userId )  " +
//      ",        concat(mi.imageFile.filePath, mi.imageFile.fileOriginName)  " +
//      ")  " +
//      "FROM MusicInfo mi  " +
//      "WHERE mi.keyType = :keyType  " +
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
      ",      mi.RANK " +
      ",      mi.DIFFICULTY " +
      ",      NVL(rd.GRADE, '') " +
      ",      NVL(rd.IS_ALL_COOL, FALSE) " +
      ",      NVL(rd.IS_NO_MISS, FALSE) " +
      ",      (SELECT f.FILE_ORIGIN_NAME FROM FILE f WHERE mi.FILE_ID = f.FILE_ID) " +
      "FROM MUSIC_INFO mi " +
      "  LEFT JOIN ( " +
      "      SELECT MUSIC_INFO_ID, MEMBER_ID, RECORD_DETAIL_ID, MAX(ADD_TIME) " +
      "      FROM RECORD " +
      "      GROUP BY MUSIC_INFO_ID, MEMBER_ID, RECORD_DETAIL_ID " +
      "      HAVING MEMBER_ID = ( " +
      "          SELECT MEMBER_ID " +
      "          FROM MEMBER " +
      "          WHERE USER_ID = :userId " +
      "      ) " +
      "  ) r ON mi.MUSIC_INFO_ID = r.MUSIC_INFO_ID " +
      "  LEFT JOIN RECORD_DETAIL rd ON rd.RECORD_DETAIL_ID = r.RECORD_DETAIL_ID " +
      "WHERE mi.KEY_TYPE = :keyType " +
      "AND   mi.LEVEL = :level " +
      "ORDER BY mi.RANK DESC, mi.NAME";
    
    return resultMapper.list(em.createNativeQuery(nativeQuery)
        .setParameter("userId", userId)
        .setParameter("keyType", keyType.name())
        .setParameter("level", level)
      , AchieveVO.class
    );
  }
}
