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
    nativeQuery.append("select coalesce(rd.record_detail_id, -1) ");
    nativeQuery.append(",      mi.music_info_id ");
    nativeQuery.append(",      mi.name ");
    nativeQuery.append(",      coalesce(rd.score, -1) ");
    nativeQuery.append(",      coalesce(rd.point, -1) ");
    nativeQuery.append(",      coalesce(rd.percentage, -1) ");
    nativeQuery.append(",      mi.ranks ");
    nativeQuery.append(",      mi.difficulty ");
    nativeQuery.append(",      coalesce(rd.grade, '') ");
    nativeQuery.append(",      coalesce(rd.is_all_cool, false) ");
    nativeQuery.append(",      coalesce(rd.is_no_miss, false) ");
    //nativeQuery.append(",      (select f.fileuuid from file f where mi.file_id = f.file_id) ");
    nativeQuery.append("from music_info mi ");
    nativeQuery.append("  left join ( ");
    nativeQuery.append("    select tr2.record_detail_id, tr2.music_info_id, tr2.member_id, tr2.add_time ");
    nativeQuery.append("    from ( ");
    nativeQuery.append("      select music_info_id, member_id, max(add_time) as add_time ");
    nativeQuery.append("      from record tr2 ");
    nativeQuery.append("      group by music_info_id, member_id ");
    nativeQuery.append("      having member_id = ( ");
    nativeQuery.append("        select member_id ");
    nativeQuery.append("        from member ");
    nativeQuery.append("        where user_id = :userId ");
    nativeQuery.append("      ) ");
    nativeQuery.append("    ) tr ");
    nativeQuery.append("    left join record tr2 on tr2.music_info_id = tr.music_info_id and tr2.add_time = tr.add_time and tr2.member_id = tr.member_id ");
    nativeQuery.append("  ) r on mi.music_info_id = r.music_info_id   ");
    nativeQuery.append("  left join record_detail rd on rd.record_detail_id = r.record_detail_id ");
    nativeQuery.append("where mi.key_type = :keyType ");
    nativeQuery.append("and   mi.level = :level ");
    nativeQuery.append("order by mi.ranks desc, mi.name ");
    
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
    nativeQuery.append("select avg(rd.percentage) rateavg ");
    nativeQuery.append(",      0  ");
    nativeQuery.append(",      sum(case when rd.is_all_cool = true then 1 else 0 end) allcoolcnt  ");
    nativeQuery.append(",      sum(case when rd.is_no_miss  = true then 1 else 0 end) nomisscnt  ");
    nativeQuery.append(",      sum(case when rd.grade       = 'sppp' then 1 else 0 end) spppcnt  ");
    nativeQuery.append(",      sum(case when rd.grade       = 'spp' then 1 else 0 end) sppcnt  ");
    nativeQuery.append(",      sum(case when rd.grade       = 'sp' then 1 else 0 end) spcnt  ");
    nativeQuery.append("from music_info mi    ");
    nativeQuery.append("  join (  ");
    nativeQuery.append("    select tr2.record_detail_id, tr2.music_info_id, tr2.member_id, tr2.add_time ");
    nativeQuery.append("    from ( ");
    nativeQuery.append("      select music_info_id, member_id, max(add_time) as add_time ");
    nativeQuery.append("      from record tr2 ");
    nativeQuery.append("      group by music_info_id, member_id ");
    nativeQuery.append("      having member_id = ( ");
    nativeQuery.append("        select member_id ");
    nativeQuery.append("        from member ");
    nativeQuery.append("        where user_id = :userId ");
    nativeQuery.append("      ) ");
    nativeQuery.append("    ) tr ");
    nativeQuery.append("    left join record tr2 on tr2.music_info_id = tr.music_info_id and tr2.add_time = tr.add_time and tr2.member_id = tr.member_id ");
    nativeQuery.append("  ) r on mi.music_info_id = r.music_info_id    ");
    nativeQuery.append("  join record_detail rd on rd.record_detail_id = r.record_detail_id  ");
    nativeQuery.append("where mi.key_type = :keyType  ");
    nativeQuery.append("and   mi.level = :level  ");
    nativeQuery.append("group by r.member_id");

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
