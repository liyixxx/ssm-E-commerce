package com.ego.dubbo.service.impl;

import javax.annotation.Resource;

import com.ego.dubbo.service.TbOrderShippingDubboService;
import com.ego.mapper.TbOrderShippingMapper;
import com.ego.pojo.TbOrderShipping;

public class TbOrderShippingDubboServiceImpl implements TbOrderShippingDubboService{

	@Resource
	private TbOrderShippingMapper tbOrderShippingMapperImpl ;
	
	@Override
	public TbOrderShipping selById(String id) {
		return tbOrderShippingMapperImpl.selectByPrimaryKey(id);
	}

}
