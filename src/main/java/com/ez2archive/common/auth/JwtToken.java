package com.ez2archive.common.auth;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class JwtToken
{
  @ApiModelProperty(hidden = true)
  private String type;

  @ApiModelProperty(hidden = true)
  private String token;

  public JwtToken(String type, String token)
  {
    this.type = type;
    this.token = token;
  }
}
