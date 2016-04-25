package com.inkyi.iblog.contrl;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inkyi.iblog.entity.IbArticle;
import com.inkyi.iblog.service.IbArticleService;

/**
 * 
 * @author InkYi
 * @date 2016年3月24日 下午5:31:43
 *
 */
@Controller
@RequestMapping("blog")
public class BlogController extends BaseController {

	@Resource
	private IbArticleService ibArticleService;
	
	@RequestMapping("")
	public String index(){
		
		//博客列表
		//分类
		
		return "front/blog";
	}
	
	@RequestMapping("detail")
	public String detail(Integer aid, Model model){
		IbArticle article = ibArticleService.selectByPrimaryKey(aid);
		
		model.addAttribute("article", article);
		return "front/blog_detail";
	}
	
}
