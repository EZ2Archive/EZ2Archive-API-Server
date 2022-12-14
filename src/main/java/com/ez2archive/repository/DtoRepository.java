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
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings({ "StringBufferReplaceableByString", "StringBufferMayBeStringBuilder" })
@Repository
@RequiredArgsConstructor
public class DtoRepository
{
  private final EntityManager em;

  private final JpaResultMapper resultMapper;

  /**
   * 사용자 티어구간으로부터 평균 스코어, 평균 티어포인트 조회한다.
   * @param tierGradeList 사용자의 티어구간 목록 (ex. SILVER2 = {SILVER1, SILVER2, SILVER3, SILVER4})
   * @param keyType 조회할 키 타입
   * @param level 조회할 레벨
   * @return
   */
  public List<TierAverageDTO> findAvgRecordsByMusicInTierGradeAndKeyType(List<TierGrade> tierGradeList, KeyType keyType, int level)
  {
    StringBuffer query = new StringBuffer();
    query.append("select tmp.music_info_id, tmp.name, avg(tmp.score), avg(tmp.point) ");
    query.append("from( ");
    query.append("  select m.member_id, mi.music_info_id, mi.name, max(rd.score) as score, max(rd.point) as point ");
    query.append("  from record r ");
    query.append("    join (select m.member_id ");
    query.append("      from tier t ");
    query.append("        join member m on t.member_id = m.member_id ");
    query.append("      where t.tier_grade in :tierGradeList ");
    query.append("      and   t.key_type   = :keyType ");
    query.append("    )m on m.member_id = r.member_id ");
    query.append("    join record_detail rd on rd.record_detail_id = r.record_detail_id ");
    query.append("    join music_info mi on mi.music_info_id = r.music_info_id ");
    query.append("  where mi.level = :level ");
    query.append("  group by m.member_id, mi.music_info_id, mi.name ");
    query.append(") tmp ");
    query.append("group by tmp.music_info_id, tmp.name ");

    return resultMapper.list(em.createNativeQuery(query.toString())
      .setParameter("tierGradeList", tierGradeList.stream().map(TierGrade::name).collect(Collectors.toList()))
      .setParameter("keyType", keyType.name())
      .setParameter("level", level)
      , TierAverageDTO.class
    );

  }

  /**
   * 플레이 기록 중 티어포인트가 가장 높은 50곡 목록을 조회한다.
   * @param member 조회할 사용자
   * @param keyType 조회할 키 타입
   * @return
   */
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

  /**
   * 사용자 티어구간으로부터 평균 스코어, 평균 티어포인트 조회한다.
   * @param tierGradeList 사용자의 티어구간 목록 (ex. SILVER2 = {SILVER1, SILVER2, SILVER3, SILVER4})
   * @param keyType 조회할 키 타입
   * @param musicInfoId 조회할 음원
   * @return
   */
  public Optional<TierAverageDTO> findAvgRecordByMusicInfoIdInTierGrade(List<TierGrade> tierGradeList, KeyType keyType, Long musicInfoId)
  {
    StringBuffer query = new StringBuffer();
    query.append("select tmp.music_info_id, tmp.name, avg(tmp.score), avg(tmp.point) ");
    query.append("from( ");
    query.append("  select m.member_id, mi.music_info_id, mi.name, max(rd.score) as score, max(rd.point) as point ");
    query.append("  from record r ");
    query.append("    join (select m.member_id ");
    query.append("      from tier t ");
    query.append("        join member m on t.member_id = m.member_id ");
    query.append("      where t.tier_grade in :tierGradeList ");
    query.append("      and   t.key_type   = :keyType ");
    query.append("    )m on m.member_id = r.member_id ");
    query.append("    join record_detail rd on rd.record_detail_id = r.record_detail_id ");
    query.append("    join music_info mi on mi.music_info_id = r.music_info_id ");
    query.append("  where mi.music_info_id = :musicInfoId ");
    query.append("  group by m.member_id, mi.music_info_id, mi.name ");
    query.append(") tmp ");
    query.append("group by tmp.music_info_id, tmp.name ");

    Optional<TierAverageDTO> result;

    try
    {
      result = Optional.ofNullable(resultMapper.uniqueResult(
        em.createNativeQuery(query.toString())
          .setParameter("tierGradeList", tierGradeList.stream().map(TierGrade::name).collect(Collectors.toList()))
          .setParameter("keyType", keyType.name())
          .setParameter("musicInfoId", musicInfoId),
        TierAverageDTO.class
      ));
    }
    catch( NoResultException e )
    {
      result = Optional.empty();
    }

    return result;
  }
}
