package com.example.vue.controller;

import com.example.vue.entity.Menu;
import com.example.vue.entity.TableModel;
import com.example.vue.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/api/menu/")
public class MenuController
{
	@Autowired
	private MenuService menuServiceImpl;

	@RequestMapping("getList")
	@ResponseBody
	public TableModel getList(HttpSession session)
	{
		return menuServiceImpl.getList(session);
	}


}