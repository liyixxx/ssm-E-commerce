package com.ego.manage.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.pojo.EasyUiTree;
import com.ego.commons.pojo.EgoResult;
import com.ego.commons.utils.IDUtils;
import com.ego.dubbo.service.TbContentCategoryDubboService;
import com.ego.manage.service.TbContentCategoryService;
import com.ego.pojo.TbContentCategory;

@Service
public class TbContentCategoryServiceImpl implements TbContentCategoryService{

	@Reference
	private TbContentCategoryDubboService tbContentCategoryDubboServiceImpl ;
	
	@Override
	public List<EasyUiTree> showCategory(long id) {
		List<EasyUiTree> listTree = new ArrayList<>();
		List<TbContentCategory> list = tbContentCategoryDubboServiceImpl.selByPid(id);
		for (TbContentCategory cat : list) {
			EasyUiTree tree = new EasyUiTree();
			
			tree.setId(cat.getId());
			tree.setText(cat.getName());
			tree.setState(cat.getIsParent()?"closed":"open");
			
			listTree.add(tree);
		}
		
		return listTree;
	}

	@Override
	public EgoResult create(TbContentCategory cate) {
		EgoResult ego = new EgoResult();
		
		// 查询同一父节点下的所有子节点 判断是否有重名
		List<TbContentCategory> children = tbContentCategoryDubboServiceImpl.selByPid(cate.getParentId());
		for (TbContentCategory child : children) {
			if (child.getName().equals(cate.getName())) {
				ego.setData("结点"+cate.getName()+"已经存在");
				return ego ;
			}
		}
		
		// 没有重名 做新增操作
		Date date = new Date();
		cate.setCreated(date);
		cate.setUpdated(date);
		cate.setIsParent(false);
		cate.setSortOrder(1);
		cate.setStatus(1);
		long id = IDUtils.genItemId();
		cate.setId(id);
		
		int index = tbContentCategoryDubboServiceImpl.insTbContentCategory(cate);
		if(index>0){
			// 新增成功 修改父节点信息
			TbContentCategory parent = new TbContentCategory();
			parent.setUpdated(date);
			parent.setIsParent(true);
			parent.setId(cate.getParentId());
			index += tbContentCategoryDubboServiceImpl.updIsParentById(parent);
		}
		if(index == 2){
			ego.setStatus(200);
			Map<String, Long> map = new HashMap<String, Long>();
			map.put("id", id);
			ego.setData(map);
		}
		return ego;
	}

	@Override
	public EgoResult update(TbContentCategory cate) {
		EgoResult ego = new EgoResult();
		
		// 查询当前结点信息
		TbContentCategory category = tbContentCategoryDubboServiceImpl.selById(cate.getId());
		// 查询和当前结点同一级的所有信息
		List<TbContentCategory> listParent = tbContentCategoryDubboServiceImpl.selByPid(category.getParentId());
		// 查询当前结点下的所有子节点信息
		List<TbContentCategory> listChild = tbContentCategoryDubboServiceImpl.selByPid(category.getId());
		// 查询父节点信息
		TbContentCategory parent = tbContentCategoryDubboServiceImpl.selById(category.getParentId());
		if(parent.getName().equals(cate.getName())){
			ego.setData("结点"+cate.getName()+"在父节点中已经存在");
			return ego ;
		}
		
		for (TbContentCategory node : listParent) {
			if (node.getName().equals(cate.getName())) {
				ego.setData("结点"+cate.getName()+"已经存在");
				return ego ;
			}
		}
		
		for (TbContentCategory node : listChild) {
			if(node.getName().equals(cate.getName())){
				ego.setData("结点"+cate.getName()+"在其子节点中已经存在");
				return ego ;
			}
		}
		
		int index = tbContentCategoryDubboServiceImpl.updTbContentCategory(cate);
		if(index > 0){
			ego.setStatus(200);
		}
		
		return ego;
	}

	@Override
	public EgoResult delete(TbContentCategory cate) {
		EgoResult ego = new EgoResult() ;
		
		TbContentCategory category = tbContentCategoryDubboServiceImpl.selById(cate.getId());
		category.setStatus(0);
		int index = tbContentCategoryDubboServiceImpl.updTbContentCategory(category);
		if(index > 0){
			// 判断该节点是否含有其他子节点 没有将其isParent属性置为false
			List<TbContentCategory> listChild = tbContentCategoryDubboServiceImpl.selByPid(category.getId());
			if(listChild.size()>0&&listChild!=null){
				ego.setStatus(200);
			}else{
				TbContentCategory parent = new TbContentCategory();
				parent.setId(category.getParentId());
				parent.setIsParent(false);
				index += tbContentCategoryDubboServiceImpl.updIsParentById(parent);
				if(index == 2){
					ego.setStatus(200);
				}
			}
		}
		
		return ego;
	}

}
