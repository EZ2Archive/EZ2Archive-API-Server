package com.ez2db.common.config;

import com.ez2db.common.auth.JwtToken;
import com.ez2db.common.auth.JwtTokenProvider;
import com.ez2db.common.auth.TokenProvider;
import com.ez2db.common.crypt.DefaultPasswordCryptor;
import com.ez2db.common.handler.crypt.PasswordCryptHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.security.SecureRandom;

@Configuration
@PropertySource("classpath:security.properties")
public class SecurityConfig
{
  @Value("${ez2db.security.password.algorithm}")
  private String PASSWORD_ALGORITHM;

  @Bean
  public PasswordCryptHandler passwordCryptHandler() { return new PasswordCryptHandler(new DefaultPasswordCryptor(PASSWORD_ALGORITHM)); }

  @Bean
  public SecureRandom secureRandom()
  {
    return new SecureRandom();
  }

  @Bean
  public TokenProvider<String, JwtToken> tokenProvider() { return new JwtTokenProvider(); }
}