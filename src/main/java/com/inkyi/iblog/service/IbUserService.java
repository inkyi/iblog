package com.inkyi.iblog.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.inkyi.iblog.entity.IbUser;
import com.inkyi.iblog.entity.IbUserExample;

public interface IbUserService {
	
	int countByExample(IbUserExample example);

    int deleteByExample(IbUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IbUser record);

    int insertSelective(IbUser record);

    List<IbUser> selectByExample(IbUserExample example);

    IbUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IbUser record, @Param("example") IbUserExample example);

    int updateByExample(@Param("record") IbUser record, @Param("example") IbUserExample example);

    int updateByPrimaryKeySelective(IbUser record);

    int updateByPrimaryKey(IbUser record);
	
}
