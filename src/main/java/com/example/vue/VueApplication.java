package com.example.vue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VueApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(VueApplication.class, args);
		System.out.println("服务已启动");
	}

}
