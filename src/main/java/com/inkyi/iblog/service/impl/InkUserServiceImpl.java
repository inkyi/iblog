package com.inkyi.iblog.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inkyi.iblog.dao.BaseDao;
import com.inkyi.iblog.dao.InkUserMapper;
import com.inkyi.iblog.entity.InkUser;
import com.inkyi.iblog.entity.InkUserExample;
import com.inkyi.iblog.service.InkUserService;
@Service
public class InkUserServiceImpl extends BaseServiceImpl<InkUser, InkUserExample> implements InkUserService {

	@Resource
	private InkUserMapper inkUserMapper;

	@Override
	public BaseDao<InkUser, InkUserExample> dao() {
		return this.inkUserMapper;
	}

}
