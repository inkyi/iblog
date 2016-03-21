package com.inkyi.iblog.dao;

import com.inkyi.iblog.entity.IbAlbum;
import com.inkyi.iblog.entity.IbAlbumExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IbAlbumMapper {
    int countByExample(IbAlbumExample example);

    int deleteByExample(IbAlbumExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IbAlbum record);

    int insertSelective(IbAlbum record);

    List<IbAlbum> selectByExample(IbAlbumExample example);

    IbAlbum selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IbAlbum record, @Param("example") IbAlbumExample example);

    int updateByExample(@Param("record") IbAlbum record, @Param("example") IbAlbumExample example);

    int updateByPrimaryKeySelective(IbAlbum record);

    int updateByPrimaryKey(IbAlbum record);
}