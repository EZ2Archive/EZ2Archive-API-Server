package com.ez2archive.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;

@Configuration
public class Swagger3Config
{
  private static final String BASE_PACKAGE = "com.ez2archive.controller.api";

  @Bean
  public Docket api()
  {
    return new Docket(DocumentationType.OAS_30)
      .useDefaultResponseMessages(false)
      .apiInfo( this.apiInfo() )
      .securityContexts(List.of(
        SecurityContext.builder()
          .securityReferences(
            List.of(
              new SecurityReference("Authorization", new AuthorizationScope[]{
                // request header = Authorization : {Type} {JwtToken}
                new AuthorizationScope("global", "none")
              }))
          )
          .build()
      ))
      .securitySchemes(List.of(
        new ApiKey("Authorization", "JWT", "header")
      ))
      .select()
      .apis( RequestHandlerSelectors.basePackage(BASE_PACKAGE) )
      .paths(PathSelectors.any())
      .build();
  }

  private ApiInfo apiInfo()
  {
    return ApiInfo.DEFAULT;
  }
}