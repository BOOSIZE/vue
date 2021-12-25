package com.example.vue.controller;

import com.example.vue.entity.Quest;
import com.example.vue.entity.TableModel;
import com.example.vue.service.QuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/quest/")
public class QuestController
{
	@Autowired
	private QuestService questServiceImpl;

	@RequestMapping("getList")
	@ResponseBody
	public TableModel getList(String type,String name,Integer limit ,Integer page)
	{
		return questServiceImpl.getList(type,name, limit, page);
	}

	@RequestMapping("add")
	@ResponseBody
	public TableModel add(Quest quest)
	{
		return questServiceImpl.add(quest);
	}

	@RequestMapping("update")
	@ResponseBody
	public TableModel update(Quest quest)
	{
		return questServiceImpl.update(quest);
	}

	@RequestMapping("delete")
	@ResponseBody
	public TableModel delete(Long id)
	{
		return questServiceImpl.delete(id);
	}
}