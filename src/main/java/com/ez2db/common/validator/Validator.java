package com.ez2db.common.validator;

import java.util.Arrays;

public interface Validator<T>
{
  boolean isValid(T t);

  default boolean isValidWithTrim(T t)
  {
    trim(t);

    return isValid(t);
  }

  default void trim(T t)
  {
    Arrays.stream( t.getClass().getDeclaredFields() )
      .filter( field -> field.getType() == String.class )
      .forEach(field -> {
        field.setAccessible(true);

        try
        {
          String value = (String) field.get(t);
          if( value != null ) field.set(t, value.trim());
        }
        catch ( IllegalAccessException e )
        {
          throw new RuntimeException(e);
        }

        field.setAccessible(false);
      });

  }
}
