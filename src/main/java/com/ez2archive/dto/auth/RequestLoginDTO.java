package com.ez2archive.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RequestLoginDTO
{
  private String userId;
  private String password;
}
