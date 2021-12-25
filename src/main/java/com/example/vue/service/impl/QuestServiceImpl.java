package com.example.vue.service.impl;

import com.example.vue.dao.QuestDao;
import com.example.vue.entity.Quest;
import com.example.vue.entity.TableModel;
import com.example.vue.service.QuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class QuestServiceImpl implements QuestService
{
	@Autowired(required = false)
	private QuestDao questDao;

	@Override
	public TableModel getList(String type,String name, Integer limit, Integer page)
	{
		TableModel tableModel=new TableModel();
		try
		{
			tableModel.setCount(questDao.getSum(type,name));
			tableModel.setData(questDao.getList(type,name,limit,limit*(page-1)));
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
	public TableModel add(Quest quest)
	{
		TableModel tableModel=new TableModel();
		try
		{
			questDao.add(quest);
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
	public TableModel update(Quest quest)
	{
		TableModel tableModel=new TableModel();
		try
		{
			questDao.update(quest);
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
	public TableModel delete(Long id)
	{
		TableModel tableModel=new TableModel();
		try
		{
			questDao.delete(id);
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