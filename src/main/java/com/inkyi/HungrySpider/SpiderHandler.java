package com.inkyi.HungrySpider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.inkyi.common.util.RandomUtil;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class SpiderHandler implements PageProcessor {

	private static ExecutorService threadPool;

	public void buildThreadPool() {
		threadPool = Executors.newCachedThreadPool();
	}

	public String getWebSite(int size) {
		return RandomUtil.generateString(size);
	}

	public void setThreadPool() {
		threadPool.execute(new Runnable() {
			public void run() {
			}
		});
	}

	public static void crawling(String uri) {
		try {
			URL url = new URL(uri);// 创建资源类型
			String protocol = url.getProtocol();// 获取资源类型
			String host = url.getHost();// 获取域名
			int port = url.getPort();// 获取端口
			String file = url.getFile();// 获取路径
			System.out.println("url地址的资源类型为：" + protocol + "域名为：" + host + "端口为:" + port + "路径为:" + file);
			InputStream is = url.openStream();// 获取页面信息流
			BufferedReader bfr = new BufferedReader(new InputStreamReader(is));// 封装成字符流
			String len;
			while ((len = bfr.readLine()) != null) {
				System.out.println(len);
			}
			bfr.close();
			is.close();
			
			
			
			
		} catch (MalformedURLException e) {
			System.out.println("创建URL对象发生异常");
		} catch (IOException e) {
			System.out.println("发生IO操作异常");
		}
	}
	
	private Site site = Site.me().setRetryTimes(3).setSleepTime(100);
	
	@Override
	public void process(Page page) {
		page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
		page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
		page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
		if (page.getResultItems().get("name")==null){
			//skip this page
		    page.setSkip(true);
		}
		page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
		
	}

	@Override
	public Site getSite() {
		return site;
	}
	
	public static void main(String[] args) {
        Spider.create(new SpiderHandler()).addUrl("https://github.com/code4craft").thread(5).run();
    }
	

}
