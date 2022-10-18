package com.ez2archive.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@RedisHash("passwordModifyToken")
@Getter @Setter
public class PasswordModifyToken
{
  @Id
  private String token;

  private String userId;

  private LocalDateTime addTime;

  /** transient */
  @JsonDeserialize
  private static final int EXPIRE_AMOUNT = 30;
  /** transient */
  @JsonDeserialize
  private static final ChronoUnit EXPIRE_UNIT = ChronoUnit.MINUTES;

  public PasswordModifyToken()
  {
  }

  @Builder
  public PasswordModifyToken(String userId, String token, LocalDateTime addTime)
  {
    this.userId = userId;
    this.token = token;
    this.addTime = addTime;
  }

  public LocalDateTime getExpireTime()
  {
    return this.addTime.plus(EXPIRE_AMOUNT, EXPIRE_UNIT);
  }

  public static LocalDateTime getExpireTime(LocalDateTime time)
  {
    return time.plus(EXPIRE_AMOUNT, EXPIRE_UNIT);
  }

  public boolean isExpired()
  {
    return this.addTime.isAfter(this.getExpireTime());
  }
}
