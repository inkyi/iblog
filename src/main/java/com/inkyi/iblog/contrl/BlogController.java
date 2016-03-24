package com.inkyi.iblog.contrl;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inkyi.iblog.service.IbArticleService;

/**
 * 
 * @author InkYi
 * @date 2016年3月24日 下午5:31:43
 *
 */
@Controller
@RequestMapping("blog")
public class BlogController {

	@Resource
	private IbArticleService ibArticleService;
	
	
	
	
	@RequestMapping("index")
	public String index(){
		
		
		
		return null;
	}
	
}
