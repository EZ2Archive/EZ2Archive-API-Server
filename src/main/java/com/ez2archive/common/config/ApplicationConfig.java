package com.ez2archive.common.config;

import com.ez2archive.common.validator.*;
import com.ez2archive.entity.*;
import com.ez2archive.handler.DefaultTierHandler;
import com.ez2archive.handler.TierHandler;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig
{
  @Bean
  public JpaResultMapper jpaResultMapper() { return new JpaResultMapper(); }

  @Bean
  public Validator<Member> memberValidator() { return new DefaultMemberValidator(); }

  @Bean
  public Validator<RecordDetail> recordDetailValidator() { return new DefaultRecordDetailValidator(); }

  @Bean
  public Validator<MusicInfo> musicInfoValidator() { return new DefaultMusicInfoValidator(); }

  @Bean
  public Validator<Email> emailValidator() { return new DefaultEmailValidator(); }

  @Bean
  public Validator<RankSurvey> rankValidator() { return new DefaultRankValidator();}

  @Bean
  public TierHandler<TierGrade> tierHandler() { return new DefaultTierHandler(); }

}
