package com.example.vue.service;

import com.example.vue.entity.Quest;
import com.example.vue.entity.TableModel;

public interface QuestService
{
	TableModel getList(String type,String name,Integer limit,Integer page);

	TableModel add(Quest quest);

	TableModel update(Quest quest);

	TableModel delete(Long id);
}