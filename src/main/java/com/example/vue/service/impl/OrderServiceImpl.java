package com.example.vue.service.impl;

import com.example.vue.dao.OrderDao;
import com.example.vue.entity.Orderinfo;
import com.example.vue.entity.TableModel;
import com.example.vue.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService
{
	@Autowired(required = false)
	private OrderDao orderDao;

	@Override
	public TableModel delete(Long id)
	{
		TableModel tableModel=new TableModel();
		try
		{
			orderDao.delete(id);
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
	public TableModel add(Orderinfo orderinfo)
	{
		TableModel tableModel=new TableModel();
		try
		{
			int count=orderDao.check(orderinfo);
			if(count>0)
			{
				tableModel.setSuccess(false);
				tableModel.setCode(500);
				tableModel.setMsg("该营养师在当天已有排班!");
				return  tableModel;
			}
			orderDao.add(orderinfo);
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
	public TableModel update(Orderinfo orderinfo)
	{
		TableModel tableModel=new TableModel();
		try
		{
			int count=orderDao.check(orderinfo);
			Orderinfo old=orderDao.getById(orderinfo.getId());
			if(count>0 && (!old.getTime().equals(orderinfo.getTime()) || !old.getAdminid().equals(orderinfo.getAdminid())) )
			{
				tableModel.setSuccess(false);
				tableModel.setCode(500);
				tableModel.setMsg("该营养师在当天已有排班!");
				return  tableModel;
			}
			orderDao.update(orderinfo);
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
	public TableModel getList(String adminid,String startTime,String endTime,Integer limit, Integer page)
	{
		TableModel tableModel=new TableModel();
		try
		{
			tableModel.setCount(orderDao.getSum(adminid, startTime, endTime));
			tableModel.setData(orderDao.getList(adminid, startTime, endTime,limit,limit*(page-1)));
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