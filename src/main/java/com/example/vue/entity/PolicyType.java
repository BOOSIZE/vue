package com.example.vue.entity;

import lombok.Data;

@Data
public class PolicyType
{
	private Long id;
	private Long parentId;
	private String name;
	private String level;
}