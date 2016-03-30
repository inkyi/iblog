package com.inkyi.iblog.contrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;

public class BaseController {
	
	protected HttpSession getSession(HttpServletRequest request){
		return request.getSession();
	}
	
	@ModelAttribute
	private void setBasePath(HttpServletRequest request){
		if(request.getAttribute("basePath")==null){
			request.setAttribute("basePath", request.getContextPath());
		}
	}

}
