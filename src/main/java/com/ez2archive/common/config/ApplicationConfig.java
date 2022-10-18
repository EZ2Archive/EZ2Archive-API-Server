package com.ez2archive.common.config;

import com.ez2archive.common.validator.*;
import com.ez2archive.dto.auth.RequestLoginDTO;
import com.ez2archive.dto.auth.RequestPasswordModifyDTO;
import com.ez2archive.dto.auth.RequestSignUpDTO;
import com.ez2archive.entity.MusicInfo;
import com.ez2archive.entity.RankSurvey;
import com.ez2archive.entity.RecordDetail;
import com.ez2archive.entity.TierGrade;
import com.ez2archive.handler.DefaultTierHandler;
import com.ez2archive.handler.TierHandler;
import com.ez2archive.repository.MusicInfoRepository;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig
{
  private final MusicInfoRepository musicInfoRepository;

  @Bean
  public String frontContextPath(@Value("${spring.security.front-end.context-path}") String frontContextPath) { return frontContextPath; }

  @Bean
  public JpaResultMapper jpaResultMapper() { return new JpaResultMapper(); }

  @Bean
  public Validator<RequestSignUpDTO> signUpValidator() { return new DefaultSignUpValidator(); }

  @Bean
  public Validator<RecordDetail> recordDetailValidator() { return new DefaultRecordDetailValidator(); }

  @Bean
  public Validator<MusicInfo> musicInfoValidator() { return new DefaultMusicInfoValidator(); }

  @Bean
  public Validator<RankSurvey> rankValidator() { return new DefaultRankSurveyValidator();}

  @Bean
  public Validator<RequestLoginDTO> loginValidator() { return new DefaultLoginValidator(); }

  @Bean
  public Validator<RequestPasswordModifyDTO> passwordModifyValidator() { return new DefaultPasswordModifyValidator(); }

  @Bean
  public TierHandler<TierGrade> tierHandler() { return new DefaultTierHandler(musicInfoRepository); }

}
