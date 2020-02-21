package com.ego.dubbo.service;

import com.ego.pojo.TbItemParamItem;
/**
 * 商品详细规格参数
 * @author 柒
 *
 */
public interface TbItemParamItemDubboService {

	/**
	 * 根据商品id查询商品规格参数
	 * @param itemId
	 * @return
	 */
	TbItemParamItem selByItemId(long itemId);

}
