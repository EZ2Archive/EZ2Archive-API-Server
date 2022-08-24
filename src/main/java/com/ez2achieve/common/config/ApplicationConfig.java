package com.ez2achieve.common.config;

import com.ez2achieve.common.validator.DefaultMemberValidator;
import com.ez2achieve.common.validator.DefaultMusicInfoValidator;
import com.ez2achieve.common.validator.DefaultRecordValidator;
import com.ez2achieve.common.validator.Validator;
import com.ez2achieve.entity.Member;
import com.ez2achieve.entity.MusicInfo;
import com.ez2achieve.entity.Record;
import com.ez2achieve.entity.TierGrade;
import com.ez2achieve.handler.DefaultTierHandler;
import com.ez2achieve.handler.TierHandler;
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
  public Validator<Record> recordValidator() { return new DefaultRecordValidator(); }

  @Bean
  public Validator<MusicInfo> musicInfoValidator() { return new DefaultMusicInfoValidator(); }

  @Bean
  public TierHandler<TierGrade> tierHandler() { return new DefaultTierHandler(); }

}
