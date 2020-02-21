package com.ego.dubbo.service;

import com.ego.pojo.TbOrderShipping;

/**
 * 订单 - 收货人
 * @author 柒
 *
 */
public interface TbOrderShippingDubboService {

	/**
	 * 根据id查询详细信息
	 * @param id
	 * @return
	 */
	TbOrderShipping selById(String id);
}
