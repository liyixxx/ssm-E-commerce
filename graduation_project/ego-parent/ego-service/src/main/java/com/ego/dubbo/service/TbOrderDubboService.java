package com.ego.dubbo.service;

import java.util.List;

import com.ego.pojo.TbOrder;
import com.ego.pojo.TbOrderItem;
import com.ego.pojo.TbOrderShipping;

/**
 * 订单
 * @author 柒
 *
 */
public interface TbOrderDubboService {

	/**
	 * 创建订单
	 * @param order
	 * @param list
	 * @param orderShipping
	 * @return
	 */
	int insOrder(TbOrder order , List<TbOrderItem> list , TbOrderShipping orderShipping) throws Exception ;
	
	/**
	 * 根据主键(tbOrderId)查询订单信息 
	 * @return
	 */
	TbOrder selById(String id);
}
