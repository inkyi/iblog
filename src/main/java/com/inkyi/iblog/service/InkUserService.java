package com.inkyi.iblog.service;

import com.inkyi.iblog.entity.InkUser;
import com.inkyi.iblog.entity.InkUserExample;

public interface InkUserService extends BaseService<InkUser, InkUserExample> {

	/**
	 * 用户名是否存在
	 * @author:InkYi
	 * @time:2016年6月20日 - 下午8:37:20
	 * @description:
	 * @param username
	 * @return ture-存在 ，false-不存在
	 */
	boolean existsUsername(String username);

	InkUser selectByUsername(String username);

}
