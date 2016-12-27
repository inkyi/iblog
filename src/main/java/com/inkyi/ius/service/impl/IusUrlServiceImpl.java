package com.inkyi.ius.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inkyi.ius.constant.IusUrlKey;
import com.inkyi.ius.dao.IusUrlMapper;
import com.inkyi.ius.entity.IusUrl;
import com.inkyi.ius.entity.IusUrlExample;
import com.inkyi.ius.enums.IusUrlEnum;
import com.inkyi.ius.service.IusUrlService;
import com.inkyi.redis.service.RedisService;
import com.inkyi.util.Base62Util;
@Service
public class IusUrlServiceImpl implements IusUrlService {

	@Resource
	private RedisService redisService;
	
	@Resource
	private IusUrlMapper iusUrlsMapper;
	
	/**
	 * 编码
	 */
	@Override
	public String produce(String url) {
		//查询缓存
		String rkey = String.format(IusUrlKey.URL_CACHE, url);
		String code = redisService.get(rkey);
		if(code!=null && !code.isEmpty()){
			redisService.expire(rkey, IusUrlKey.URL_TIME);
			return code;
		}
		//写入数据库
		IusUrl bean = new IusUrl();
		bean.setUrl(url);
		bean.setTotal(0);
		bean.setStatus(IusUrlEnum.STUTAS_NO.getValues());
		int line = iusUrlsMapper.insertSelective(bean);
		if(line==1){
			code = Base62Util.encoding(bean.getId());
			redisService.set(rkey, code);
			redisService.expire(rkey, (long)60*60);
			return code;
		}else{
			return null;
		}
	}

	@Override
	public String restore(String code) {
		long urlid = Base62Util.decoding(code);
		IusUrl bean = iusUrlsMapper.selectByPrimaryKey(urlid);
		if(bean==null || bean.getUrl() == null || bean.getUrl().isEmpty()){
			return null;
		}
		return bean.getUrl();
	}

	@Override
	public Boolean custom(String url, String code) {
		long urlid = Base62Util.decoding(code);
		//DB
		IusUrlExample example = new IusUrlExample();
		example.createCriteria().andIdEqualTo(urlid);
		long total = iusUrlsMapper.countByExample(example);
		if(total > 0){
			return false;
		}
		IusUrl bean = new IusUrl();
		bean.setId(urlid);
		bean.setUrl(url);
		bean.setTotal(0);
		bean.setStatus(IusUrlEnum.STUTAS_NO.getValues());
		int line = iusUrlsMapper.insertSelective(bean);
		if(line!=1){
			return false;
		}
		//Redis
		String rkey = String.format(IusUrlKey.URL_CACHE, url);
		redisService.set(rkey, code);
		redisService.expire(rkey, IusUrlKey.URL_TIME);
		return true;
	}
	
	
	
}
