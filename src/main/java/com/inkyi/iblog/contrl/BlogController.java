package com.inkyi.iblog.contrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author InkYi
 * @date 2016年3月24日 下午5:31:43
 *
 */
@Controller
@RequestMapping("blog")
public class BlogController extends BaseController {

	
	
	@RequestMapping("")
	public String index(){
		return "front/blog";
	}
	
	
	
}
