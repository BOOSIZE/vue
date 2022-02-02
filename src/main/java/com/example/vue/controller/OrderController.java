package com.example.vue.controller;

import com.example.vue.entity.Orderinfo;
import com.example.vue.entity.TableModel;
import com.example.vue.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/order/")
public class OrderController
{
	@Autowired
	private OrderService orderServiceImpl;

	@RequestMapping("getList")
	@ResponseBody
	public TableModel getList(String adminid,String startTime,String endTime,Integer limit, Integer page)
	{
		return orderServiceImpl.getList(adminid,startTime,endTime,limit, page);
	}

	@RequestMapping("add")
	@ResponseBody
	public TableModel add(Orderinfo orderinfo)
	{
		return orderServiceImpl.add(orderinfo);
	}

	@RequestMapping("update")
	@ResponseBody
	public TableModel update(Orderinfo orderinfo)
	{
		return orderServiceImpl.update(orderinfo);
	}

	@RequestMapping("delete")
	@ResponseBody
	public TableModel delete(Long id)

	{
		return orderServiceImpl.delete(id);
	}
}