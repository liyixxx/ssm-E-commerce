package com.ego.manage.service;

import java.util.List;

import com.ego.commons.pojo.EasyUiTree;

public interface TbItemCatService {

	/**
	 * 根据父id查询所有子类目
	 * @param pid
	 * @return
	 */
	List<EasyUiTree> show(long pid);
}
