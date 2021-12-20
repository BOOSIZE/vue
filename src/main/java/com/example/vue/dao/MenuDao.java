package com.example.vue.dao;

import com.example.vue.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MenuDao
{
	@Select("SELECT * FROM MENU WHERE ROLE=#{role} ORDER BY NUM")
	List<Menu> getList(String role);
}