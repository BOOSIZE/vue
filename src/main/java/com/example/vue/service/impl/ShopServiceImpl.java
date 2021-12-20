package com.example.vue.service.impl;

import com.example.vue.dao.ShopDao;
import com.example.vue.entity.Shop;
import com.example.vue.entity.ShopType;
import com.example.vue.entity.TableModel;
import com.example.vue.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.awt.geom.AreaOp;

@Service
@Transactional
public class ShopServiceImpl implements ShopService
{
	@Autowired(required = false)
	private ShopDao shopDao;

	@Override
	public TableModel addType(String name)
	{
		TableModel tableModel=new TableModel();
		try
		{
			int count=shopDao.checkName(name);
			if(count>0)
			{
				tableModel.setCode(500);
				tableModel.setSuccess(false);
				tableModel.setMsg("该名称已存在");
				return  tableModel;
			}
			shopDao.addType(name);
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
	public TableModel add(Shop shop)
	{
		TableModel tableModel=new TableModel();
		try
		{
			int sum=shopDao.checkShopName(shop.getName());
			if(sum>0)
			{
				tableModel.setCode(500);
				tableModel.setSuccess(false);
				tableModel.setMsg("该商品名称已存在");
				return tableModel;
			}
			shopDao.add(shop);
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
	public TableModel delete(Long id)
	{
		TableModel tableModel=new TableModel();
		try
		{
			shopDao.delete(id);
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
	public TableModel update(Shop shop)
	{
		TableModel tableModel=new TableModel();
		try
		{
			Shop old=shopDao.getById(shop.getId());
			int sum=shopDao.checkShopName(shop.getName());
			if(sum>0 && ! shop.getName().equals(old.getName()))
			{
				tableModel.setCode(500);
				tableModel.setSuccess(false);
				tableModel.setMsg("该商品名称已存在");
				return tableModel;
			}
			shopDao.update(shop);
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
	public TableModel deleteType(Long id)
	{
		TableModel tableModel=new TableModel();
		try
		{
			int sum=shopDao.checkTypeSum(id);
			if(sum>0)
			{
				tableModel.setCode(500);
				tableModel.setSuccess(false);
				tableModel.setMsg("该分类下存在商品，无法删除");
				return tableModel;
			}
			shopDao.deleteType(id);
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
	public TableModel getAllType()
	{
		TableModel tableModel=new TableModel();
		try
		{
			tableModel.setData(shopDao.getAllType());
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
	public TableModel getList(String name, String type,Integer limit, Integer page)
	{
			TableModel tableModel=new TableModel();
		try
		{
			tableModel.setCount(shopDao.getSum(name, type));
			tableModel.setData(shopDao.getList(name, type,limit,limit*(page-1)));
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
	public TableModel updateType(ShopType shopType)
	{
		TableModel tableModel=new TableModel();
		try
		{
			int count=shopDao.checkName(shopType.getName());
			if(count>0)
			{
				tableModel.setCode(500);
				tableModel.setSuccess(false);
				tableModel.setMsg("该名称已存在");
				return  tableModel;
			}
			shopDao.updateType(shopType);
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
	public TableModel getTypeList(String name, Integer limit, Integer page)
	{
		TableModel tableModel=new TableModel();
		try
		{
			tableModel.setCount(shopDao.getTypeSum(name));
			tableModel.setData(shopDao.getTypeList(name,limit,limit*(page-1)));
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