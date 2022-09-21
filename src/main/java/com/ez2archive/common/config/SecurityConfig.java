package com.ez2archive.common.config;

import com.ez2archive.common.auth.JwtToken;
import com.ez2archive.common.auth.JwtTokenProvider;
import com.ez2archive.common.auth.TokenProvider;
import com.ez2archive.common.crypt.DefaultHashCryptor;
import com.ez2archive.common.handler.crypt.EmailCryptHandler;
import com.ez2archive.common.handler.crypt.PasswordCryptHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.SecureRandom;

@Configuration
public class SecurityConfig
{
  @Value("${spring.security.custom.hash.algorithm}")
  private String HASH_ALGORITHM;

  @Value("${spring.security.custom.email.salt}")
  private String EMAIL_SALT;

  @Value("${spring.security.custom.jwt.secret-key}")
  protected String TOKEN_SECRET_KEY;

  @Bean
  public PasswordCryptHandler passwordCryptHandler() { return new PasswordCryptHandler(new DefaultHashCryptor(HASH_ALGORITHM)); }

  @Bean
  public EmailCryptHandler emailCryptHandler() { return new EmailCryptHandler(new DefaultHashCryptor(HASH_ALGORITHM), EMAIL_SALT); }

  @Bean
  public SecureRandom secureRandom()
  {
    return new SecureRandom();
  }

  @Bean
  public TokenProvider<String, JwtToken> tokenProvider() { return new JwtTokenProvider(TOKEN_SECRET_KEY); }
}
