package com.ego.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.ego.commons.pojo.EasyUiDataGrid;
import com.ego.dubbo.service.TbContentDubboService;
import com.ego.mapper.TbContentMapper;
import com.ego.pojo.TbContent;
import com.ego.pojo.TbContentExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public class TbContentDubboServiceImpl implements TbContentDubboService{

	@Resource
	private TbContentMapper tbContentMapper ;
	
	@Override
	public EasyUiDataGrid showPage(long categoryId, int page, int rows) {
		PageHelper.startPage(page, rows);
		TbContentExample example = new TbContentExample();
		EasyUiDataGrid datagrid = new EasyUiDataGrid();
		if(categoryId!=0){
			example.createCriteria().andCategoryIdEqualTo(categoryId);
		}
		List<TbContent> list = tbContentMapper.selectByExample(example);
		PageInfo<TbContent> info = new PageInfo<>(list);
		datagrid.setRows(info.getList());
		datagrid.setTotal(info.getTotal());
		return datagrid;
	}

	@Override
	public int insTbContent(TbContent content) {
		return tbContentMapper.insertSelective(content);
	}

	@Override
	public List<TbContent> showPic(int count, boolean isSort) {
		TbContentExample example = new TbContentExample();
		if(isSort){
			// 排序
			example.setOrderByClause("updated desc");
		}
		if(count!=0){
			// 分页 从1开始
			PageHelper.startPage(1, count);
			List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example);
			PageInfo<TbContent> info = new PageInfo<>(list);
			return info.getList();
		}else{
			return tbContentMapper.selectByExampleWithBLOBs(example);
		}
	}

	@Override
	public int updTbContent(TbContent content) {
		return tbContentMapper.updateByPrimaryKeySelective(content);
	}

}
