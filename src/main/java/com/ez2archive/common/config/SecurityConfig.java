package com.ez2archive.common.config;

import com.ez2archive.common.auth.JwtToken;
import com.ez2archive.common.auth.JwtTokenProvider;
import com.ez2archive.common.auth.TokenProvider;
import com.ez2archive.common.crypt.DefaultPasswordCryptor;
import com.ez2archive.common.crypt.DefaultEmailCryptor;
import com.ez2archive.common.handler.crypt.EmailCryptHandler;
import com.ez2archive.common.handler.crypt.PasswordCryptHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.security.SecureRandom;

@Configuration
@PropertySource("classpath:security.properties")
public class SecurityConfig
{
  @Value("${ez2archive.security.password.algorithm}")
  private String PASSWORD_ALGORITHM;

  @Value("${ez2archive.security.email.algorithm}")
  private String EMAIL_ALGORITHM;

  @Value("${ez2archive.security.email.salt}")
  private String EMAIL_SALT;

  @Value("${ez2archive.security.jwt.secret-key}")
  protected String TOKEN_SECRET_KEY;

  @Bean
  public PasswordCryptHandler passwordCryptHandler() { return new PasswordCryptHandler(new DefaultPasswordCryptor(PASSWORD_ALGORITHM)); }

  @Bean
  public EmailCryptHandler emailCryptHandler() { return new EmailCryptHandler(new DefaultEmailCryptor(EMAIL_ALGORITHM, EMAIL_SALT)); }

  @Bean
  public SecureRandom secureRandom()
  {
    return new SecureRandom();
  }

  @Bean
  public TokenProvider<String, JwtToken> tokenProvider() { return new JwtTokenProvider(TOKEN_SECRET_KEY); }
}
