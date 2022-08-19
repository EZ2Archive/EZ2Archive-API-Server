package com.ez2db.common.config;

import com.ez2db.common.validator.DefaultMemberValidator;
import com.ez2db.common.validator.DefaultRecordValidator;
import com.ez2db.common.validator.Validator;
import com.ez2db.entity.Member;
import com.ez2db.entity.Record;
import com.ez2db.entity.TierGrade;
import com.ez2db.handler.DefaultTierHandler;
import com.ez2db.handler.TierHandler;
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
  public TierHandler<TierGrade> tierHandler() { return new DefaultTierHandler(); }

}
