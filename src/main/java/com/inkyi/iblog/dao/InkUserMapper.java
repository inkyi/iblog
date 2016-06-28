package com.inkyi.iblog.dao;

import com.inkyi.iblog.entity.InkUser;
import com.inkyi.iblog.entity.InkUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InkUserMapper extends BaseDao<InkUser, InkUserExample>{
    int countByExample(InkUserExample example);

    int deleteByExample(InkUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(InkUser record);

    int insertSelective(InkUser record);

    List<InkUser> selectByExample(InkUserExample example);

    InkUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") InkUser record, @Param("example") InkUserExample example);

    int updateByExample(@Param("record") InkUser record, @Param("example") InkUserExample example);

    int updateByPrimaryKeySelective(InkUser record);

    int updateByPrimaryKey(InkUser record);
}