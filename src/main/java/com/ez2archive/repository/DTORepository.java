package com.ez2archive.repository;

import com.ez2archive.dto.tier.RecordDetailDTO;
import com.ez2archive.dto.tier.TierAverageDTO;
import com.ez2archive.entity.KeyType;
import com.ez2archive.entity.Member;
import com.ez2archive.entity.TierGrade;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings({ "StringBufferReplaceableByString", "StringBufferMayBeStringBuilder" })
@Repository
@RequiredArgsConstructor
public class DTORepository
{
  private final EntityManager em;

  private final JpaResultMapper resultMapper;

  public List<TierAverageDTO> findAvgRecordByMusicInTierGradeAndKeyType(List<TierGrade> tierGradeList, KeyType keyType, int level)
  {
//    StringBuffer query = new StringBuffer();
//    query.append("SELECT new com.ez2archive.dto.tier.TierAverageDTO(mi.id, mi.name, avg(rd.score), avg(rd.point)) ");
//    query.append("FROM Record r ");
//    query.append("  JOIN r.music mi ");
//    query.append("  JOIN r.member m ");
//    query.append("  JOIN r.recordDetail rd ");
//    query.append("WHERE m IN (SELECT m ");
//    query.append("  FROM Tier t ");
//    query.append("    JOIN t.member m ");
//    query.append("  WHERE t.tierGrade IN :tierGradeList ");
//    query.append("  AND t.keyType = :keyType ");
//    query.append(")");
//    query.append("GROUP BY mi.id, mi.name, mi.level ");
//    query.append("HAVING mi.level = :level");
//
//    return em.createQuery(query.toString(), TierAverageDTO.class)
//      .setParameter("tierGradeList", tierGradeList)
//      .setParameter("keyType", keyType)
//      .setParameter("level", level)
//      .getResultList();

    StringBuffer query = new StringBuffer();
    query.append("SELECT tmp.music_info_id, tmp.name, avg(tmp.score), avg(tmp.point) ");
    query.append("FROM( ");
    query.append("  SELECT m.member_id, mi.music_info_id, mi.name, max(rd.score) as score, max(rd.point) as point ");
    query.append("  FROM Record r ");
    query.append("    JOIN (SELECT m.member_id ");
    query.append("      FROM Tier t ");
    query.append("        JOIN Member m ON t.member_id = m.member_id ");
    query.append("      WHERE t.tier_grade IN :tierGradeList ");
    query.append("      AND   t.key_type   = :keyType ");
    query.append("    )m ON m.member_id = r.member_id ");
    query.append("    JOIN Record_Detail rd ON rd.record_detail_id = r.record_detail_id ");
    query.append("    JOIN Music_Info mi ON mi.music_info_id = r.music_info_id ");
    query.append("  WHERE mi.level = :level ");
    query.append("  GROUP BY m.member_id, mi.music_info_id, mi.name ");
    query.append(") tmp ");
    query.append("GROUP BY tmp.music_info_id, tmp.name ");

    return resultMapper.list(em.createNativeQuery(query.toString())
      .setParameter("tierGradeList", tierGradeList.stream().map(TierGrade::name).collect(Collectors.toList()))
      .setParameter("keyType", keyType.name())
      .setParameter("level", level)
      , TierAverageDTO.class
    );

  }
  public List<RecordDetailDTO> findTop50RecordDetailDTOsByMemberAndKeyType(Member member, KeyType keyType)
  {
    StringBuffer query = new StringBuffer();
    query.append("SELECT rd.id, mi.name, mi.level, mi.difficulty, max(rd.score), max(rd.point) ");
    query.append("FROM Record r ");
    query.append("  JOIN r.recordDetail rd ");
    query.append("  JOIN r.member m ON m = :member ");
    query.append("  JOIN r.music mi ON mi.keyType = :keyType ");
    query.append("GROUP BY mi ");
    query.append("ORDER BY max(rd.point) DESC");

    return resultMapper.list(
      em.createQuery(query.toString())
        .setParameter("member", member)
        .setParameter("keyType", keyType)
        .setMaxResults(50)
      , RecordDetailDTO.class
    );
  }
}
