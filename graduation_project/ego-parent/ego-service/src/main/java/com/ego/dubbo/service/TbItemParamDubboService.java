package com.ego.dubbo.service;

import com.ego.commons.pojo.EasyUiDataGrid;
import com.ego.pojo.TbItemParam;
/**
 * 商品规格参数信息
 * @author 柒
 *
 */
public interface TbItemParamDubboService {

	/**
	 * 分页查询规格参数信息
	 * @param page
	 * @param rows
	 * @return
	 */
	EasyUiDataGrid showPage( int page ,int rows );

	/**
	 * 根据id进行批量删除
	 * @param ids
	 * @return
	 */
	int delById(String ids ) throws Exception ;
	
	/**
	 * 根据类目id查询参数模板
	 * @param catId
	 * @return
	 */
	TbItemParam selByCatId(long catId) ;
	
	/***
	 * 新增商品 
	 * @param param
	 * @return
	 */
	int insParam(TbItemParam param );
}
