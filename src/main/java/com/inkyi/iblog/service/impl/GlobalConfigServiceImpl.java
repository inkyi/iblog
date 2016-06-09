package com.inkyi.iblog.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inkyi.iblog.service.GlobalConfigService;
import com.inkyi.redis.service.RedisService;

/**
 * 全局设置
 * @ClassName: GlobalConfigService 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author InkYi
 * @date 2016年6月9日 下午5:32:32 
 *
 */
@Service
public class GlobalConfigServiceImpl implements GlobalConfigService{

	@Resource
	private RedisService redisService;

	@Override
	public boolean logReCaptcha() {
		return false;
	}
	
	
	
}
