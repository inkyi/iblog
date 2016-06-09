package com.inkyi.iblog.dao;

import com.inkyi.iblog.entity.InkUserInfo;
import com.inkyi.iblog.entity.InkUserInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InkUserInfoMapper extends BaseDao<InkUserInfo, InkUserInfoExample> {
    int countByExample(InkUserInfoExample example);

    int deleteByExample(InkUserInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(InkUserInfo record);

    int insertSelective(InkUserInfo record);

    List<InkUserInfo> selectByExample(InkUserInfoExample example);

    InkUserInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") InkUserInfo record, @Param("example") InkUserInfoExample example);

    int updateByExample(@Param("record") InkUserInfo record, @Param("example") InkUserInfoExample example);

    int updateByPrimaryKeySelective(InkUserInfo record);

    int updateByPrimaryKey(InkUserInfo record);
}