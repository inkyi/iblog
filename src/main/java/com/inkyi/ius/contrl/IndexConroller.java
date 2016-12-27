package com.inkyi.ius.contrl;

import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.Bag;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inkyi.ius.service.IusUrlService;
import com.inkyi.util.JsonUtil;
import com.sun.jmx.remote.internal.ArrayQueue;

/**
 * 短网址生成器
 * @author InkYi
 */
@Controller
@RequestMapping("ius")
public class IndexConroller {
	
	@Resource
	private IusUrlService iusUrlsService;
	
	@RequestMapping(value = "/t/{code}")
	public void index(@PathVariable String code,HttpServletRequest req, HttpServletResponse res) throws Exception {
		String location = iusUrlsService.restore(code);
		if(location!=null && location.startsWith("/")){
			int port = req.getServerPort();
			String basePath = req.getScheme()+"://"+req.getServerName();
			if(port!=80){
				basePath=basePath+":"+port;
			}
			basePath += req.getContextPath();
			res.sendRedirect(basePath+location);
		}else{
			res.sendRedirect(location);
		}
	}
	
	@RequestMapping("index")
	public String index(){
		
		return null;
	}
	
	/**
	 * 制造
	 * @author:InkYi
	 * @time:2016年12月5日 - 下午2:51:19
	 * @description:
	 * @param urls
	 * @return
	 */
	@RequestMapping("produce")
	public @ResponseBody String produce(String url){
		if(StringUtils.isEmpty(url)){
			//空的
		}
		Map<String,Object> data = new HashMap<>();
		String code = iusUrlsService.produce(url);
		if(StringUtils.isEmpty(code)){
			data.put("success", false);
			data.put("msg","转换失败");
		}else{
			data.put("success", true);
			data.put("code", code);
			data.put("msg","转换成功");
		}
		return JsonUtil.Object2Json(data);
	}
	/**
	 * 还原
	 * @author:InkYi
	 * @time:2016年12月5日 - 下午2:51:52
	 * @description:
	 * @param urls
	 * @return
	 */
	@RequestMapping("restore")
	public @ResponseBody String restore(String urls){
		if(StringUtils.isEmpty(urls)){
			//空的
		}
		Map<String,Object> data = new HashMap<>();
		String url = iusUrlsService.restore(urls);
		if(StringUtils.isEmpty(url)){
			data.put("success", false);
			data.put("msg","还原失败");
		}else{
			data.put("success", true);
			data.put("url", url);
			data.put("msg","还原成功");
		}
		return JsonUtil.Object2Json(data);
	}
	/**
	 * 定制
	 * @author:InkYi
	 * @time:2016年12月5日 - 下午2:51:59
	 * @description:
	 * @param urls
	 * @return
	 */
	@RequestMapping("custom")
	public @ResponseBody String custom(String url,String code){
		if(StringUtils.isEmpty(code)){
			//空的
		}
		Map<String,Object> data = new HashMap<>();
		boolean flag = iusUrlsService.custom(url,code);
		data.put("success", flag);
		return JsonUtil.Object2Json(data);
	}
	
}
