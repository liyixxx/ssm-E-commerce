package com.ego.manage.service;

import com.ego.commons.pojo.EasyUiDataGrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.pojo.TbItemParam;

public interface TbItemParamService {

	/**
	 * 分页显示商品规格参数
	 * @param page
	 * @param rows
	 * @return
	 */
	EasyUiDataGrid showPage(int page , int rows );
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	int delById(String ids ) throws Exception;
	
	/**
	 * 根据类目id查询模板信息
	 * @param catId
	 * @return
	 */
	EgoResult selByCatId(long catId);
	
	/**
	 * 新增商品类目
	 * @param param
	 * @return
	 */
	EgoResult insParam (TbItemParam param);
}
