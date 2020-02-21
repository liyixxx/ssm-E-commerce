package com.ego.manage.service;

import java.util.List;

import com.ego.commons.pojo.EasyUiTree;
import com.ego.commons.pojo.EgoResult;
import com.ego.pojo.TbContentCategory;

public interface TbContentCategoryService {

	/**
	 * 查询所有类目 转换为easyuitree类型
	 * @param id
	 * @return
	 */
	List<EasyUiTree> showCategory(long id) ;
	
	/**
	 * 内容新增
	 * @param cate
	 * @return
	 */
	EgoResult create (TbContentCategory cate) ;
	
	/**
	 * 内容修改
	 * @param cate
	 * @return
	 */
	EgoResult update (TbContentCategory cate) ;
	
	/**
	 * 类目删除/逻辑删除
	 * @param cate
	 * @return
	 */
	EgoResult delete (TbContentCategory cate) ;
	
}
