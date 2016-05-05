package com.inkyi.iblog.contrl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("index")
public class IndexController extends BaseController {

	/**
	 * 
	 * @Title: index 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	@RequestMapping("")
	public String index(Model model,HttpServletRequest request){
		
		return "blog/index";
	}
	
	
	/**
	 * 网站标题
	 * 网站简介
	 * 
	 * 菜单
	 * 
	 * bannner
	 * 
	 * 最近发布 8
	 * 
	 * 热门文章 6
	 * 
	 * 最近留言 5
	 */
	
	
}
