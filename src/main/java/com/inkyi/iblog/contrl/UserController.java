package com.inkyi.iblog.contrl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inkyi.iblog.service.UserService;
import com.inkyi.iblog.user.UserDome;

/**
 * 
 * @author InkYi
 * @date 2016年3月16日 上午10:04:59
 *
 */
@Controller
public class UserController {

	private final static Logger logger = LoggerFactory.getLogger(UserDome.class);
	
	@Resource
	private UserService userService;
	
	@RequestMapping("user/get")
	public String getUser(Integer id){
		
		logger.info("logback 成功了");
        logger.error("logback 成功了");
        
        return "success";
	}
	
	
	
}
