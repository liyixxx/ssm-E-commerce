package com.ego.manage.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ego.commons.pojo.EasyUiDataGrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.manage.service.TbContentService;
import com.ego.pojo.TbContent;

@Controller
public class TbContentController {

	@Resource
	private TbContentService tbContentServiceImpl ;
	
	/**
	 * 查询商品内容
	 * @param categoryId
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("content/query/list")
	@ResponseBody
	public EasyUiDataGrid showPage(long categoryId , int page ,int rows){
		return tbContentServiceImpl.showPage(categoryId, page, rows);
	}

	/**
	 * 内容新增
	 * @param content
	 * @return
	 */
	@RequestMapping("content/save")
	@ResponseBody
	public EgoResult save(TbContent content){
		return tbContentServiceImpl.save(content);
	}
	
	/**
	 * 内容修改
	 * @param content
	 * @return
	 */
	@RequestMapping("rest/content/edit")
	@ResponseBody
	public EgoResult edit(TbContent content){
		return tbContentServiceImpl.edit(content) ;
	}
	
}
