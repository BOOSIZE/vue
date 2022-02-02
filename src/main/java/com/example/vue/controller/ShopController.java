package com.example.vue.controller;

import com.example.vue.entity.Shop;
import com.example.vue.entity.ShopType;
import com.example.vue.entity.TableModel;
import com.example.vue.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/shop/")
public class ShopController
{
	@Autowired
	private ShopService shopServiceImpl;

	@RequestMapping("getTypeList")
	@ResponseBody
	public TableModel getTypeList(String name, Integer limit, Integer page)
	{
		return shopServiceImpl.getTypeList(name, limit, page);
	}

	@RequestMapping("getList")
	@ResponseBody
	public TableModel getList(String name, String type,Integer limit, Integer page)
	{
		return shopServiceImpl.getList(name,type, limit, page);
	}

	@RequestMapping("addType")
	@ResponseBody
	public TableModel addType(String name)
	{
		return shopServiceImpl.addType(name);
	}

	@RequestMapping("updateType")
	@ResponseBody
	public TableModel updateType(ShopType shopType)
	{
		return shopServiceImpl.updateType(shopType);
	}

	@RequestMapping("getAllType")
	@ResponseBody
	public TableModel getAllType()
	{
		return shopServiceImpl.getAllType();
	}

	@RequestMapping("deleteType")
	@ResponseBody
	public TableModel deleteType(Long id)
	{
		return shopServiceImpl.deleteType(id);
	}

	@RequestMapping("add")
	@ResponseBody
	public TableModel add(Shop shop)
	{
		return shopServiceImpl.add(shop);
	}

	@RequestMapping("update")
	@ResponseBody
	public TableModel update(Shop shop)
	{
		return shopServiceImpl.update(shop);
	}

	@RequestMapping("delete")
	@ResponseBody
	public TableModel delete(Long id)
	{
		return shopServiceImpl.delete(id);
	}

}