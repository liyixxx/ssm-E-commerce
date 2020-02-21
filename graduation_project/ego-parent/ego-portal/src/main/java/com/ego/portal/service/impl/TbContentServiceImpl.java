package com.ego.portal.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.utils.JsonUtils;
import com.ego.dubbo.service.TbContentDubboService;
import com.ego.pojo.TbContent;
import com.ego.portal.service.TbContentService;
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
	public String showPic() {
		
		// 查缓存
		if(jedisDaoImpl.exists(key)){
			String value = jedisDaoImpl.get(key);
			if(value!=null && !value.equals("")){
				return value ;
			}
		}
		
		List<Map<String, Object>> listResult = new ArrayList<>();
		// 查询内容信息
		List<TbContent> list = tbContentDubboServiceImpl.showPic(6, true);
		System.out.println(list);
		for (TbContent ct : list) {
			Map<String, Object> map = new HashMap<>();
			
			//var data = [{"srcB":"http://image.ego.com/images/2015/03/03/2015030304360302109345.jpg","height":240,"alt":"","width":670,"src":"http://image.ego.com/images/2015/03/03/2015030304360302109345.jpg","widthB":550,"href":"http://sale.jd.com/act/e0FMkuDhJz35CNt.html?cpdad=1DLSUE","heightB":240}
			map.put("id", ct.getId());
			map.put("srcB", ct.getPic2());
			map.put("height", 240);
			map.put("alt", "");
			map.put("width", 670);
			map.put("src", ct.getPic());
			map.put("widthB", 550);
			map.put("href", ct.getUrl());
			map.put("heightB", 240);
			
			listResult.add(map);
		}
		// 存入缓存
		String json = JsonUtils.objectToJson(listResult);
		jedisDaoImpl.set(key, json);
		
		return JsonUtils.objectToJson(listResult);
	}

}
