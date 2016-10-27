package com.inkyi.iblog.contrl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.inkyi.util.JsonUtil;

public class BaseController {
	
	
	
	/*@ModelAttribute
	private void setBasePath(HttpServletRequest request){
		if(request.getAttribute("basePath")==null){
			request.setAttribute("basePath", request.getContextPath());
		}
	}*/
	
	public boolean isLogin() {
		return false;
	}
	

	public String getRootURL(HttpServletRequest request){
		int port = request.getServerPort();
		String basePath = request.getScheme()+"://"+request.getServerName();
		if(port!=80){
			basePath=basePath+":"+port;
		}
		basePath += request.getContextPath()+"/";
		return basePath;
	}

	
	public HttpSession getSession(HttpServletRequest request){
		return request.getSession();
	}
	
	public String getBasePath(HttpServletRequest request){
		int port = request.getServerPort();
		String basePath = request.getScheme()+"://"+request.getServerName();
		if(port!=80){
			basePath=basePath+":"+port;
		}
		basePath += request.getContextPath();
		return basePath;
	}
	
	public String messageJson(boolean success,String errorMsg){
		Map<String,Object> result = new HashMap<>();
		result.put("success", success);
		result.put("errorMsg", errorMsg);
		return JsonUtil.Object2Json(result);
	}
	

}
