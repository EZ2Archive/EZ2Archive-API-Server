package com.ez2archive.common.aspect;

import com.ez2archive.entity.MemberAuthority;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import static com.ez2archive.entity.MemberAuthority.REGULAR;

@Target({ ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiredAuthority
{
  MemberAuthority authority() default REGULAR;
}
