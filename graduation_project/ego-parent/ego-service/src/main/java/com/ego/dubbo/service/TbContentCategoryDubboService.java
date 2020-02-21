package com.ego.dubbo.service;

import java.util.List;

import com.ego.pojo.TbContentCategory;

/**
 * 内容管理模块
 * @author 柒
 *
 */
public interface TbContentCategoryDubboService {

	/**
	 * 根据父id查询所有子类目信息
	 * @param pid
	 * @return
	 */
	List<TbContentCategory> selByPid(long pid);
	
	/**
	 * 新增分类
	 * @param cate
	 * @return
	 */
	int insTbContentCategory(TbContentCategory cate) ;
	
	/**
	 * 修改父结点状态
	 * @param cate
	 * @return
	 */
	int updIsParentById(TbContentCategory cate );
	
	/**
	 * 根据id查询结点信息
	 * @param id
	 * @return
	 */
	TbContentCategory selById(long id) ;
	
	/**
	 * 修改结点信息
	 * @param cate
	 * @return
	 */
	int updTbContentCategory(TbContentCategory cate);
	
}
