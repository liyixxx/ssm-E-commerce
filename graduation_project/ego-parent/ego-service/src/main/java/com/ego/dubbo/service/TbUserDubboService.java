package com.ego.dubbo.service;

import com.ego.pojo.TbUser;
/**
 * 用户
 * @author 柒
 *
 */
public interface TbUserDubboService {

	/**
	 * 根据用户名密码查询用户
	 * @param user
	 * @return
	 */
	TbUser selByUser(TbUser user);
}
