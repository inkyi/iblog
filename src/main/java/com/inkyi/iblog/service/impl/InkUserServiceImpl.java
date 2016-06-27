package com.inkyi.iblog.service.impl;

import java.util.List;

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

	@Override
	public boolean existsUsername(String username) {
		InkUserExample userExample = new InkUserExample();
		userExample.createCriteria().andUsernameEqualTo(username);
		List<InkUser> users = inkUserMapper.selectByExample(userExample);
		if(users.size() > 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public InkUser selectByUsername(String username) {
		InkUserExample userExample = new InkUserExample();
		userExample.createCriteria().andUsernameEqualTo(username);
		List<InkUser> users = inkUserMapper.selectByExample(userExample);
		if(users.size() > 0){
			return users.get(0);
		}
		return null;
	}

}
