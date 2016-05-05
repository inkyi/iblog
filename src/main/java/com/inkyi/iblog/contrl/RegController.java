package com.inkyi.iblog.contrl;

import java.util.HashMap;
import java.util.Map;

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
public class RegController extends BaseController {

	@Resource
	private IbUserService ibUserService;
	
	
	@RequestMapping("")
	public String reg(){
		System.out.println("fasfdsafdsa");
		return "blog/register";
	}
	
	
	@RequestMapping("persist")
	public String persist(IbUser user){
		
		
		
		
		return null;
	}
	
	@RequestMapping("validate")
	public String validate(String code,IbUser user){
		Map data = new HashMap<String, Object>();
		//验证码
		//用户名
		//邮箱
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
