package com.inkyi.iblog.constants;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 爬虫：黄冈高级资料
 * 
 * @author InkYi
 *
 */
public class HungrySpider {

	/**
	 * 访问类 网址生成类 判断类 IO类
	 */

	public static String SendGet(String url) {
		// 定义一个字符串用来存储网页内容
		String result = "";
		// 定义一个缓冲字符输入流
		BufferedReader in = null;
		try {
			// 将string转成url对象
			URL realUrl = new URL(url);
			// 初始化一个链接到那个url的连接
			URLConnection connection = realUrl.openConnection();
			// 开始实际的连接
			connection.connect();
			// 初始化 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			// 用来临时存储抓取到的每一行的数据
			String line;
			while ((line = in.readLine()) != null) {
				// 遍历抓取到的每一行并将其存储到result里面
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	public static String RegexString(String targetStr, String patternStr) {
		// 定义一个样式模板，此中使用正则表达式，括号中是要抓的内容
		// 相当于埋好了陷阱匹配的地方就会掉下去
		Pattern pattern = Pattern.compile(patternStr);
		// 定义一个matcher用来做匹配
		Matcher matcher = pattern.matcher(targetStr);
		// 如果找到了
		if (matcher.find()) {
			// 打印出结果
			return matcher.group(1);
		}
		return "Nothing";
	}

	public static void main(String[] args) {
		// 定义即将访问的链接
		String url = "http://www.baidu.com";
		// 访问链接并获取页面内容
		String result = SendGet(url);
		// 使用正则匹配图片的src内容
		String imgSrc = RegexString(result, "src=\"(.+?)\"");
		// 打印结果
		System.out.println(imgSrc);
	}
	
	/**
	 * 1.生成一个随机网址 几位-从谁开始
	 * 2.访问网址 如果成功 下载页面
	 * 3.在页面上找出关键词，如果有，就放到文件中
	 * 
	 * 
	 * 
	 */

	public String getRandomURL(Integer size,Integer subscript){
		String[] mid ={"a","b","c","d","e","f","g","h","i","j","k","l",
						"m","n","o","p","q","r","s","t","u","v","w","x",
						"y","z","0","1","2","3","4","5","6","7","8","9"};
		String[] back = {"com","net","org","info"};
		StringBuffer sb = new StringBuffer("http://");
		
		
		return null;
	}

}
