package com.example.vue.entity;
import lombok.Data;

@Data
public class Menu
{
	private Long id;
	private Integer num;
	private String name;
	private String path;
	private String icon;
	private String role;
}