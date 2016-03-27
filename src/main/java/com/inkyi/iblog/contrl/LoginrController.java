package com.inkyi.iblog.contrl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inkyi.common.util.GsonUtil;
import com.inkyi.common.util.Md5Encrypt;
import com.inkyi.iblog.entity.IbUser;
import com.inkyi.iblog.entity.IbUserExample;
import com.inkyi.iblog.service.IbUserService;
import com.inkyi.iblog.vo.LoginVO;

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
public class LoginrController {

	@Resource
	private IbUserService ibUserService;
	
	@RequestMapping("")
	public String login(){
		return null;
	}
	
	@RequestMapping("validLogin")
	public @ResponseBody String validLogin(LoginVO bean,Model model){
		
		Map<String,Object> status =  new HashMap<>();
		
		String verifyCode = bean.getVerifyCode();
		String username = bean.getUsername();
		String password = bean.getPassword();
		String md5pwd = Md5Encrypt.md5(password);
		
		//验证码
		if(verifyCode == null){
			status.put("code", 100);
			status.put("msg", "验证码错误");
			return GsonUtil.toJson(status);
		}
		
		//用户
		IbUserExample ibExample = new IbUserExample();
		ibExample.createCriteria().andUsernameEqualTo(username);
		List<IbUser> ibUsers = ibUserService.selectByExample(ibExample);
		if(ibUsers.size() > 0){
			IbUser ibUser = ibUsers.get(0);
			if(ibUser.getPassword().equals(md5pwd)){
				status.put("code", 200);
				status.put("msg", "登陆成功");
			}else{
				status.put("code", 101);
				status.put("msg", "密码错误");
			}
		}else{
			status.put("code", 102);
			status.put("msg", "用户不存在");
		}
		return GsonUtil.toJson(status);
	}

	
}
