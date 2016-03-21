package com.inkyi.iblog.dao;

import com.inkyi.iblog.entity.IbUserRole;
import com.inkyi.iblog.entity.IbUserRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IbUserRoleMapper {
    int countByExample(IbUserRoleExample example);

    int deleteByExample(IbUserRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IbUserRole record);

    int insertSelective(IbUserRole record);

    List<IbUserRole> selectByExample(IbUserRoleExample example);

    IbUserRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IbUserRole record, @Param("example") IbUserRoleExample example);

    int updateByExample(@Param("record") IbUserRole record, @Param("example") IbUserRoleExample example);

    int updateByPrimaryKeySelective(IbUserRole record);

    int updateByPrimaryKey(IbUserRole record);
}