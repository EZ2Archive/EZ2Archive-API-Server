package com.ez2archive.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("tokenBlackList")
@Getter @Setter
public class BlackList
{
  
}
