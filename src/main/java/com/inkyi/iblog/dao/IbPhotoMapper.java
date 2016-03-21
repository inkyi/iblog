package com.inkyi.iblog.dao;

import com.inkyi.iblog.entity.IbPhoto;
import com.inkyi.iblog.entity.IbPhotoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IbPhotoMapper {
    int countByExample(IbPhotoExample example);

    int deleteByExample(IbPhotoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IbPhoto record);

    int insertSelective(IbPhoto record);

    List<IbPhoto> selectByExample(IbPhotoExample example);

    IbPhoto selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IbPhoto record, @Param("example") IbPhotoExample example);

    int updateByExample(@Param("record") IbPhoto record, @Param("example") IbPhotoExample example);

    int updateByPrimaryKeySelective(IbPhoto record);

    int updateByPrimaryKey(IbPhoto record);
}