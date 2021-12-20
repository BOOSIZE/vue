package com.example.vue.service;

import com.example.vue.entity.TableModel;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface MenuService
{
	TableModel getList(HttpSession session);
}