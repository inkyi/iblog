package com.inkyi.common.util;

import java.lang.Character.UnicodeBlock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilterSpecialCharacter {
	
	/**
	 * 
	* @author: gavin
	* @date:   2015年7月27日
	* @Title:  getStr
	* @Description: 过滤特殊字符
	* @param @param str
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	public static String filterSpeChar(String str){
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			if ((str.charAt(i)+"").getBytes().length<1) {
				sb.append(str.charAt(i));
			}else{
				sb.append(getChinese(str.charAt(i)+""));
			}
		}
		return sb.toString();
	}
	/**
	 * 	
	* @author: gavin
	* @date:   2015年7月27日
	* @Title:  getChinese
	* @Description: 提取中文，换掉特殊字符
	* @param @param str
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	public static String getChinese(String str){
		//int count = 0;
		String regEx = "[\\u4e00-\\u9fa5]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		while (m.find()) {
			return m.group(0);
		}
		return "a";
	}
	public String gbk2utf8(String gbk) {
        String l_temp = GBK2Unicode(gbk);
        l_temp = unicodeToUtf8(l_temp);
 
        return l_temp;
    }
 
    public String utf82gbk(String utf) {
        String l_temp = utf8ToUnicode(utf);
        l_temp = Unicode2GBK(l_temp);
 
        return l_temp;
    }
 
    /**
     *
     * @param str
     * @return String
     */
 
    public static String GBK2Unicode(String str) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char chr1 = (char) str.charAt(i);
 
            if (!isNeedConvert(chr1)) {
                result.append(chr1);
                continue;
            }
 
            result.append("\\u" + Integer.toHexString((int) chr1));
        }
 
        return result.toString();
    }
 
    /**
     *
     * @param dataStr
     * @return String
     */
 
    public static String Unicode2GBK(String dataStr) {
        int index = 0;
        StringBuffer buffer = new StringBuffer();
 
        int li_len = dataStr.length();
        while (index < li_len) {
            if (index >= li_len - 1
                    || !"\\u".equals(dataStr.substring(index, index + 2))) {
                buffer.append(dataStr.charAt(index));
 
                index++;
                continue;
            }
 
            String charStr = "";
            charStr = dataStr.substring(index + 2, index + 6);
 
            char letter = (char) Integer.parseInt(charStr, 16);
 
            buffer.append(letter);
            index += 6;
        }
 
        return buffer.toString();
    }
 
    public static boolean isNeedConvert(char para) {
        return ((para & (0x00FF)) != para);
    }
    public static boolean isEmojiCharacter(char codePoint) {
		return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA)
				|| (codePoint == 0xD)
				|| ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
				|| ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
				|| ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
	}
    /**
     * utf-8 转unicode
     *
     * @param inStr
     * @return String
     */
    public static String utf8ToUnicode(String inStr) {
        char[] myBuffer = inStr.toCharArray();
 
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < inStr.length(); i++) {
        	if(isEmojiCharacter(myBuffer[i])){
        		sb.append(myBuffer[i]);
        	}else{
        		UnicodeBlock ub = UnicodeBlock.of(myBuffer[i]);
                if (ub == UnicodeBlock.BASIC_LATIN) {
                    sb.append(myBuffer[i]);
                } else if (ub == UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
                    int j = (int) myBuffer[i] - 65248;
                    sb.append((char) j);
                } else {
                    short s = (short) myBuffer[i];
                    String hexS = Integer.toHexString(s).substring(4);
                    String unicode = "\\u" + hexS;
                    sb.append(unicode.toLowerCase());
                }
        	}
            
        }
        return sb.toString();
    }
 
    /**
     *
     * @param theString
     * @return String
     */
    public static String unicodeToUtf8(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len;) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9':
                            value = (value << 4) + aChar - '0';
                            break;
                        case 'a':
                        case 'b':
                        case 'c':
                        case 'd':
                        case 'e':
                        case 'f':
                            value = (value << 4) + 10 + aChar - 'a';
                            break;
                        case 'A':
                        case 'B':
                        case 'C':
                        case 'D':
                        case 'E':
                        case 'F':
                            value = (value << 4) + 10 + aChar - 'A';
                            break;
                        default:
                            throw new IllegalArgumentException(
                                    "Malformed   \\uxxxx   encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }
}
