package com.ego.order.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.HttpClient;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ego.commons.pojo.EgoResult;
import com.ego.commons.utils.CookieUtils;
import com.ego.commons.utils.HttpClientUtil;
import com.ego.commons.utils.JsonUtils;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
/**
 * 登录拦截器
 * @author 柒
 *
 */
public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3)
			throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// 获取cookie值
		String cookie = CookieUtils.getCookieValue(request, "TT_TOKEN");
		// 非空判断
		if(cookie!=null && !cookie.equals("")){
			// 跨域请求获取用户信息
			String json = HttpClientUtil.doPost("http://localhost:8084/user/token/"+cookie);
			EgoResult ego = JsonUtils.jsonToPojo(json, EgoResult.class);
			// 当获取到用户信息 正确返回状态时 放行
			if(ego.getStatus() == 200){
				return true ;
			}
		}
		// 其他情况 跳转到登录界面
		// 获取到购物车界面的num信息
		String num = request.getParameter("num");
		System.out.println("interUrl : " + request.getRequestURL());
		if(num!=null && !num.equals("")){
			// %3F 转移字符 ？
			response.sendRedirect("http://localhost:8084/user/showLogin?interurl="+request.getRequestURL()+"%3Fnum="+num);
		}else{
			response.sendRedirect("http://localhost:8084/user/showLogin?interurl");
		}
		return false;
	}

}
