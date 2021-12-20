package com.example.vue.service;

import com.example.vue.entity.Shop;
import com.example.vue.entity.ShopType;
import com.example.vue.entity.TableModel;

public interface ShopService
{
	TableModel getTypeList(String name,Integer limit,Integer page);

	TableModel addType(String name);

	TableModel updateType(ShopType shopType);

	TableModel getList(String name, String type,Integer limit,Integer page);

	TableModel getAllType();

	TableModel deleteType(Long id);

	TableModel add(Shop shop);

	TableModel update(Shop shop);

	TableModel delete(Long id);
}