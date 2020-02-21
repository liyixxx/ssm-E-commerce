package com.ego.item.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.pojo.TbItemChild;
import com.ego.commons.utils.JsonUtils;
import com.ego.dubbo.service.TbItemCatDubboService;
import com.ego.dubbo.service.TbItemDescDubboService;
import com.ego.dubbo.service.TbItemDubboService;
import com.ego.item.service.TbItemService;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemCat;
import com.ego.redis.dao.JedisDao;

@Service
public class TbItemServiceImpl implements TbItemService{

	@Reference
	private TbItemDubboService tbItemDubboServiceImpl ;
	
	@Reference
	private TbItemDescDubboService tbItemDescDubboServiceImpl ;
	
	@Reference
	private TbItemCatDubboService tbItemCatDubboServiceImpl ;
	
	@Resource
	private JedisDao jedisDaoImpl ;
	
	@Value("${redis.item.key}")
	private String itemKey ;
	
	@Value("${redis.desc.key}")
	private String descKey ;
	

	@Override
	public List<String> showItemCat(long id) {
		TbItem item = tbItemDubboServiceImpl.selById(id);
		// 类目
		TbItemCat cat = tbItemCatDubboServiceImpl.selById(item.getCid());
		// 该节点的类目名称
		List<String> catList = new ArrayList<>();
		catList.add(cat.getName());
		while(cat.getParentId()!=0){
			cat = tbItemCatDubboServiceImpl.selById(cat.getParentId());
			catList.add(cat.getName());
		}
		System.out.println(catList+"\t"+catList.size());
		
		for(int i=catList.size()-1;i>=0;i--){
			System.out.println(catList.get(i));
		}
		
		return catList;
	}
	
	@Override
	public TbItemChild showItem(long id) {
		TbItemChild child = new TbItemChild();
		String key = itemKey+id;
		// 查缓存
		if(jedisDaoImpl.exists(key)){
			String value = jedisDaoImpl.get(key);
			if(value!=null && !value.equals("")){
				return JsonUtils.jsonToPojo(value, TbItemChild.class);
			}
		}
		// 查数据库
		TbItem item = tbItemDubboServiceImpl.selById(id);
		child.setId(item.getId());
		child.setTitle(item.getTitle());
		child.setPrice(item.getPrice());
		child.setSellPoint(item.getSellPoint());
		child.setImages(item.getImage()!=null&&!item.getImage().equals("")?item.getImage().split(","):new String [1]);
		jedisDaoImpl.set(key, JsonUtils.objectToJson(child));
		return child;
	}

	@Override
	public String showDesc(long itemId) {
		String key = descKey + itemId ;
		if(jedisDaoImpl.exists(key)){
			String value = jedisDaoImpl.get(key);
			if(value!=null && !value.equals("")){
				return value ;
			}
		}
		String desc = tbItemDescDubboServiceImpl.selByItemId(itemId).getItemDesc();
		jedisDaoImpl.set(key, desc);
		return desc;
	}


}
