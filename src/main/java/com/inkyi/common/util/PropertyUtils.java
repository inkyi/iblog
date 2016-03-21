package com.inkyi.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @desc 读取配置文件，并提取相应的键值
 * @author Jcrazy
 * @date 2014-12-4
 * @time 下午2:53:43
 * @version 1.0
 */
public class PropertyUtils {
	
	
	/**
	 * 根据key获取键值
	 * @return
	 */
	public static String getValue(String key, String filepath) {
		InputStream in = PropertyUtils.class.getClassLoader()
				.getResourceAsStream(filepath);
		Properties p = new Properties();
		try {
			p.load(in);
			in.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return p.getProperty(key);
	}
	
	/**
	 * 根据文件路径获取属性对象
	 * @param filepath
	 * @return
	 */
	public static Properties getPropertyFile(String filepath){
		InputStream in = PropertyUtils.class.getClassLoader()
				.getResourceAsStream(filepath);
		Properties p = new Properties();
		try {
			p.load(in);
			in.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return p;
	}
	
	public static Object getProperty(String key){
		Properties p = PropertyUtils.getPropertyFile("properties/config.properties");
		return p.get(key);
	}
	
	
}
