package com.ez2archive.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer
{
//  private final String DISK_IMG_PATH;
//
//  public WebMvcConfig(@Value("${spring.servlet.multipart.location}") String diskImgPath)
//  {
//    DISK_IMG_PATH = diskImgPath;
//  }
//  @Override
//  public void addResourceHandlers(ResourceHandlerRegistry registry)
//  {
//    registry.addResourceHandler("/files/images/**")
//      .addResourceLocations("file:///" + DISK_IMG_PATH + "/")
//      .setCachePeriod(3600)
//      .resourceChain(true)
//      .addResolver(new PathResourceResolver());
//
//    WebMvcConfigurer.super.addResourceHandlers(registry);
//  }
  @Override
  public void addCorsMappings(CorsRegistry registry)
  {
    // Global CORS Setting
    registry.addMapping("/**")
      .allowedOrigins("*");

    WebMvcConfigurer.super.addCorsMappings(registry);
  }

}
