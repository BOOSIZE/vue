package com.example.vue.service;

import com.example.vue.entity.TableModel;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface CalendarService
{
	TableModel getList(String name,Integer limit,Integer page);

	TableModel add(String name);

	TableModel update(Long id,String name);

	TableModel delete(Long id);

	TableModel fileList(Long cid, Integer limit, Integer page);

	TableModel addFile(HttpServletRequest request, MultipartFile[] files);

	TableModel deleteFile(Long id);
}