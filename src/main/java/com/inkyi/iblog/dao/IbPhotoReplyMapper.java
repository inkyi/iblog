package com.inkyi.iblog.dao;

import com.inkyi.iblog.entity.IbPhotoReply;
import com.inkyi.iblog.entity.IbPhotoReplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IbPhotoReplyMapper {
    int countByExample(IbPhotoReplyExample example);

    int deleteByExample(IbPhotoReplyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IbPhotoReply record);

    int insertSelective(IbPhotoReply record);

    List<IbPhotoReply> selectByExample(IbPhotoReplyExample example);

    IbPhotoReply selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IbPhotoReply record, @Param("example") IbPhotoReplyExample example);

    int updateByExample(@Param("record") IbPhotoReply record, @Param("example") IbPhotoReplyExample example);

    int updateByPrimaryKeySelective(IbPhotoReply record);

    int updateByPrimaryKey(IbPhotoReply record);
}