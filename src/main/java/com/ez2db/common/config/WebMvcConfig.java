package com.ez2db.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer
{
  private final String DISK_IMG_PATH;

  public WebMvcConfig(@Value("${spring.servlet.multipart.location}") String diskImgPath)
  {
    DISK_IMG_PATH = diskImgPath;
  }

  @Override
  public void addCorsMappings(CorsRegistry registry)
  {
    WebMvcConfigurer.super.addCorsMappings(registry);

    // Global CORS Setting
    registry.addMapping("/**")
      .allowedOrigins("*");
  }

  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters)
  {
    // Global Encode Setting
    converters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
    WebMvcConfigurer.super.configureMessageConverters(converters);
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry)
  {
    registry.addResourceHandler("/files/images/**")
      .addResourceLocations("file:///" + DISK_IMG_PATH + "/")
      .setCachePeriod(3600)
      .resourceChain(true)
      .addResolver(new PathResourceResolver());

    WebMvcConfigurer.super.addResourceHandlers(registry);
  }
}
