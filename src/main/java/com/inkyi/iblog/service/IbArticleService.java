package com.inkyi.iblog.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.inkyi.iblog.entity.IbArticle;
import com.inkyi.iblog.entity.IbArticleExample;


public interface IbArticleService {
	int countByExample(IbArticleExample example);

    int deleteByExample(IbArticleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IbArticle record);

    int insertSelective(IbArticle record);

    List<IbArticle> selectByExampleWithBLOBs(IbArticleExample example);

    List<IbArticle> selectByExample(IbArticleExample example);

    IbArticle selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IbArticle record, @Param("example") IbArticleExample example);

    int updateByExampleWithBLOBs(@Param("record") IbArticle record, @Param("example") IbArticleExample example);

    int updateByExample(@Param("record") IbArticle record, @Param("example") IbArticleExample example);

    int updateByPrimaryKeySelective(IbArticle record);

    int updateByPrimaryKeyWithBLOBs(IbArticle record);

    int updateByPrimaryKey(IbArticle record);
}