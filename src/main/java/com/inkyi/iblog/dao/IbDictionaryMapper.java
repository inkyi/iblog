package com.inkyi.iblog.dao;

import com.inkyi.iblog.entity.IbDictionary;
import com.inkyi.iblog.entity.IbDictionaryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IbDictionaryMapper {
    int countByExample(IbDictionaryExample example);

    int deleteByExample(IbDictionaryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IbDictionary record);

    int insertSelective(IbDictionary record);

    List<IbDictionary> selectByExample(IbDictionaryExample example);

    IbDictionary selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IbDictionary record, @Param("example") IbDictionaryExample example);

    int updateByExample(@Param("record") IbDictionary record, @Param("example") IbDictionaryExample example);

    int updateByPrimaryKeySelective(IbDictionary record);

    int updateByPrimaryKey(IbDictionary record);
}