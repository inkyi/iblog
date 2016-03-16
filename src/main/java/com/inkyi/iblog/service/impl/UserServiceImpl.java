package com.inkyi.iblog.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inkyi.iblog.dao.UserMapper;
import com.inkyi.iblog.entity.UserText;
import com.inkyi.iblog.service.UserService;

/**
 * 
 * @author InkYi
 * @date 2016年3月15日 下午5:38:29
 *
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;

	public UserText selectByPrimaryKey(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}

	public void addOne(UserText user) {
		userMapper.addOne(user);
	}
	
}
