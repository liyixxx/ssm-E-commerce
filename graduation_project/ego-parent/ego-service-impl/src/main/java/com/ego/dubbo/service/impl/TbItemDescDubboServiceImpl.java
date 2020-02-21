package com.ego.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.ego.dubbo.service.TbItemDescDubboService;
import com.ego.mapper.TbItemDescMapper;
import com.ego.pojo.TbItemDesc;
import com.ego.pojo.TbItemDescExample;

public class TbItemDescDubboServiceImpl implements TbItemDescDubboService {

	@Resource
	private TbItemDescMapper tbItemDescMapper;

	@Override
	public TbItemDesc selByItemId(long itemId) {
		return tbItemDescMapper.selectByPrimaryKey(itemId);
	}

	@Override
	public int updByItemDesc(TbItemDesc desc) {
		return tbItemDescMapper.updateByPrimaryKeySelective(desc);
	}

}
