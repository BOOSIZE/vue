package com.example.vue.controller;

import com.example.vue.entity.Admin;
import com.example.vue.entity.TableModel;
import com.example.vue.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/api/admin/")
public class AdminController
{
	@Autowired
	private AdminService adminServiceImpl;

	@RequestMapping("getList")
	@ResponseBody
	public TableModel getList(String name,String type,Integer limit,Integer page)
	{
		return adminServiceImpl.getList(name,type,limit, page);
	}

	@RequestMapping("delete")
	@ResponseBody
	public TableModel delete(Long id)
	{
		return adminServiceImpl.delete(id);
	}

	@RequestMapping("add")
	@ResponseBody
	public TableModel add(Admin admin)
	{
		return adminServiceImpl.add(admin);
	}


	@RequestMapping("update")
	@ResponseBody
	public TableModel update(Admin admin)
	{
		return adminServiceImpl.update(admin);
	}

	@RequestMapping("login")
	@ResponseBody
	public TableModel login(HttpSession session,Admin admin)
	{
		return adminServiceImpl.login(session, admin);
	}

	@RequestMapping("checkLogin")
	@ResponseBody
	public TableModel checkLogin(HttpSession session)
	{
		return adminServiceImpl.checkLogin(session);
	}

	@RequestMapping("logout")
	@ResponseBody
	public TableModel logout(HttpSession session)
	{
		return adminServiceImpl.logout(session);
	}

	@RequestMapping("getAllAdmin")
	@ResponseBody
	public TableModel getAllAdmin()
	{
		return adminServiceImpl.getAllAdmin();
	}



}
