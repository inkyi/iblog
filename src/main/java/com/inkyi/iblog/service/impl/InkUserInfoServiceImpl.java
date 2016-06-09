package com.inkyi.iblog.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inkyi.iblog.dao.BaseDao;
import com.inkyi.iblog.dao.InkUserInfoMapper;
import com.inkyi.iblog.entity.InkUserInfo;
import com.inkyi.iblog.entity.InkUserInfoExample;
import com.inkyi.iblog.service.InkUserInfoService;
@Service
public class InkUserInfoServiceImpl extends BaseServiceImpl<InkUserInfo, InkUserInfoExample> implements InkUserInfoService {

	@Resource
	private InkUserInfoMapper inkUserInfoMapper;

	@Override
	public BaseDao<InkUserInfo, InkUserInfoExample> dao() {
		return this.inkUserInfoMapper;
	}

}
