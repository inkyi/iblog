package com.inkyi.common.convertion;

import java.text.SimpleDateFormat;
import java.util.Date;


import org.springframework.core.convert.converter.Converter;

/**
 * 自定义日期类型转换    页面--》日期   --》 后台JavaBean  Date
 * String -->Date 
 * @author lx
 *
 */
public class CustomDateConverter implements Converter<String, Date> {

	@Override
	public Date convert(String source) {
		// TODO Auto-generated method stub  
		// null "" "  "  2015-05-03 12:12:12
		try {
			//判断NUll
			if(null != source){
				return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(source);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
