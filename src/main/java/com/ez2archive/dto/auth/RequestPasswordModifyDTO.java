package com.ez2archive.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RequestPasswordModifyDTO
{
  private String password;
  private String token;
}
