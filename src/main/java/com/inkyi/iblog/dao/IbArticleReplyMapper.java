package com.inkyi.iblog.dao;

import com.inkyi.iblog.entity.IbArticleReply;
import com.inkyi.iblog.entity.IbArticleReplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IbArticleReplyMapper {
    int countByExample(IbArticleReplyExample example);

    int deleteByExample(IbArticleReplyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IbArticleReply record);

    int insertSelective(IbArticleReply record);

    List<IbArticleReply> selectByExample(IbArticleReplyExample example);

    IbArticleReply selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IbArticleReply record, @Param("example") IbArticleReplyExample example);

    int updateByExample(@Param("record") IbArticleReply record, @Param("example") IbArticleReplyExample example);

    int updateByPrimaryKeySelective(IbArticleReply record);

    int updateByPrimaryKey(IbArticleReply record);
}