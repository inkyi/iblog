package com.inkyi.common.util;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * 描述：获取各种Gson的工具类<br/>
 * 作者：data-zrb <br/>
 * 修改日期：2015年10月29日 - 上午11:50:41<br/>
 *
 */
public class GsonUtil {

	/**
	 * @author: data-zrb
	 * 修改时间：2015年10月29日 - 上午11:51:04<br/>
	 * 功能说明：使用@Expose注解<br/>
	 * @return
	 */
	public static final Gson getGsonWithAnnotation(){
		return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	}
	
	/**
	 * @author: data-zrb
	 * 修改时间：2015年10月29日 - 上午11:51:58<br/>
	 * 功能说明：序列化时使用指定日期格式的Gson<br/>
	 * @param formater
	 * @return
	 */
	public static final Gson getGsonWithDateSerializer(String formater){
		return new GsonBuilder().registerTypeAdapter(Date.class,new JsonSerializer<Date>() {
			@Override
			public JsonElement serialize(Date src,Type typeOfSrc, JsonSerializationContext context){
				SimpleDateFormat format = new SimpleDateFormat(formater);
				return new JsonPrimitive(format.format(src));
			}
		}).create();
	}
	
	/**
	 * @author: data-zrb
	 * 修改时间：2015年10月29日 - 上午11:51:58<br/>
	 * 功能说明：序列化时使用long的Gson<br/>
	 * @param formater
	 * @return
	 */
	public static final Gson getGsonWithDateSerializer(){
		return new GsonBuilder().registerTypeAdapter(Date.class,new JsonSerializer<Date>() {
			@Override
			public JsonElement serialize(Date src,Type typeOfSrc, JsonSerializationContext context){
				return new JsonPrimitive(src.getTime());
			}
		}).create();
	}
	
	/**
	 * @author: data-zrb
	 * 修改时间：2015年10月29日 - 上午11:51:58<br/>
	 * 功能说明：反序列化时使用指定日期格式的Gson<br/>
	 * @param formater
	 * @return
	 */
	public static final Gson getGsonWithDateDeserializer(String formater){
		return new GsonBuilder().registerTypeAdapter(Date.class,new JsonDeserializer<Date>() {
			@Override
			public Date deserialize(JsonElement json,Type typeOfT,JsonDeserializationContext context) throws JsonParseException{
				SimpleDateFormat format = new SimpleDateFormat(formater);
				try{
					return format.parse(json.getAsString());
				} catch (ParseException e){
					e.printStackTrace();
				}
				return null;
			}
		}).create();
	}
	
	/**
	 * @author: data-zrb
	 * 修改时间：2015年10月29日 - 上午11:51:58<br/>
	 * 功能说明：反序列化时使用long格式的Gson<br/>
	 * @param formater
	 * @return
	 */
	public static final Gson getGsonWithDateDeserializer(){
		return new GsonBuilder().registerTypeAdapter(Date.class,new JsonDeserializer<Date>() {
			@Override
			public Date deserialize(JsonElement json,Type typeOfT,JsonDeserializationContext context) throws JsonParseException{
				return new Date(json.getAsJsonPrimitive().getAsLong());
			}
		}).create();
	}
}
