package com.ego.dubbo.service;

import java.util.List;

import com.ego.pojo.TbItemCat;

/**
 * 商品类目表
 * @author 柒
 *
 */
public interface TbItemCatDubboService {

	/**
	 * 根据父id查询所有子类目
	 * @param pid
	 * @return
	 */
	List<TbItemCat> show(long pid);
	
	/**
	 * 根据id查询商品类目
	 * @param id
	 * @return
	 */
	TbItemCat selById(long id);
}
