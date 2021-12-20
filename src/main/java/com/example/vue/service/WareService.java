package com.example.vue.service;

import com.example.vue.entity.TableModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface WareService
{
	TableModel getList(String name,String startTime,String endTime,Integer limit,Integer page);

	TableModel add(HttpServletRequest request, MultipartFile file);

	TableModel delete(Long id);

	TableModel update(HttpServletRequest request, MultipartFile file);
}