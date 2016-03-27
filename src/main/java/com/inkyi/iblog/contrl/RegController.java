package com.inkyi.iblog.contrl;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inkyi.iblog.entity.IbUser;
import com.inkyi.iblog.service.IbUserService;

/**
 * 注册
 * @ClassName: RegController 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author InkYi
 * @date 2016年3月26日 下午7:15:02 
 *
 */
@Controller
@RequestMapping("reg")
public class RegController {

	@Resource
	private IbUserService ibUserService;
	
	
	@RequestMapping("reg")
	public String reg(){
		return null;
	}
	
	
	@RequestMapping("persist")
	public String persist(IbUser user){
		
		
		
		
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
