package com.ego.manage.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.pojo.EasyUiDataGrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.dubbo.service.TbItemCatDubboService;
import com.ego.dubbo.service.TbItemParamDubboService;
import com.ego.manage.pojo.TbItemParamChild;
import com.ego.manage.service.TbItemParamService;
import com.ego.pojo.TbItemParam;

@Service
public class TbItemParamServiceImpl implements TbItemParamService{

	@Reference
	private TbItemParamDubboService tbItemParamDubboServiceImpl ;
	
	@Reference
	private TbItemCatDubboService tbItemCatDubboServiceImpl ;
	
	@Override
	public EasyUiDataGrid showPage(int page, int rows) {
		EasyUiDataGrid datagrid = tbItemParamDubboServiceImpl.showPage(page, rows);
		List<TbItemParam> paramList = (List<TbItemParam>) datagrid.getRows();
		List<TbItemParamChild> childList = new ArrayList<>();
		
		for (TbItemParam param : paramList) {
			TbItemParamChild child = new TbItemParamChild();
			
			child.setId(param.getId());
			child.setCreated(param.getCreated());
			child.setUpdated(param.getUpdated());
			child.setItemCatId(param.getItemCatId());
			child.setParamData(param.getParamData());
			
			child.setItemCatName(tbItemCatDubboServiceImpl.selById(child.getItemCatId()).getName());
			
			childList.add(child);
		}
		datagrid.setRows(childList);
		
		return datagrid ;
	}

	@Override
	public int delById(String ids) throws Exception {
		return tbItemParamDubboServiceImpl.delById(ids);
	}

	@Override
	public EgoResult selByCatId(long catId) {
		EgoResult ego = new EgoResult();
		TbItemParam param = tbItemParamDubboServiceImpl.selByCatId(catId);
		if(param!=null ){
			ego.setStatus(200);
			ego.setData(param);
		}
		return ego;
	}

	@Override
	public EgoResult insParam(TbItemParam param) {
		Date date = new Date();
		param.setCreated(date);
		param.setUpdated(date);
		int index = tbItemParamDubboServiceImpl.insParam(param);
		EgoResult ego = new EgoResult();
		if(index > 0){
			ego.setStatus(200);
		}	
		return ego ;
	}

}
