package com.ez2db.common.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;
import springfox.documentation.annotations.ApiIgnore;

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
