package com.ez2db.common.config;

import com.ez2db.common.handler.file.CommonFileHandler;
import com.ez2db.common.handler.file.FileHandler;
import com.ez2db.common.validator.DefaultMemberValidator;
import com.ez2db.common.validator.DefaultRecordValidator;
import com.ez2db.common.validator.Validator;
import com.ez2db.entity.Member;
import com.ez2db.entity.Record;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

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
  public FileHandler<MultipartFile> fileHandler() { return new CommonFileHandler(); }
}
