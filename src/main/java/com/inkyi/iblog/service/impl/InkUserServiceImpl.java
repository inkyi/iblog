package com.inkyi.iblog.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inkyi.iblog.constants.RedisKey;
import com.inkyi.iblog.dao.BaseDao;
import com.inkyi.iblog.dao.InkUserMapper;
import com.inkyi.iblog.entity.InkUser;
import com.inkyi.iblog.entity.InkUserExample;
import com.inkyi.iblog.exception.InkUserRuntimeException;
import com.inkyi.iblog.service.InkUserService;
import com.inkyi.redis.service.RedisService;
import com.inkyi.util.JsonUtil;
@Service
public class InkUserServiceImpl extends BaseServiceImpl<InkUser, InkUserExample> implements InkUserService {

	@Resource
	private InkUserMapper inkUserMapper;
	
	@Resource
	private RedisService redisServcie;

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

	@Override
	public InkUser getUserForCache(Long id) {
		if(id == null){
			throw new InkUserRuntimeException("从缓存中获取用户时，用户ID不能为空");
		}
		String userKey = String.format(RedisKey.USER, id);
		String userValue = redisServcie.get(userKey);
		InkUser user = (InkUser) JsonUtil.str2bean(userValue, new InkUser().getClass());
		return  user;
	}

	@Override
	public boolean setUserForCache(InkUser user) {
		if(user == null){
			throw new InkUserRuntimeException("将用户存入缓存服务器时，用户不能为空");
		}
		Long id = user.getId();
		if(id==null){
			throw new InkUserRuntimeException("将用户存入缓存服务器时，用户ID不能为空");
		}
		String userValue = JsonUtil.Object2Json(user);
		String userKey = String.format(RedisKey.USER, id);
		redisServcie.set(userKey, userValue);
		redisServcie.expire(userKey, (long) (30*60));
		return true;
	}

}
