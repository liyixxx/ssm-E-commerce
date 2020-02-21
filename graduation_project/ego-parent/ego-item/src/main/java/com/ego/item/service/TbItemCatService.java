package com.ego.item.service;

import java.util.List;

import com.ego.item.pojo.PortalMenu;
import com.ego.pojo.TbItemCat;

public interface TbItemCatService {

	/**
	 * 显示类目信息
	 * @return
	 */
	PortalMenu showMenu();
	
	/**
	 * 递归查询所有信息
	 * @param itemCat
	 * @return
	 */
	List<Object> showAll(List<TbItemCat> itemCat);
}
