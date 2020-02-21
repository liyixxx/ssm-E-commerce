package com.ego.portal.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ego.portal.service.TbContentService;

@Controller
public class TbContentController {

	@Resource
	private TbContentService tbContentServiceImpl;

	@RequestMapping("showPic")
	public String showPic(Model model) {
		//数据规定 --> var data = ${ad1};
		model.addAttribute("ad1", tbContentServiceImpl.showPic());
		return "index";
	}

}
