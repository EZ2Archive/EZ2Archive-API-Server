package com.ez2archive.common.auth;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;

@Getter
public class JwtToken
{
  @ApiModelProperty(hidden = true)
  private String type;

  @ApiModelProperty(hidden = true)
  private String accessToken;

  @ApiModelProperty(hidden = true)
  @Id
  private String refreshToken;

  public JwtToken()
  {
  }

  @Builder
  public JwtToken(String type, String accessToken, String refreshToken)
  {
    this.type = type;
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
  }
}
