package com.inkyi.iblog.dao;

import com.inkyi.iblog.entity.IbUserInfo;
import com.inkyi.iblog.entity.IbUserInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IbUserInfoMapper {
    int countByExample(IbUserInfoExample example);

    int deleteByExample(IbUserInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IbUserInfo record);

    int insertSelective(IbUserInfo record);

    List<IbUserInfo> selectByExample(IbUserInfoExample example);

    IbUserInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IbUserInfo record, @Param("example") IbUserInfoExample example);

    int updateByExample(@Param("record") IbUserInfo record, @Param("example") IbUserInfoExample example);

    int updateByPrimaryKeySelective(IbUserInfo record);

    int updateByPrimaryKey(IbUserInfo record);
}