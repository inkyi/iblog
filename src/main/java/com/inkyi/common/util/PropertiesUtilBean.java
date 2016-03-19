package com.inkyi.common.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

public class PropertiesUtilBean implements InitializingBean {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private String[] baseNames;
	
	private Properties configs;
	
	private static boolean isInited=false;
	
	private static Map<String, String> configurations = new HashMap<String, String>();
	
	public void init(){
		logger.info("配置文件内存化Bean【propertiesUtilBean】成功>>>>>>");
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		if(null!=baseNames){
			for(String path : baseNames){
				if(StringUtils.isEmpty(path)){
					continue;
				}
				ResourceBundle rb = ResourceBundle.getBundle(path);
				Set<String> keyset = rb.keySet();
				for(String key : keyset){
					configurations.put(key, rb.getString(key));
				}
				rb = null;
			}
		}
		if(null!=configs){
			configs.entrySet().forEach(p -> {
				configurations.put(p.getKey().toString(), p.getValue().toString());
			});
		}
		isInited = true;
	}

	public void setBaseNames(String[] baseNames) {
		this.baseNames = baseNames;
	}
	
	public static class PropertiesUtils{
		
		private PropertiesUtils(){}
		
		/**
		 * @author: data-zrb
		 * 修改时间：2015年9月17日 - 下午9:09:08<br/>
		 * 功能说明：如果可以访问返回true<br/>
		 * @return
		 */
		public static boolean canUse(){
			return isInited;
		}
		
		public static Map<String, String> getAllProperties(){
			return configurations;
		}
		
		public static String getConfig(String key){
			return configurations.get(key);
		}
	}
}
