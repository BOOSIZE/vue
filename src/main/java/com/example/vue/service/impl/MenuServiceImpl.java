package com.example.vue.service.impl;

import com.example.vue.dao.MenuDao;
import com.example.vue.entity.Admin;
import com.example.vue.entity.TableModel;
import com.example.vue.entity.Ware;
import com.example.vue.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;

@Service
@Transactional
public class MenuServiceImpl implements MenuService
{
	@Autowired(required = false)
	private MenuDao menuDao;

	@Override
	public TableModel getList(HttpSession session)
	{
		TableModel tableModel=new TableModel();
		try
		{
			Admin admin=(Admin) session.getAttribute("admin");
			tableModel.setData(menuDao.getList(admin.getRole()));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tableModel.setSuccess(false);
			tableModel.setCode(500);
		}
		return tableModel;
	}


}