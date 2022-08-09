package com.ez2db.ez2db;

import java.util.UUID;

public class CommonTest
{
  public static void main(String[] args)
  {
    String uuid = UUID.randomUUID().toString();

    System.out.println("uuid = " + uuid);

    String fileName = "C:\\Users\\Adam\\Desktop\\text.txt";

    System.out.println("getExt(fileName) = " + getExt(fileName));
  }

  private static final String getExt(String fileName)
  {
    return fileName.substring(fileName.lastIndexOf("."));
  }
}
