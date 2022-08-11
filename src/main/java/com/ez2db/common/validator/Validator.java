package com.ez2db.common.validator;

public interface Validator<T>
{
  boolean isValid(T t);
}
