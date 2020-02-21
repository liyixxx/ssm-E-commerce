package com.ego.manage.service;

import com.ego.commons.pojo.EasyUiDataGrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.pojo.TbItem;

public interface TbItemService {
	/**
	 * 显示商品
	 * @param page
	 * @param rows
	 * @return
	 */
	EasyUiDataGrid show(int page,int rows);
	/**
	 * 批量修改商品状态
	 * @param ids
	 * @param status
	 * @return
	 */
	int update(String ids,byte status);
	
	/**
	 * 新增商品
	 * @param item
	 * @param desc
	 * @return
	 */
	int insTbItem(TbItem item , String desc) throws Exception ; 
	
	/**
	 * 新增商品 同时新增规格参数信息
	 * @param item
	 * @param desc
	 * @param itemParams
	 * @return
	 */
	int insTbItem(TbItem item , String desc , String itemParams) throws Exception ; 
	
	/**
	 * 商品信息修改
	 * @param item
	 * @param desc
	 * @return
	 */
	EgoResult updateItem(TbItem item , String desc);
	
	
}
