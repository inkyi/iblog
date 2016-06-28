package com.inkyi.iblog.contrl;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.NestedRuntimeException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inkyi.common.util.JsonUtil;
import com.inkyi.common.util.Md5Encrypt;
import com.inkyi.iblog.constants.RedisKey;
import com.inkyi.iblog.entity.InkUser;
import com.inkyi.iblog.service.InkUserService;
import com.inkyi.iblog.vo.LoginVo;
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
@RequestMapping("")
public class LoginrController extends BaseController {
	
	@Resource
	private InkUserService inkUserService;
	
	@Resource
	private RedisService redisServcie;
	
	/**
	 * 登录页面
	 * @author:InkYi
	 * @time:2016年6月20日 - 下午7:56:29
	 * @description:
	 * @return
	 */
	@RequestMapping("login")
	public String login(){
		return "blog/login";
	}
	
	
	/**
	 * 验证账户
	 * @author:InkYi
	 * @time:2016年6月20日 - 下午7:56:37
	 * @description:
	 * @return
	 */
	@RequestMapping(value="vAccount", produces = "text/html;charset=UTF-8")
	public @ResponseBody String verifyAccount(String loginData, HttpServletRequest request){
		LoginVo login = null;
		Map<String,Object> result = new HashMap<>();
		if(!StringUtils.isBlank(loginData)){
			login = (LoginVo) JsonUtil.str2bean(loginData, new LoginVo().getClass());
		}else{
			return messageJson(false,"数据错误");
		}
		
		if(login==null){
			return messageJson(false,"数据错误");
		}
		/*//验证码
		String verifyCode = login.getVerifyCode();
		if(StringUtils.isBlank(verifyCode)){
			HttpSession session = getSession(request);
			Object captcha = session.getAttribute(SystemKey.USER_LOGIN_CAPTCHA);
			if(null==captcha){
				return JacksonUtil.Object2Json(JsonResult.messageJson(false,"非法的验证码"));
			}
			if(!captcha.toString().equalsIgnoreCase(verifyCode)){
				return JacksonUtil.Object2Json(JsonResult.messageJson(false,"验证码错误"));
			}
		}else{
			return JacksonUtil.Object2Json(JsonResult.messageJson(false,"非法的验证码"));
		}*/
		//用户
		String username = login.getUsername();
		InkUser iuser =  inkUserService.selectByUsername(username);
		if(iuser==null){
			return messageJson(false,"用户名不存在");
		}
		String upw = iuser.getPassword();
		String lpw = login.getPassword();
		if(!upw.equals(Md5Encrypt.md5(lpw))){
			return messageJson(false,"密码错误");
		}
		//inkUserService.setUserForCache(iuser);
		return messageJson(true,"登录成功");
	}
	
	@RequestMapping("logout")
	public String logout(HttpServletRequest request,HttpSession session){
		session.removeAttribute(null);
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "login";
	}

	@RequestMapping("error")
	public String error(Model model){
		model.addAttribute("exceptionMessage", "用户无权访问");
		return "error";
	}
	
	@RequestMapping("anno/error")
	public String requestDeny(Model model){
		model.addAttribute("msg", "用户无权访问");
		model.addAttribute("redirect", "index");
		return "message";
	}
	
	@RequestMapping("anno/fail")
	public String requestFail(Model model){
		model.addAttribute("msg", "用户无权操作");
		model.addAttribute("redirect", "sysuser/list");
		return "message";
	}
}
