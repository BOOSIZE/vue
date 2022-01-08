package com.example.vue.controller;

import com.example.vue.entity.PolicyType;
import com.example.vue.entity.TableModel;
import com.example.vue.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/policy/")
public class PolicyController
{
	@Autowired
	private PolicyService policyServiceImpl;

	@RequestMapping("getTypeList")
	@ResponseBody
	public TableModel getTypeList(String name, Integer limit, Integer page)
	{
		return policyServiceImpl.getTypeList(name, limit, page);
	}

	@RequestMapping("getByParentId")
	@ResponseBody
	public TableModel getByParentId()
	{
		return policyServiceImpl.getByParentId();
	}

	@RequestMapping("addType")
	@ResponseBody
	public TableModel addType(PolicyType policyType)
	{
		return policyServiceImpl.addType(policyType);
	}

	@RequestMapping("updateType")
	@ResponseBody
	public TableModel updateType(PolicyType policyType)
	{
		return policyServiceImpl.updateType(policyType);
	}

	@RequestMapping("deleteType")
	@ResponseBody
	public TableModel deleteType(Long id)
	{
		return policyServiceImpl.deleteType(id);
	}
}