package com.inkyi.iblog.service;

import com.inkyi.iblog.entity.UserText;

/**
 * 
 * @author InkYi
 * @date 2016年3月15日 下午5:38:03
 *
 */
public interface UserService {

	public UserText selectByPrimaryKey(Integer id);
	
	public void addOne(UserText user);
	
}
