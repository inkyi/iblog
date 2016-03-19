package com.inkyi.common.convertion;

import org.springframework.core.convert.converter.Converter;

/**
 * 参数转换绑定   “”  “  ”  去掉前后空格
 * @author lx
 *
 */
public class CustomTrimConverter implements Converter<String, String> {

	@Override
	public String convert(String source) {
		// TODO Auto-generated method stub
		try {
			//
			if(null != source){
				source = source.trim();
				//判断 不是 ""
				if(!"".equals(source)){
					return source;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
