package com.ez2archive.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("tokens")
@Getter @Setter
public class RefreshToken
{
  @Id
  private String userId;

  private String refreshToken;

  public RefreshToken()
  {
  }

  @Builder
  public RefreshToken(String userId, String refreshToken)
  {
    this.userId = userId;
    this.refreshToken = refreshToken;
  }
}
