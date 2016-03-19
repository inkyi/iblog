package com.inkyi.common.util;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class MiscUtil
{

    /**
     * 判断输入的字符串是否是空(包含trim操作)
     * 
     * @param inStr
     *            输入字符串
     * 
     * @return
     * 
     */
    public static boolean isNullOrEmpty(String inStr)
    {
        return (inStr == null || inStr.trim().length() == 0);
    }
    
    /**
     * 判断输入的字符串不为空且长度大于零(包含trim操作)
     * 
     * @param inStr
     *            输入字符串
     * 
     * @return
     * 
     */
    public static boolean isNotNullAndNotEmpty(String inStr)
    {
        return (inStr != null && inStr.trim().length() > 0);
    }

    /**
     * 新建字符串 inStr==null?"":new String (inStr)
     * 
     * @param inStr
     *            输入字符串
     * 
     * @return
     * 
     */
    public static String newString(String inStr)
    {
        return inStr == null ? "" : new String(inStr);
    }
    
    /**
     * 对象转成String null->""
     * @param object
     * @return
     */
    public static String objectToString(Object object){
        if (object==null){
            return ""; 
        }
        return object.toString();
    }

    /**
     * 保留有效位数, 超过长度从左保留, 不足长度原样输出 abc 2 ->ab
     * 
     * @param inStr
     *            输入字符串
     * @param length
     *            长度
     * @return
     */
    public static String subString(String inStr, int length)
    {
        if (inStr == null || inStr.length() == 0 || length <= 0)
        {
            return "";
        }
        else if (inStr.length() >= length)
        {
            return inStr.substring(0, length);
        }
        else
        {
            return inStr;
        }
    }

    /**
     * 保留有效位数, 超过长度从右保留, 不足长度原样输出 2 abc -> bc
     * 
     * @param inStr
     *            输入字符串
     * @param length
     *            长度
     * @return
     */
    public static String subString(int length, String inStr)
    {
        if (inStr == null || inStr.length() == 0 || length <= 0)
        {
            return "";
        }
        else if (inStr.length() >= length)
        {
            return inStr.substring(inStr.length() - length, inStr.length());
        }
        else
        {
            return inStr;
        }
    }

    /**
     * 左补零 按有效位数截取保留右侧 null->"" abc 5 -> 00abc
     * 
     * @param pinseed
     * @param i
     * @return
     */
    public static String lengthPinseed(String pinseed, int i)
    {
        if (i <= 0)
        {
            return "";
        }
        if (pinseed == null)
        {
            pinseed = "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        try
        {
            for (int j = 0; j < i; j++)
            {
                stringBuffer.append("0");
            }

            if (pinseed.length() < i)
            {
                pinseed = stringBuffer.toString() + pinseed;
            }
            if (pinseed.length() > i)
            {
                pinseed = pinseed.substring(pinseed.length() - i, pinseed.length());
            }
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            stringBuffer.replace(0, stringBuffer.length(), "");
        }
        return pinseed;
    }
    
    /**
     * 左补a 默认为"0" 按有效位数截取保留右侧 null->"" abc 5 -> 00abc
     * 
     * @param pinseed 源字符串
     * @param i 有效位
     * @param a 填充元素
     * @return
     */
    public static String lengthPinseed2(String pinseed, int i, String a)
    {
        if (i <= 0)
        {
            return "";
        }
        if (pinseed == null)
        {
            pinseed = "";
        }
        if (a == null ){
            a="0";
        }
        StringBuffer stringBuffer = new StringBuffer();
        try
        {
            for (int j = 0; j < i; j++)
            {
                stringBuffer.append(a);
            }

            if (pinseed.length() < i)
            {
                pinseed = stringBuffer.toString() + pinseed;
            }
            if (pinseed.length() > i)
            {
                pinseed = pinseed.substring(pinseed.length() - i, pinseed.length());
            }
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            stringBuffer.replace(0, stringBuffer.length(), "");
        }
        return pinseed;
    }

  
  /**
   *   从左到右, 优先返回非空字段内容
   *   判断输入的字符串是否为空(不包含trim操作) 
   * @param inStr1
   * @param inStr2
   * @return
   */
    public static String chooseInOther (String... inStr)
    {
        for (int i = 0; i < inStr.length; i++)
        {
            String string = inStr[i];
            if (string != null && string.length() > 0){
                return string;
            }
        }
            return "";
    }

    /**
     *  URLEncoder map 参数
     * @param oMap
     * @param inputChatset
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map getURLEncoderMap(Map oMap, String inputChatset)
			throws Exception {
		Map map = new HashMap();
		Set<Map.Entry> set = oMap.entrySet();
		for (Iterator<Map.Entry> it = set.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			map.put(entry.getKey(), URLEncoder.encode(
					(String) entry.getValue(), inputChatset));
		}
		return map;
	}
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        System.out.println(subString(null, 3));
        System.out.println(lengthPinseed("1234", 10));
        System.out.println(chooseInOther(null, "","123"));
    }

}
