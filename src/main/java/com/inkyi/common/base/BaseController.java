package com.inkyi.common.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class BaseController {

	protected HttpSession getSession(HttpServletRequest request){
		return request.getSession();
	}
	
}
