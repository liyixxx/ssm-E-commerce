package com.ego.manage.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.pojo.EasyUiDataGrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.commons.utils.JsonUtils;
import com.ego.dubbo.service.TbContentDubboService;
import com.ego.manage.service.TbContentService;
import com.ego.pojo.TbContent;
import com.ego.redis.dao.JedisDao;

@Service
public class TbContentServiceImpl implements TbContentService{

	@Reference
	private TbContentDubboService tbContentDubboServiceImpl ;

	@Resource
	private JedisDao jedisDaoImpl ;
	
	@Value("${redis.pic.key}")
	private String key ;
	
	@Override
	public EasyUiDataGrid showPage(long categoryId, int page, int rows) {
		return tbContentDubboServiceImpl.showPage(categoryId, page, rows);
	}

	@Override
	public EgoResult save(TbContent content) {
		EgoResult ego = new EgoResult() ;
		Date date = new Date();
		content.setCreated(date);
		content.setUpdated(date);
		int index = tbContentDubboServiceImpl.insTbContent(content);
		// 数据库新增 成功后存入redis
		if(index > 0){
			if(jedisDaoImpl.exists(key)){
				String value = jedisDaoImpl.get(key);
				if(value!=null && !value.equals("")){
					// 获取redis中的所有广告信息
					List<HashMap> list = JsonUtils.jsonToList(value, HashMap.class);
					HashMap<String, Object> map = new HashMap<>();
					
					// 存入内容信息
					map.put("id", content.getId());
					map.put("srcB", content.getPic2());
					map.put("height", 240);
					map.put("alt", "");
					map.put("width", 670);
					map.put("src", content.getPic());
					map.put("widthB", 550);
					map.put("href", content.getUrl());
					map.put("heightB", 240);
					
					// 保证元素个数为6
					if(list.size()==6){
						list.remove(5);
					}
					
					list.add(0,map);
					jedisDaoImpl.set(key, JsonUtils.objectToJson(list));
					ego.setStatus(200);
				}
			}
		}
		
		return ego ;
	}

	@Override
	public EgoResult edit(TbContent content) {
		EgoResult ego = new EgoResult();
		Date date = new Date();
		content.setUpdated(date);
		int index = tbContentDubboServiceImpl.updTbContent(content);
		// 修改后对redis同步
		if(index > 0){
			if(jedisDaoImpl.exists(key)){
				String value = jedisDaoImpl.get(key);
				if(value!=null && !value.equals("")){
					// 获取redis中的所有广告信息
					List<HashMap> list = JsonUtils.jsonToList(value, HashMap.class);
					System.out.println(list);
					for (HashMap<String , Object> map : list) {
						// 遍历redis广告信息 当id和当前传入id相同时 对其进行修改
						Long parseLong = Long.parseLong(map.get("id").toString());
						if(parseLong.equals(content.getId())){
							// 获取map在list中的位置
							int listIndex = list.indexOf(map);
							// 存入内容信息
							map.put("id", content.getId());
							map.put("srcB", content.getPic2());
							map.put("height", 240);
							map.put("alt", "");
							map.put("width", 670);
							map.put("src", content.getPic());
							map.put("widthB", 550);
							map.put("href", content.getUrl());
							map.put("heightB", 240);
							// 修改该节点的map元素
							list.set(listIndex, map);
							
							String json = JsonUtils.objectToJson(list);
							jedisDaoImpl.set(key, JsonUtils.objectToJson(list));
							ego.setStatus(200);
						}
					}
					System.out.println(list);
				}
			}
		}
		
		return ego;
	}
	
	
}
