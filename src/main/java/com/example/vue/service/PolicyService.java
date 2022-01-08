package com.example.vue.service;

import com.example.vue.entity.PolicyType;
import com.example.vue.entity.TableModel;

public interface PolicyService
{
	TableModel getTypeList(String name,Integer limit,Integer page);

	TableModel getByParentId();

	TableModel addType(PolicyType policyType);

	TableModel updateType(PolicyType policyType);

	TableModel deleteType(Long id);
}