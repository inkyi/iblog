package com.inkyi.HungrySpider;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.inkyi.util.BloomFilter;
import com.inkyi.util.RandomUtil;

public class SpiderHandler {

	private static final String WS_PREFIX = "http://www.";

	private static final String WS_SUFFIX = ".com";
	
	private static final String FILE_PATH = "D:\\website.txt";
	
	private static ExecutorService threadPool;

	private static BloomFilter<String> bloomFilter;

	private static int urlLength = 4;
	
	private static int loopSize = 2;

	
	public void init(){
		this.threadPool = Executors.newFixedThreadPool(20);
		this.bloomFilter = new BloomFilter<>(100000000, 8);
		this.urlLength = 6;
		this.loopSize = 10000000;
	}
	
	/**
	 * 创建线程池
	 * @author:InkYi
	 * @time:2016年8月26日 - 上午10:03:42
	 * @description:
	 */
	public void buildThreadPool(int size) {
		this.threadPool = Executors.newFixedThreadPool(size);
	}
	
	/**
	 * 创建过滤器
	 * @author:InkYi
	 * @time:2016年8月26日 - 上午10:07:27
	 * @description:
	 */
	public void buildBloomFilter(int m,int k) {
		this.bloomFilter = new BloomFilter<>(m, k);
	}
	
	/**
	 * 创建网址规则
	 * @author:InkYi
	 * @time:2016年8月26日 - 上午10:45:21
	 * @description:
	 * @param length
	 */
	public void setUrlLength(int length) {
		this.urlLength = length;
	}
	
	public void setSize(int size) {
		this.loopSize = size;
	}
	
	/**
	 * 开始
	 * @author:InkYi
	 * @throws IOException 
	 * @time:2016年8月26日 - 上午10:54:18
	 * @description:
	 */
	public void start() throws IOException{
		if(loopSize <=0 ){
			throw new IllegalArgumentException("size must be greater than 0");
		}
		if(urlLength <= 0){
			throw new IllegalArgumentException("length must be greater than 0");
		}
		for (int i = 0; i < loopSize; i++) {
			Runnable runnable = new Runnable() {
				public void run() {
					String webSite = getWebSite();
					if(bloomFilter.test(webSite)){
						return;
					}
					bloomFilter.add(webSite);
					String result = sendGet(webSite);
					if(result==null||result.isEmpty()){
						return;
					}
					boolean isFlag = regularLookup(result);
					if(isFlag){
						persist(FILE_PATH, webSite);
					}
				}
			};
			threadPool.execute(runnable);
		}
		
	}

	/**
	 * 生成网址
	 * @author:InkYi
	 * @time:2016年8月26日 - 上午10:03:30
	 * @description:
	 * @param size
	 * @return
	 */
	private String getWebSite() {
		String website = RandomUtil.generateString(urlLength);
		return WS_PREFIX + website.toLowerCase() + WS_SUFFIX;
	}

	/**
	 * 爬行
	 * @author:InkYi
	 * @time:2016年8月26日 - 上午10:02:57
	 * @description:
	 * @param uri
	 */
	public String sendGet(String url) {
		StringBuffer result = new StringBuffer();
		BufferedReader br = null;
		InputStream is = null;
		try {
			URL realUrl = new URL(url);
			URLConnection connection = realUrl.openConnection();
			connection.connect();
			is = connection.getInputStream();
			br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String line;
			while ((line = br.readLine()) != null) {
				result.append(line);
			}
		} catch (IOException e) {
			System.out.println("网址不存在！" + e);
		} finally {
			try {
				if(is!=null){
					is.close();
				}
				if(br!=null){
					br.close();
				}
			} catch (IOException e) {
				System.out.println("流关闭错误！");
			}
			
		}
		return result.toString();
	}

	/**
	 * 正则验证
	 * @author:InkYi
	 * @time:2016年8月26日 - 上午10:02:13
	 * @description:
	 * @param html
	 * @return
	 */
	private boolean regularLookup(String html) {
		Pattern p = Pattern.compile("另类视频");
		Matcher m = p.matcher(html);
		if(m.find()){
			System.out.println(m.group());
			return true;
		}
		return false;
	}

	/**
	 * 持久化
	 * @author:InkYi
	 * @time:2016年8月26日 - 上午10:02:01
	 * @description:
	 * @param path
	 * @param content
	 */
	private void persist(String path, CharSequence content) {
		try {
			FileWriter fileWriter = new FileWriter(path, true);
			fileWriter.append(content);
			fileWriter.append("\n");
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws IOException {
		SpiderHandler sh = new SpiderHandler();
		sh.buildBloomFilter(100000000,8);//过滤器
		sh.buildThreadPool(20);//创建线程
		sh.setUrlLength(6);//网址规则
		sh.setSize(10000);//执行次数
		sh.start();//开始
	}

}
