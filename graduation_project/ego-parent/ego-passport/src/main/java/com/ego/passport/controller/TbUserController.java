package com.ego.passport.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ego.commons.pojo.EgoResult;
import com.ego.passport.service.TbUserService;
import com.ego.pojo.TbUser;

@Controller
public class TbUserController {

	@Resource
	private TbUserService tbUserServiceImpl ;
	
	/**
	 * 显示登录页面
	 * @RequestHeader("Referer") 获取请求头中的信息 返回原本网页
	 * @param url
	 * @return
	 */
	@RequestMapping("user/showLogin")
	public String showLogin(@RequestHeader(value = "Referer" , defaultValue="") String url , Model model,String interurl){
		// 地址判断
		System.out.println("转递过来的interUrl : "+interurl);
		if(interurl!=null && !interurl.equals("")){
			model.addAttribute("redirect", interurl);
		}else if (!url.equals("")){
			model.addAttribute("redirect", url);
		}
		return "login";
	}
	
	/**
	 * 登录
	 * @param user
	 * @return
	 */
	@RequestMapping("user/login")
	@ResponseBody
	public EgoResult login(TbUser user , HttpServletRequest request , HttpServletResponse  response){
		return tbUserServiceImpl.login(user,request,response);
	}
	
	/**
	 * 通过cookie值来获取用户信息
	 * 返回时根据需求判断返回方式 jsonp/json 
	 * @param token
	 * @param callback 回调函数 可选参数 有值表示jsonp 没有则是json
	 * @return
	 */
	@RequestMapping("user/token/{token}")
	@ResponseBody
	public Object getUserInfo(@PathVariable String token , String callback){
		EgoResult ego = tbUserServiceImpl.getUserInfoByToken(token);
		if(callback!=null && !callback.equals("")){
			// jsonp
			MappingJacksonValue mjv = new MappingJacksonValue(ego);
			mjv.setJsonpFunction(callback);
			return mjv ;
		}
		return ego ;
	}
	
	/**
	 * 退出
	 * @param token
	 * @param callback
	 * @return
	 */
	@RequestMapping("user/logout/{token}")
	@ResponseBody
	public Object logout(@PathVariable String token,String callback,HttpServletRequest request,HttpServletResponse response){
		EgoResult er = tbUserServiceImpl.logout(token,request,response);
		if(callback!=null&&!callback.equals("")){
			MappingJacksonValue mjv = new MappingJacksonValue(er);
			mjv.setJsonpFunction(callback);
			return mjv;
		}
		return er;
	}
	
}
