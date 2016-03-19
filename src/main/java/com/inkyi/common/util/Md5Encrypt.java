package com.inkyi.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5\u52A0\u5BC6\u7B97\u6CD5
 */
public class Md5Encrypt {
    /**
     * Used building output as Hex
     */
    private static final char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
            'f' };
    
    /**
     * md5加密算法
     * @param text
     * @return
     */
    public static String md5(String text) {
        MessageDigest msgDigest = null;
        try {
            msgDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("System doesn't support MD5 algorithm.");
        }
        try {
            msgDigest.update(text.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("System doesn't support your  EncodingException.");
        }
        byte[] bytes = msgDigest.digest();
        String md5Str = new String(encodeHex(bytes));
        return md5Str.toUpperCase();
    }
    private static char[] encodeHex(byte[] data) {
        int l = data.length;
        char[] out = new char[l << 1];

        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
            out[j++] = DIGITS[0x0F & data[i]];
        }
        return out;
    }
    
    /**
     * 京东加密使用
     * @param bySourceByte
     * @return
     */
    public static byte[] getMD5Mac(byte[] bySourceByte){
        byte[] byDisByte;
        MessageDigest md;
        try{
        md = MessageDigest.getInstance("MD5"); 
        md.reset();
        md.update(bySourceByte);
        byDisByte = md.digest(); 
        }catch (NoSuchAlgorithmException n){
            
        return(null);
        }
        return(byDisByte);
        }

    public static String bintoascii(byte []bySourceByte)

        {
               int len,i;
               byte tb;
               char high,tmp,low;
               String result=new String();
               len=bySourceByte.length;
               for(i=0;i<len;i++)
               {
                      tb=bySourceByte[i];
                      tmp=(char)((tb>>>4)&0x000f);
                      if(tmp>=10)
                             high=(char)('a'+tmp-10);
                      else
                             high=(char)('0'+tmp);
                      result+=high;
                      tmp=(char)(tb&0x000f);
                      if(tmp>=10)
                             low=(char)('a'+tmp-10);
                      else
                             low=(char)('0'+tmp);
                      result+=low;
               }
               return result;
        }
     public static String getMD5ofStr(String inbuf)
     {
         if(inbuf==null||"".equals(inbuf)) return "";
         return bintoascii(getMD5Mac(inbuf.getBytes()));
     }
     /**
      * 该方法用于生成签名，参数inbuf=签名种子+xml字符串
      * @param inbuf
      * @return
      */
     public static String getSign(String inbuf){
         return getMD5ofStr(inbuf).toLowerCase();
     }
    

}