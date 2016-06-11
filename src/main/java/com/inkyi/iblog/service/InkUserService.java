package com.inkyi.iblog.service;

import com.inkyi.iblog.entity.InkUser;
import com.inkyi.iblog.entity.InkUserExample;

public interface InkUserService extends BaseService<InkUser, InkUserExample> {

	/**
	 * 检测用户名 
	 * false-重复 true-不重复
	 * @Title: checkUserName 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param username
	 * @param @return    设定文件 
	 * @return boolean    返回类型 
	 * @throws
	 */
	boolean checkUserName(String username);

}
