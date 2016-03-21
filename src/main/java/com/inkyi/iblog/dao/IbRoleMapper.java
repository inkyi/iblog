package com.inkyi.iblog.dao;

import com.inkyi.iblog.entity.IbRole;
import com.inkyi.iblog.entity.IbRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IbRoleMapper {
    int countByExample(IbRoleExample example);

    int deleteByExample(IbRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IbRole record);

    int insertSelective(IbRole record);

    List<IbRole> selectByExample(IbRoleExample example);

    IbRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IbRole record, @Param("example") IbRoleExample example);

    int updateByExample(@Param("record") IbRole record, @Param("example") IbRoleExample example);

    int updateByPrimaryKeySelective(IbRole record);

    int updateByPrimaryKey(IbRole record);
}