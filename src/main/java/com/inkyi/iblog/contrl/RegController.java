package com.inkyi.iblog.contrl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inkyi.common.util.IPUtils;
import com.inkyi.iblog.constants.RedisKey;
import com.inkyi.iblog.service.GlobalConfigService;
import com.inkyi.iblog.service.InkUserService;
import com.inkyi.iblog.vo.RegVo;
import com.inkyi.redis.service.RedisService;

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
	private InkUserService inkUserService;
	
	@Resource
	private RedisService redisServcie;
	
	@Resource
	private GlobalConfigService globalConfigService;
	
	@RequestMapping("")
	public String reg(Model model,HttpServletRequest request){
		
		String regipKey = String.format(RedisKey.USER_REG_IP, IPUtils.getRemortIP(request));
		String regipValue = redisServcie.get(regipKey);
		if(!StringUtils.isBlank(regipValue)){
			model.addAttribute("msg", "1个IP一小时只能注册一次");
			model.addAttribute("isReg", 0);
			model.addAttribute("redirect", "index");
			return "blog/messsage";
		}
		return "blog/register";
	}
	
	/**
	 * 添加用户
	 * @Title: PersistUser 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param inkUser
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	@RequestMapping("persistUser")
	public String PersistUser(RegVo regVo,Model model,HttpServletRequest request){

		/*Integer userid = inkUser.getId();
		if(userid!=null){
			inkUserService.addOne(inkUser);
		}else{
			inkUserService.updateById(inkUser);
		}*/
		
		//1个IP 1小时只能注册一个帐号
		String regipKey = "iblog:user:reg:ip:" + IPUtils.getRemortIP(request);
		redisServcie.set(regipKey, "1");
		redisServcie.expire(regipKey, (long) (60*60*1));
		
		model.addAttribute("msg", "注册成功");
		model.addAttribute("redirect", "home");
		return "blog/messsage";
	}
	
	/**
	 * 验证验证码
	 * @Title: Verification 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param vCode
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	public @ResponseBody String Verification(String vCode){
		
		
		
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
