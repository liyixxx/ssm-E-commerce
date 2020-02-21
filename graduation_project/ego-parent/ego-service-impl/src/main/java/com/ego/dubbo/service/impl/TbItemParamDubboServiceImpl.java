package com.ego.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.ego.commons.pojo.EasyUiDataGrid;
import com.ego.dubbo.service.TbItemParamDubboService;
import com.ego.mapper.TbItemParamMapper;
import com.ego.pojo.TbItemParam;
import com.ego.pojo.TbItemParamExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public class TbItemParamDubboServiceImpl implements TbItemParamDubboService{

	@Resource
	private TbItemParamMapper tbItemParamMapper ;
	
	@Override
	public EasyUiDataGrid showPage(int page,int rows) {
		PageHelper.startPage(page, rows);

		List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(new TbItemParamExample());
		
		PageInfo<TbItemParam> info = new PageInfo<>(list);
		
		EasyUiDataGrid dataGtid = new EasyUiDataGrid();
		dataGtid.setRows(info.getList());
		dataGtid.setTotal(info.getTotal());
		
		return dataGtid;
	}

	@Override
	public int delById(String ids) throws Exception {
		String[] idsStr = ids.split(",");
		int index = 0 ;
		for (String id : idsStr) {
			 index += tbItemParamMapper.deleteByPrimaryKey(Long.parseLong(id));
		}
		if(index == idsStr.length){
			return 1 ;
		}else{
			throw new Exception("记录已经不存在！");
		}
	}

	@Override
	public TbItemParam selByCatId(long catId) {
		TbItemParamExample example = new TbItemParamExample();
		example.createCriteria().andItemCatIdEqualTo(catId);
		List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null ;
	}

	@Override
	public int insParam(TbItemParam param) {
		return tbItemParamMapper.insertSelective(param);
	}

}
