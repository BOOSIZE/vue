package com.example.vue.service;

import com.example.vue.entity.Orderinfo;
import com.example.vue.entity.TableModel;

public interface OrderService
{
	TableModel getList(String adminid,String startTime,String endTime,Integer limit,Integer page);

	TableModel add(Orderinfo orderinfo);

	TableModel update(Orderinfo orderinfo);

	TableModel delete(Long id);
}