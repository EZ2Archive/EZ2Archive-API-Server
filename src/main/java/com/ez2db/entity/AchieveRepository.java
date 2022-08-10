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
//    String query = "SELECT new com.ez2db.entity.AchieveVO(r.id, mi.name, r.score, mi.level, mi.rank, r.grade, concat(mi.imageFile.filePath, mi.imageFile.fileOriginName)) " +
//      "FROM Member m JOIN m.recordList r JOIN r.music mi " +
//      "WHERE mi.keyType = :keyType " +
//      "AND   mi.level   = :level " +
//      "AND   m.userId   = :userId ";

//    String query = "" +
//      "select new com.ez2db.entity.AchieveVO(" +
//      "         (select coalesce(r.id, '')  FROM Record r WHERE r.music.id = mi.id AND r.member.userId = :userId ) " +
//      ",        mi.id " +
//      ",        mi.name " +
//      ",        (select r.score             FROM Record r WHERE r.music.id = mi.id AND r.member.userId = :userId ) " +
//      ",        mi.rank " +
//      ",        (select concat(r.grade, '') FROM Record r WHERE r.music.id = mi.id AND r.member.userId = :userId ) " +
//      ",        (select r.isAllCool         FROM Record r WHERE r.music.id = mi.id AND r.member.userId = :userId ) " +
//      ",        (select r.isNoMiss          FROM Record r WHERE r.music.id = mi.id AND r.member.userId = :userId ) " +
//      ",        concat(mi.imageFile.filePath, mi.imageFile.fileOriginName) " +
//      ") " +
//      "FROM MusicInfo mi " +
//      "WHERE mi.keyType = :keyType " +
//      "AND mi.level = :level ";

//    return em.createQuery(query, AchieveVO.class)
//      .setParameter("userId", userId)
//      .setParameter("keyType", keyType)
//      .setParameter("level", level)
//      .getResultList();

    String query = "SELECT NVL(r.RECORD_ID, -1) " +
      " ,      mi.MUSIC_INFO_ID" +
      " ,      mi.NAME" +
      " ,      NVL(r.SCORE, -1)" +
      " ,      mi.RANK" +
      " ,      NVL(r.GRADE, '')" +
      " ,      NVL(r.IS_ALL_COOL, FALSE)" +
      " ,      NVL(r.IS_NO_MISS, FALSE)" +
      " ,      (SELECT f.FILE_ORIGIN_NAME FROM FILE f WHERE mi.FILE_ID = f.FILE_ID) " +
      "FROM MUSIC_INFO mi " +
      "    LEFT JOIN ( " +
      "        SELECT * " +
      "        FROM RECORD " +
      "        WHERE MEMBER_ID = ( " +
      "            SELECT MEMBER_ID " +
      "            FROM MEMBER " +
      "            WHERE USER_ID = :userId " +
      "        ) " +
      "    ) r ON mi.MUSIC_INFO_ID = r.MUSIC_INFO_ID " +
      "WHERE mi.KEY_TYPE = :keyType " +
      "AND   mi.LEVEL = :level";

    return resultMapper.list(em.createNativeQuery(query)
        .setParameter("userId", userId)
        .setParameter("keyType", keyType.name())
        .setParameter("level", level)
      , AchieveVO.class
    );
  }
}
