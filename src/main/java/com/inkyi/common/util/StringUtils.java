package com.inkyi.common.util;

import java.util.regex.Pattern;

/**
 * @desc 系统字符串处理类
 * @author Gavin
 * @date 2014-12-4
 * @time 上午10:30:51
 * @version 1.0
 */
public class StringUtils {
	
	private static String[] denied_keywords={"paypassword","password","confirm_password","oldpassword","newpassword","newpassword1","oldpasswordpay","newpasswordpay","newpassword1pay"};
	
	/**
	* @author: gavin
	* @date:   2015年7月13日
	* @Title:  isNull
	* @Description: 如果str为null，返回“”,否则返回str
	* @param @param str
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	public static String isNull(String str) {
		if (str == null) {
			return "";
		}
		return str.trim();
	}
	
	/**
	* @author: gavin
	* @date:   2015年7月13日
	* @Title:  isEmpty
	* @Description: 判断当前传入的字符串是否为空
	* @param @param a
	* @param @return    设定文件
	* @return boolean    返回类型
	* @throws
	 */
	public static boolean isEmpty(String a){
		if(null != a && !"".equals(a)){
			return false;
		}
		return true;
	}
	
	/**
	* @author: gavin
	* @date:   2015年7月13日
	* @Title:  wash
	* @Description: 清洗传入的字符串
	* @param @param replaceStr
	* @param @param sourceStr
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	public static String wash(String replaceStr, String sourceStr){
		if(!isEmpty(sourceStr)){
			sourceStr = sourceStr.replaceAll(replaceStr, " ");
		}
		return sourceStr;
	}
	
	/**
	* @author: gavin
	* @date:   2015年7月13日
	* @Title:  isDeniedKeywords
	* @Description: 是否存在需要过滤的字符
	* @param @param text
	* @param @return    设定文件
	* @return boolean    返回类型
	* @throws
	 */
	public static boolean isDeniedKeywords(String text){
		for(int j=0;j<denied_keywords.length;j++){
			String denied_Text=StringUtils.isNull(denied_keywords[j]);
			if(StringUtils.isNull(text).toLowerCase().indexOf(denied_Text)>-1){
				return true;
			}
		}
		return false;
	}
	
	/**
	* @Title: isNumeric
	* @Date: 2015-2-28
	* @Autor: gavin
	* @Description: TODO(验证字符串是否是有效数字)
	* @param @param str
	* @return boolean    返回类型
	* @throws
	 */
	public static boolean isNumeric(String str)
	{
		//正则判断 整数 非0开头小数 验证
		Pattern pattern = Pattern.compile("^[1-9][0-9]*\\.?[0-9]{0,}");
	    return pattern.matcher(str).matches();    
	}
	
	/**
	* @Title: isPhone
	* @Date: 2015-3-30
	* @Autor: gavin
	* @Description: TODO(验证字符串是否是手机号)
	* @param @param str
	* @return boolean    返回类型
	* @throws
	 */
	public static boolean isPhone(String str)
	{
		//正则判断 整数 非0开头小数 验证
		Pattern pattern = Pattern.compile("^1[3,5,7,8]\\d{9}");
	    return pattern.matcher(str).matches();    
	}
	
	/**
	* @author: gavin
	* @date:   2015年7月13日
	* @Title:  unicode2String
	* @Description: unicode转化为UTF8
	* @param @param unicode
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	public static String unicode2String(String unicode) {
		String s2 ="";
		try {
		    byte[] converttoBytes = unicode.getBytes("UTF-8");
		    s2 = new String(converttoBytes, "UTF-8");
		    System.out.println(s2);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return s2;
	}
}
