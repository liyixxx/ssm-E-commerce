package com.ego.dubbo.service;

import java.util.List;

import com.ego.commons.pojo.EasyUiDataGrid;
import com.ego.pojo.TbContent;
/**
 * 商品内容
 * @author 柒
 *
 */
public interface TbContentDubboService {

	/**
	 * 内容查询 / 分页显示
	 * @param category
	 * @param page
	 * @param rows
	 * @return
	 */
	EasyUiDataGrid showPage(long categoryId , int page , int rows);
	
	/**
	 * 新增内容
	 * @param content
	 * @return
	 */
	int insTbContent(TbContent content);
	
	/**
	 * 显示前台广告
	 * @param count 显示数量
	 * @param isSort 是否排序
	 * @return
	 */
	List<TbContent> showPic(int count , boolean isSort);
	
	/**
	 * 内容信息修改
	 * @param content
	 * @return
	 */
	int updTbContent(TbContent content);
}
