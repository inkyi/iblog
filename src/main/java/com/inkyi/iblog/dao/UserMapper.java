package com.inkyi.iblog.dao;

import com.inkyi.iblog.entity.UserText;

/**
 * 
 * @author InkYi
 * @date 2016年3月15日 下午3:36:29
 *
 */
public interface UserMapper {

	public UserText selectByPrimaryKey(Integer id);

	public void addOne(UserText user);
	
}
