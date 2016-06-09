package com.inkyi.iblog.contrl;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inkyi.iblog.service.InkUserService;
import com.inkyi.redis.service.RedisService;

/**
 * 
 * @ClassName: IbUserConroller 
 * @Description: TODO(用户) 
 * @author InkYi
 * @date 2016年3月20日 上午11:49:17 
 *
 */
@Controller
@RequestMapping("login")
public class LoginrController extends BaseController {
	
	@Resource
	private InkUserService inkUserService;
	
	@Resource
	private RedisService redisServcie;
	
	
	@RequestMapping("")
	public String login(){
		return "blog/login";
	}
	
	
	


	
}
