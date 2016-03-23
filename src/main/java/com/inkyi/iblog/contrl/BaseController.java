package com.inkyi.iblog.contrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class BaseController {

	protected String templatePath = "cpts/";
	
	protected HttpSession getSession(HttpServletRequest request){
		return request.getSession();
	}
	
}
