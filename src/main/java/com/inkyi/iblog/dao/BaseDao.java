package com.inkyi.iblog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BaseDao<T, E> {

	int countByExample(E example);

	int deleteByExample(E example);

	int deleteByPrimaryKey(Integer id);

	int insert(T record);

	int insertSelective(T record);

	List<T> selectByExample(E example);

	T selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") T record,@Param("example") E example);

	int updateByExample(@Param("record") T record,@Param("example") E example);

	int updateByPrimaryKeySelective(T record);

	int updateByPrimaryKey(T record);
}