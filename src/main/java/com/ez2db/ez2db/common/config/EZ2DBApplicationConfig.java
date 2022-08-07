package com.ez2db.ez2db.common.config;

import com.ez2db.ez2db.common.crypt.DefaultPasswordCryptor;
import com.ez2db.ez2db.common.handler.login.PasswordCryptHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.security.SecureRandom;

@Configuration
@PropertySource("classpath:security.properties")
public class EZ2DBApplicationConfig
{
  @Value("${ez2db.security.password.algorithm}")
  private String PASSWORD_ALGORITHM;

  @Bean
  public PasswordCryptHandler passwordCryptHandler()
  {
    return new PasswordCryptHandler(new DefaultPasswordCryptor(PASSWORD_ALGORITHM));
  }

  @Bean
  public SecureRandom secureRandom()
  {
    return new SecureRandom();
  }

}
