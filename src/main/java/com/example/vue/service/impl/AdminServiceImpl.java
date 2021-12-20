package com.example.vue.service.impl;

import com.example.vue.dao.AdminDao;
import com.example.vue.entity.Admin;
import com.example.vue.entity.TableModel;
import com.example.vue.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@Service
@Transactional
public class AdminServiceImpl implements AdminService
{
	@Autowired(required = false)
	private AdminDao adminDao;


	@Override
	public TableModel getList(String name,String type,Integer limit, Integer page)
	{
		TableModel tableModel=new TableModel();
		try
		{
			tableModel.setCount(adminDao.getSum(name,type));
			tableModel.setData(adminDao.getList(name,type,limit,limit*(page-1)));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tableModel.setCode(500);
			tableModel.setSuccess(false);
		}
		return tableModel;
	}

	@Override
	public TableModel getAllAdmin()
	{
		TableModel tableModel = new TableModel();
		try
		{
			tableModel.setData(adminDao.getAllAdmin());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tableModel.setSuccess(false);
			tableModel.setCode(500);
		}
		return tableModel;
	}

	@Override
	public TableModel logout(HttpSession session)
	{
		TableModel tableModel = new TableModel();
		try
		{
			session.removeAttribute("admin");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tableModel.setCode(500);
			tableModel.setSuccess(false);
		}
		return tableModel;
	}

	@Override
	public TableModel checkLogin(HttpSession session)
	{
		TableModel tableModel=new TableModel();
		try
		{
			Admin admin=(Admin) session.getAttribute("admin");
			if(admin==null)
			{
				tableModel.setCode(500);
				tableModel.setSuccess(false);
				return tableModel;
			}
			tableModel.setObj(admin);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tableModel.setCode(500);
			tableModel.setSuccess(false);
		}
		return tableModel;
	}

	@Override
	public TableModel login(HttpSession session,Admin admin)
	{
		TableModel tableModel=new TableModel();
		try
		{
			Admin login=adminDao.getAdminByAccount(admin.getAccount());
			if(login==null)
			{
				tableModel.setCode(500);
				tableModel.setSuccess(false);
				tableModel.setMsg("该账号不存在");
				return tableModel;
			}
			if(!admin.getPass().equals(login.getPass()))
			{
				tableModel.setCode(500);
				tableModel.setSuccess(false);
				tableModel.setMsg("密码有误");
				return tableModel;
			}
			tableModel.setObj(login);
			session.setAttribute("admin",login);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tableModel.setCode(500);
			tableModel.setSuccess(false);
		}
		return tableModel;
	}

	@Override
	public TableModel update(Admin admin)
	{

		TableModel tableModel=new TableModel();
		try
		{
			adminDao.update(admin);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tableModel.setCode(500);
			tableModel.setSuccess(false);
		}
		return tableModel;
	}

	@Override
	public TableModel add(Admin admin)
	{
		TableModel tableModel=new TableModel();
		try
		{
			int count=adminDao.checkSum(admin.getAccount());
			if(count>0)
			{
				tableModel.setCode(500);
				tableModel.setSuccess(false);
				tableModel.setMsg("该账号已存在");
				return tableModel;
			}

			adminDao.add(admin);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tableModel.setCode(500);
			tableModel.setSuccess(false);
			tableModel.setMsg("新增失败");
		}
		return tableModel;
	}

	@Override
	public TableModel delete(Long id)
	{
		TableModel tableModel=new TableModel();
		try
		{
			adminDao.delete(id);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tableModel.setCode(500);
			tableModel.setSuccess(false);
		}
		return tableModel;
	}
}