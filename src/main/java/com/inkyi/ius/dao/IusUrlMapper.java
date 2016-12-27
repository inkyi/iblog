package com.inkyi.ius.dao;

import com.inkyi.ius.entity.IusUrl;
import com.inkyi.ius.entity.IusUrlExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IusUrlMapper {
    long countByExample(IusUrlExample example);

    int deleteByExample(IusUrlExample example);

    int deleteByPrimaryKey(Long id);

    int insert(IusUrl record);

    int insertSelective(IusUrl record);

    List<IusUrl> selectByExample(IusUrlExample example);

    IusUrl selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") IusUrl record, @Param("example") IusUrlExample example);

    int updateByExample(@Param("record") IusUrl record, @Param("example") IusUrlExample example);

    int updateByPrimaryKeySelective(IusUrl record);

    int updateByPrimaryKey(IusUrl record);
}