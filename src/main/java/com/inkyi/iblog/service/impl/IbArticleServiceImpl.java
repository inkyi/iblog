package com.inkyi.iblog.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inkyi.iblog.dao.IbArticleMapper;
import com.inkyi.iblog.entity.IbArticle;
import com.inkyi.iblog.entity.IbArticleExample;
import com.inkyi.iblog.service.IbArticleService;
@Service
public class IbArticleServiceImpl  implements IbArticleService {
	
	@Resource
	private IbArticleMapper ibArticleMapper;

	@Override
	public int countByExample(IbArticleExample example) {
		return ibArticleMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(IbArticleExample example) {
		return ibArticleMapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return ibArticleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(IbArticle record) {
		return ibArticleMapper.insert(record);
	}

	@Override
	public int insertSelective(IbArticle record) {
		return ibArticleMapper.insertSelective(record);
	}

	@Override
	public List<IbArticle> selectByExampleWithBLOBs(IbArticleExample example) {
		return ibArticleMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public List<IbArticle> selectByExample(IbArticleExample example) {
		return ibArticleMapper.selectByExample(example);
	}

	@Override
	public IbArticle selectByPrimaryKey(Integer id) {
		return ibArticleMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByExampleSelective(IbArticle record,
			IbArticleExample example) {
		return ibArticleMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExampleWithBLOBs(IbArticle record,
			IbArticleExample example) {
		return ibArticleMapper.updateByExampleWithBLOBs(record, example);
	}

	@Override
	public int updateByExample(IbArticle record, IbArticleExample example) {
		return ibArticleMapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(IbArticle record) {
		return ibArticleMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(IbArticle record) {
		return ibArticleMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(IbArticle record) {
		return ibArticleMapper.updateByPrimaryKey(record);
	}

}