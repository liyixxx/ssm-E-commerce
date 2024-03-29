package com.ego.item.service;

import java.util.List;

import com.ego.commons.pojo.TbItemChild;

public interface TbItemService {

	/**
	 * 根据id查询商品信息
	 * @param id
	 * @return
	 */
	TbItemChild showItem(long id);
	
	/**
	 * 显示商品详情页的类目信息
	 * @param id
	 * @return
	 */
	List<String> showItemCat(long id);
	
	/**
	 * 显示商品描述信息
	 * @return
	 */
	String showDesc(long itemId);
}
