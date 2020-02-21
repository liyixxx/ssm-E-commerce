package com.ego.manage.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ego.commons.pojo.EasyUiDataGrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.manage.service.TbItemService;
import com.ego.pojo.TbItem;

@Controller
public class TbItemController {

	@Resource
	public TbItemService tbItemServiceImpl ;
	
	/** 
	 * 分页显示所有商品
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("item/list")
	@ResponseBody
	public EasyUiDataGrid show(int page , int rows ){
		return tbItemServiceImpl.show(page, rows);
	}
	
	/**
	 * 显示商品修改页面
	 * @return
	 */
	@RequestMapping("rest/page/item-edit")
	public String showItemEdit(){
		return "item-edit" ;
	}
	
	/**
	 * 删除商品
	 * @param ids
	 * @return
	 */
	@RequestMapping("rest/item/delete")
	@ResponseBody
	public EgoResult delete(String ids){
		EgoResult er = new EgoResult();
		int index = tbItemServiceImpl.update(ids, (byte)3);
		if(index == 1){
			er.setStatus(200);
		}
		return er ;
	} 
	
	/**
	 * 下架商品
	 * @param ids
	 * @return
	 */
	@RequestMapping("rest/item/instock")
	@ResponseBody
	public EgoResult instock(String ids){
		EgoResult er = new EgoResult();
		int index = tbItemServiceImpl.update(ids, (byte)2);
		if(index == 1){
			er.setStatus(200);
		}
		return er ;
	} 
	
	/**
	 * 上架商品
	 * @param ids
	 * @return
	 */
	@RequestMapping("rest/item/reshelf")
	@ResponseBody
	public EgoResult reshelf(String ids){
		EgoResult er = new EgoResult();
		int index = tbItemServiceImpl.update(ids, (byte)1);
		if(index == 1){
			er.setStatus(200);
		}
		return er ;
	} 
	
	/**
	 * 新增商品
	 * @param item
	 * @param desc
	 * @param itemParams
	 * @return
	 */
	@RequestMapping(value = "item/save")
	@ResponseBody
	public EgoResult insert(TbItem item , String desc , String itemParams){
		System.out.println(item.getTitle()+"--"+item.getSellPoint()+"--"+desc);
		
		EgoResult er = new EgoResult();
		int index ;
		try {
			index = tbItemServiceImpl.insTbItem(item, desc, itemParams);
			if(index == 1){
				er.setStatus(200);
			}
		} catch (Exception e) {
			e.printStackTrace();
			er.setData(e.getMessage());
		}
		return er ;
	}
	
	@RequestMapping("rest/item/update")
	@ResponseBody
	public EgoResult update(TbItem item , String desc){
		return tbItemServiceImpl.updateItem(item, desc);
	}
	
}
