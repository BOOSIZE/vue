package com.example.vue.entity;
import lombok.Data;

@Data
public class Admin
{
	private Long id;
	private String account;
	private String pass;
	private String name;
	private String role;
	private String sex;
	private String sexStr;
	private String tel;
	private String addr;
	private String type;
	private String typeStr;
}