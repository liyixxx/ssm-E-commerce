package com.ego.order.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ego.commons.pojo.EgoResult;
import com.ego.order.pojo.MyOrderParam;
import com.ego.order.service.TbOrderService;

@Controller
public class TbOrderController {

	@Resource
	private TbOrderService tbOrderServiceImpl ;
	
	/**
	 * 显示确认页面
	 * @param model
	 * @param ids @RequestParam("id") --> 传入的id会放入到ids中
	 * @param request
	 * @return
	 */
	@RequestMapping("order/order-cart.html")
	public String showCartOrder(Model model,@RequestParam("id") List<Long> ids,HttpServletRequest request){
		model.addAttribute("cartList", tbOrderServiceImpl.showOrderCart(ids, request));
		return "order-cart";
	}
	
	/**
	 * 创建订单
	 * @return
	 */
	@RequestMapping("order/create.html")
	public String createOrder(Model model ,MyOrderParam param,HttpServletRequest request){
		HttpSession session = request.getSession();
		if(session.getAttribute("form_submit")!=null && session.getAttribute("form_submit").equals("true")){
			return "my-orders";
		}
		EgoResult ego = tbOrderServiceImpl.create(param, request);
		if(ego.getStatus()==200){
			session.setAttribute("form_submit", "true");
			return "my-orders";
		}else{
			request.setAttribute("message", "订单创建失败");
			return "error/exception";
		}
	}
	
	
}
