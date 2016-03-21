package com.inkyi.iblog.dao;

import com.inkyi.iblog.entity.IbAuth;
import com.inkyi.iblog.entity.IbAuthExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IbAuthMapper {
    int countByExample(IbAuthExample example);

    int deleteByExample(IbAuthExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IbAuth record);

    int insertSelective(IbAuth record);

    List<IbAuth> selectByExample(IbAuthExample example);

    IbAuth selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IbAuth record, @Param("example") IbAuthExample example);

    int updateByExample(@Param("record") IbAuth record, @Param("example") IbAuthExample example);

    int updateByPrimaryKeySelective(IbAuth record);

    int updateByPrimaryKey(IbAuth record);
}