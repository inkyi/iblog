package com.inkyi.iblog.contrl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class AjaxConroller extends BaseController{

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	protected final void writeJson(HttpServletResponse response, String json){
		response.setContentType("application/json;charset=UTF-8");
		write(response, json);
	}
	
	protected final void writeHtml(HttpServletResponse response, String str){
		response.setContentType("text/html;charset=UTF-8");
		write(response, str);
	}
	
	private void write(HttpServletResponse response, String str){
		PrintWriter pw;
		try {
			pw = response.getWriter();
			pw.write(str);
			pw.flush();
		} catch (IOException e) {
			logger.error("发生IO异常!");
		}
	}
	
	
}
