package com.inkyi.iblog.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.inkyi.iblog.dao.IbUserMapper;
import com.inkyi.iblog.entity.IbUser;
import com.inkyi.iblog.entity.IbUserExample;
import com.inkyi.iblog.service.IbUserService;

public class IbUserServiceImpl implements IbUserService {

	@Resource
	private IbUserMapper mapper;
	
	@Override
	public int countByExample(IbUserExample example) {
		return mapper.countByExample(example);
	}

	@Override
	public int deleteByExample(IbUserExample example) {
		return mapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(IbUser record) {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(IbUser record) {
		return mapper.insertSelective(record);
	}

	@Override
	public List<IbUser> selectByExample(IbUserExample example) {
		return mapper.selectByExample(example);
	}

	@Override
	public IbUser selectByPrimaryKey(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByExampleSelective(IbUser record, IbUserExample example) {
		return mapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(IbUser record, IbUserExample example) {
		return mapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(IbUser record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(IbUser record) {
		return mapper.updateByPrimaryKey(record);
	}

}
