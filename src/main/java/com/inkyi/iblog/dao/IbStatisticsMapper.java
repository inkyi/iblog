package com.inkyi.iblog.dao;

import com.inkyi.iblog.entity.IbStatistics;
import com.inkyi.iblog.entity.IbStatisticsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IbStatisticsMapper {
    int countByExample(IbStatisticsExample example);

    int deleteByExample(IbStatisticsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IbStatistics record);

    int insertSelective(IbStatistics record);

    List<IbStatistics> selectByExample(IbStatisticsExample example);

    IbStatistics selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IbStatistics record, @Param("example") IbStatisticsExample example);

    int updateByExample(@Param("record") IbStatistics record, @Param("example") IbStatisticsExample example);

    int updateByPrimaryKeySelective(IbStatistics record);

    int updateByPrimaryKey(IbStatistics record);
}