package com.inkyi.iblog.dao;

import com.inkyi.iblog.entity.IbMenu;
import com.inkyi.iblog.entity.IbMenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IbMenuMapper {
    int countByExample(IbMenuExample example);

    int deleteByExample(IbMenuExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IbMenu record);

    int insertSelective(IbMenu record);

    List<IbMenu> selectByExample(IbMenuExample example);

    IbMenu selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IbMenu record, @Param("example") IbMenuExample example);

    int updateByExample(@Param("record") IbMenu record, @Param("example") IbMenuExample example);

    int updateByPrimaryKeySelective(IbMenu record);

    int updateByPrimaryKey(IbMenu record);
}