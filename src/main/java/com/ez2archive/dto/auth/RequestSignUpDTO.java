package com.ez2archive.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RequestSignUpDTO
{
  private String userId;
  private String name;
  private String password;
  private String email;
}
