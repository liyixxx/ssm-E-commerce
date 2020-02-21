package com.ego.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.ego.dubbo.service.TbOrderItemDubboService;
import com.ego.mapper.TbOrderItemMapper;
import com.ego.pojo.TbOrderItem;
import com.ego.pojo.TbOrderItemExample;

public class TbOrderItemDubboServiceImpl implements TbOrderItemDubboService{

	@Resource
	private TbOrderItemMapper tbOrderItemMapperImpl ;
	
	@Override
	public List<TbOrderItem> selByOrderId(String id) {
		TbOrderItemExample example = new TbOrderItemExample();
		example.createCriteria().andOrderIdEqualTo(id);
		return tbOrderItemMapperImpl.selectByExample(example);
	}

}
