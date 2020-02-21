package com.ego.item.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.utils.JsonUtils;
import com.ego.dubbo.service.TbItemCatDubboService;
import com.ego.item.pojo.PortalMenu;
import com.ego.item.pojo.PortalMenuNode;
import com.ego.item.service.TbItemCatService;
import com.ego.pojo.TbItemCat;
import com.ego.redis.dao.JedisDao;

import redis.clients.jedis.Jedis;

@Service
public class TbItemCatServiceImpl implements TbItemCatService{

	@Reference
	private TbItemCatDubboService tbItemCatDubboServiceImpl ;
	
	@Value("${portal.item.cat}")
	private String key ;
	
	@Resource
	private JedisDao jedisDaoImpl ;
	
	@Override
	public PortalMenu showMenu() {
		// 查缓存
		if(jedisDaoImpl.exists(key)){
			String value = jedisDaoImpl.get(key);
			if(value!=null && !value.equals("")){
				return JsonUtils.jsonToPojo(value, PortalMenu.class);
			}
		}
		List<TbItemCat> list = tbItemCatDubboServiceImpl.show(0);
		PortalMenu pm = new PortalMenu();
		pm.setData(showAll(list));
		// 存缓存
		jedisDaoImpl.set(key, JsonUtils.objectToJson(pm));
		return pm;
	}

	/**
	 * 递归查询
	 */
	@Override
	public List<Object> showAll(List<TbItemCat> itemCat) {
		List<Object> listNode = new ArrayList<>();
		for (TbItemCat item : itemCat) {
			if(item.getIsParent()){
				PortalMenuNode pmn = new PortalMenuNode();
				pmn.setU("/products/" + item.getId() + ".html");
				pmn.setN("<a href='/products/" + item.getId() + ".html'>" + item.getName() + "</a>");
				pmn.setI(showAll(tbItemCatDubboServiceImpl.show(item.getId())));
				listNode.add(pmn);
			}else{
				listNode.add("/products/"+item.getId()+".html|"+item.getName());
			}
		}
		return listNode;
	}

}
