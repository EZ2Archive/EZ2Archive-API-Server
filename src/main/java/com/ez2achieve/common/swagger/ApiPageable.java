package com.ez2achieve.common.swagger;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ApiImplicitParams({
  @ApiImplicitParam(name = "page", dataType = "int", paramType = "query", defaultValue = "0"),
  @ApiImplicitParam(name = "size", dataType = "int", paramType = "query", defaultValue = "5"),
  @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query")
})
public @interface ApiPageable
{
}
