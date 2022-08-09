package com.ez2db.common.handler.file;

import java.io.IOException;
import java.util.List;

public interface FileHandler<T>
{
  void save(T t) throws IOException;

  default void saveAll(List<T> ts) throws IOException
  {
    for ( T t : ts )
    {
      save(t);
    }
  }

  String getFullPath(String fileName);
}
