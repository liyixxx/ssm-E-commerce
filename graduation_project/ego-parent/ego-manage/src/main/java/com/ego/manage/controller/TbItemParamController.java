package com.ego.manage.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ego.commons.pojo.EasyUiDataGrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.manage.service.TbItemParamService;
import com.ego.pojo.TbItemParam;

@Controller
public class TbItemParamController {

	@Resource
	private TbItemParamService tbItemParamServiceImpl ;
	
	@RequestMapping("item/param/list")
	@ResponseBody
	public EasyUiDataGrid showPage(int page , int rows){
		return tbItemParamServiceImpl.showPage(page, rows);
	}
	
	/**
	 * 删除规格参数
	 * @param ids
	 * @return
	 */
	@RequestMapping("item/param/delete")
	@ResponseBody
	public EgoResult delById(String ids){
		int index = 0 ;
		EgoResult ego = new EgoResult();
		try {
			index = tbItemParamServiceImpl.delById(ids);
			if(index == 1){
				ego.setStatus(200);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ego.setData(e.getMessage());
		}
		return ego ;
	}
	
	/**
	 * 点击商品类目按钮显示添加分组按钮
	 * 判断类目是否已经添加模板
	 * @param catId
	 * @return
	 */
	@RequestMapping("item/param/query/itemcatid/{catId}")
	@ResponseBody
	public EgoResult selByCatId(@PathVariable long catId){
		return tbItemParamServiceImpl.selByCatId(catId);
	}
	
	/**
	 * 商品类目新增
	 * @param param
	 * @param catId
	 * @return
	 */
	@RequestMapping("item/param/save/{catId}")
	@ResponseBody
	public EgoResult save(TbItemParam param ,@PathVariable long catId){
		param.setItemCatId(catId);
		return tbItemParamServiceImpl.insParam(param);
	}
	
}
