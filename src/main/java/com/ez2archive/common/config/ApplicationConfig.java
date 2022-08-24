package com.ez2archive.common.config;

import com.ez2archive.common.validator.DefaultMemberValidator;
import com.ez2archive.common.validator.DefaultMusicInfoValidator;
import com.ez2archive.common.validator.DefaultRecordValidator;
import com.ez2archive.common.validator.Validator;
import com.ez2archive.entity.Member;
import com.ez2archive.entity.MusicInfo;
import com.ez2archive.entity.Record;
import com.ez2archive.entity.TierGrade;
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
  public Validator<Record> recordValidator() { return new DefaultRecordValidator(); }

  @Bean
  public Validator<MusicInfo> musicInfoValidator() { return new DefaultMusicInfoValidator(); }

  @Bean
  public TierHandler<TierGrade> tierHandler() { return new DefaultTierHandler(); }

}
