package com.inkyi.iblog.dao;

import com.inkyi.iblog.entity.IbRoleAuth;
import com.inkyi.iblog.entity.IbRoleAuthExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IbRoleAuthMapper {
    int countByExample(IbRoleAuthExample example);

    int deleteByExample(IbRoleAuthExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IbRoleAuth record);

    int insertSelective(IbRoleAuth record);

    List<IbRoleAuth> selectByExample(IbRoleAuthExample example);

    IbRoleAuth selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IbRoleAuth record, @Param("example") IbRoleAuthExample example);

    int updateByExample(@Param("record") IbRoleAuth record, @Param("example") IbRoleAuthExample example);

    int updateByPrimaryKeySelective(IbRoleAuth record);

    int updateByPrimaryKey(IbRoleAuth record);
}