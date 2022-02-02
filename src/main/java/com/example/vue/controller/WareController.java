package com.example.vue.controller;

import com.example.vue.entity.TableModel;
import com.example.vue.service.WareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@Controller
@RequestMapping("/ware/")
public class WareController
{
	@Autowired
	private WareService wareServiceImpl;

	@Value("${fileUrl}")
	private String fileUrl;

	@RequestMapping("getImg")
	public void getImg(HttpServletRequest request, HttpServletResponse response)
	{
		String name=request.getParameter("name");
		FileInputStream fis=null;
		try
		{
			String url=fileUrl+"/"+name;
			response.setContentType("image/gif");
			OutputStream out = response.getOutputStream();
			File file=new File(url);
			fis=new FileInputStream(file);

			byte[] bytes = new byte[fis.available()];
			fis.read(bytes);
			out.write(bytes);
			out.flush();


		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (fis != null)
			{
				try
				{
					fis.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}

	}

	@RequestMapping("getList")
	@ResponseBody
	public TableModel getList(String name, String startTime,String endTime,Integer limit, Integer page)
	{
		return wareServiceImpl.getList(name,startTime,endTime, limit, page);
	}


	@RequestMapping("delete")
	@ResponseBody
	public TableModel delete(Long id)
	{
		return wareServiceImpl.delete(id);
	}

	@RequestMapping("add")
	@ResponseBody
	public TableModel add(HttpServletRequest request, MultipartFile file)
	{
		return wareServiceImpl.add(request,file);
	}

	@RequestMapping("update")
	@ResponseBody
	public TableModel update(HttpServletRequest request, MultipartFile file)
	{
		return wareServiceImpl.update(request,file);
	}

}