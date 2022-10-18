package com.ez2archive.repository;

import com.ez2archive.entity.Member;
import com.ez2archive.entity.MusicInfo;
import com.ez2archive.entity.RankSurvey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RankSurveyRepository extends JpaRepository<RankSurvey, Long>
{
  Optional<RankSurvey> findRankSurveyByMusicAndMember(MusicInfo music, Member member);

  @Query("SELECT count(r.point) FROM RankSurvey r WHERE r.music = :music")
  Long sumPointByMusicInfo(@Param("music") MusicInfo music);
}
