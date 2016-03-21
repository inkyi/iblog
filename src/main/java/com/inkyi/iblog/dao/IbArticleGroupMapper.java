package com.inkyi.iblog.dao;

import com.inkyi.iblog.entity.IbArticleGroup;
import com.inkyi.iblog.entity.IbArticleGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IbArticleGroupMapper {
    int countByExample(IbArticleGroupExample example);

    int deleteByExample(IbArticleGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IbArticleGroup record);

    int insertSelective(IbArticleGroup record);

    List<IbArticleGroup> selectByExample(IbArticleGroupExample example);

    IbArticleGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IbArticleGroup record, @Param("example") IbArticleGroupExample example);

    int updateByExample(@Param("record") IbArticleGroup record, @Param("example") IbArticleGroupExample example);

    int updateByPrimaryKeySelective(IbArticleGroup record);

    int updateByPrimaryKey(IbArticleGroup record);
}