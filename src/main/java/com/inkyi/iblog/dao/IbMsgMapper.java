package com.inkyi.iblog.dao;

import com.inkyi.iblog.entity.IbMsg;
import com.inkyi.iblog.entity.IbMsgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IbMsgMapper {
    int countByExample(IbMsgExample example);

    int deleteByExample(IbMsgExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IbMsg record);

    int insertSelective(IbMsg record);

    List<IbMsg> selectByExample(IbMsgExample example);

    IbMsg selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IbMsg record, @Param("example") IbMsgExample example);

    int updateByExample(@Param("record") IbMsg record, @Param("example") IbMsgExample example);

    int updateByPrimaryKeySelective(IbMsg record);

    int updateByPrimaryKey(IbMsg record);
}