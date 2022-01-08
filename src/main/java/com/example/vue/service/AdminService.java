package com.example.vue.service;

import com.example.vue.entity.Admin;
import com.example.vue.entity.TableModel;

import javax.servlet.http.HttpSession;

public interface AdminService
{
	TableModel getList(String name, String type, Integer limit, Integer page);

	TableModel delete(Long id);

	TableModel add(Admin admin);

	TableModel update(Admin admin);

	TableModel login(HttpSession session, Admin admin);

	TableModel checkLogin(HttpSession session);

	TableModel logout(HttpSession session);

	TableModel getAllAdmin();

}