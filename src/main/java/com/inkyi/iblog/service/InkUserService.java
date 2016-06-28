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
	/**
	 * 根据用户名查询用户
	 * @author:InkYi
	 * @time:2016年6月28日 - 上午10:35:38
	 * @description:
	 * @param username
	 * @return
	 */
	InkUser selectByUsername(String username);
	/**
	 * 从缓存中获取用户
	 * @author:InkYi
	 * @time:2016年6月28日 - 上午10:35:49
	 * @description:
	 * @param id
	 * @return
	 */
	public InkUser getUserForCache(Long id);
	/**
	 * 将用户存入缓存
	 * @author:InkYi
	 * @time:2016年6月28日 - 上午10:36:07
	 * @description:
	 * @param user
	 * @return
	 */
	public boolean setUserForCache(InkUser user);

}
