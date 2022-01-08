package com.example.vue.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.vue.dao.PolicyDao;
import com.example.vue.entity.PolicyType;
import com.example.vue.entity.TableModel;
import com.example.vue.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PolicyServiceImpl implements PolicyService
{
	@Autowired(required = false)
	private PolicyDao policyDao;

	@Override
	public TableModel getTypeList(String name, Integer limit, Integer page)
	{
		TableModel tableModel=new TableModel();
		try
		{
			tableModel.setData(policyDao.getTypeList(name,limit,limit*(page-1)));
			tableModel.setCount(policyDao.getTypeSum(name));
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
	public TableModel getByParentId()
	{
		TableModel tableModel=new TableModel();
		try
		{
			List<JSONObject> list=test("0");

			tableModel.setData(test("0"));
			if(list==null)
			{
				tableModel.setData(new ArrayList<>());
			}

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
	public TableModel addType(PolicyType policyType)
	{
		TableModel tableModel=new TableModel();
		try
		{
			policyType.setLevel("1");
			if(policyType.getParentId()!=0)
			{
				PolicyType parent=policyDao.getTypeById(policyType.getParentId());
				Integer level=Integer.valueOf(parent.getLevel())+1;
				policyType.setLevel(level+"");
			}
			policyDao.addType(policyType);


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
	public TableModel updateType(PolicyType policyType)
	{
		TableModel tableModel=new TableModel();
		try
		{

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
			int count=policyDao.checkCount(id);
			if(count>0)
			{
				tableModel.setCode(500);
				tableModel.setSuccess(false);
				tableModel.setMsg("该分类下存在子级，无法删除");
				return tableModel;
			}
			policyDao.deleteType(id);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tableModel.setCode(500);
			tableModel.setSuccess(false);
		}
		return tableModel;
	}

	public List<JSONObject>  test(String parentId)
	{
		List<JSONObject> jsonObjectList=new ArrayList<>();
		List<PolicyType> list=policyDao.getByParentId(parentId);
		for(PolicyType policyType :list)
		{
			JSONObject object=new JSONObject();
			object.put("label",policyType.getName());
			object.put("value",policyType.getId());
			object.put("children",test(policyType.getId()+""));
			jsonObjectList.add(object);
		}
		if(list.size()==0)
		{
			return null;
		}
		return jsonObjectList;
	}
}