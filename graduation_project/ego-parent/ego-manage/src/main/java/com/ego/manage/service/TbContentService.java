package com.ego.manage.service;

import com.ego.commons.pojo.EasyUiDataGrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.pojo.TbContent;

public interface TbContentService {

	/**
	 * 商品内容查询
	 * @param categoryId
	 * @param page
	 * @param rows
	 * @return
	 */
	EasyUiDataGrid showPage(long categoryId , int page , int rows);
	
	/**
	 * 商品内容新增
	 * @param content
	 * @return
	 */
	EgoResult save(TbContent content);
	
	/**
	 * 商品内容修改
	 * @param content
	 * @return
	 */
	EgoResult edit(TbContent content);
	
}
