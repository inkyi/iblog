package com.inkyi.iblog.user;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.inkyi.iblog.entity.UserText;
import com.inkyi.iblog.junit.BaseJunitTest;
import com.inkyi.iblog.service.UserService;

/**
 * 
 * @author InkYi
 * @date 2016年3月15日 下午3:27:58
 *
 */

public class UserJunitTest extends BaseJunitTest {
	
	private final static Logger logger = LoggerFactory.getLogger(UserJunitTest.class);
	
	@Resource
	private UserService userService;
	
	@Test
	public void selectUser(){
		UserText selectByPrimaryKey = userService.selectByPrimaryKey(1);
		UserText ut  = new UserText();
		ut.setId(9);
		ut.setName("4546546");
		logger.info("logback 成功了");
        logger.debug("user-info",selectByPrimaryKey);
        logger.error("logback 成功了");
		//userService.addOne(ut);
		
	}
	
}
