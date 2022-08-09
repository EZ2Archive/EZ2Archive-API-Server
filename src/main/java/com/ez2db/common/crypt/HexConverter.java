package com.ez2db.common.crypt;

import java.nio.charset.StandardCharsets;

/**
 * <b>HexConverter</b>
 * <pre>
 *   - 16진 문자열 변환기
 * </pre>
 */
@SuppressWarnings("JavaDoc")
public class HexConverter
{
  /**
   * 바이트 배열을 16진수 문자열로 인코딩
   * @param bytes 16진수 문자열로 변환할 바이트 배열
   * @return
   */
  public static String encode(byte [] bytes)
  {
    final StringBuilder sb = new StringBuilder();

    for(byte b : bytes)
    {
      String hexStr = Integer.toString(b & 0xff, 16);
      if(hexStr.length() < 2)
      {
        sb.append('0');
      }
      sb.append(hexStr);
    }
    return sb.toString();
    //return sb.toString().toUpperCase(Locale.ROOT);
  }

  /**
   * 16진수 문자열을 바이트 배열로 디코딩
   * @param hexStr 바이트 배열로 변환할 16진수 문자열
   * @return
   */
  public static byte [] decode(String hexStr)
  {
    byte [] temp = hexStr.getBytes(StandardCharsets.UTF_8);
    byte [] result = new byte[ temp.length / 2];

    for(int i=0; i<result.length; i++)
    {
      int index = i*2;
      result[i] = (byte)Integer.parseInt(hexStr.substring(index, index+2), 16);
    }

    return result;
  }
}
