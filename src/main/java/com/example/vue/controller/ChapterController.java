package com.example.vue.controller;

import com.example.vue.entity.TableModel;
import com.example.vue.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("/chapter/")
public class ChapterController
{
	@Autowired
	private CalendarService calendarServiceImpl;

	@Value("${fileUrl}")
	private String uploadUrl;

	@RequestMapping("getList")
	@ResponseBody
	public TableModel getList(String name, Integer limit, Integer page)
	{
		return calendarServiceImpl.getList(name, limit, page);
	}

	@RequestMapping("fileList")
	@ResponseBody
	public TableModel fileList(Long cid, Integer limit, Integer page)
	{
		return calendarServiceImpl.fileList(cid, limit, page);
	}

	@RequestMapping("add")
	@ResponseBody
	public TableModel add(String name)
	{
		return calendarServiceImpl.add(name);
	}

	@RequestMapping("update")
	@ResponseBody
	public TableModel update(Long id, String name)
	{
		return calendarServiceImpl.update(id,name);
	}

	@RequestMapping("delete")
	@ResponseBody
	public TableModel delete(Long id)
	{
		return calendarServiceImpl.delete(id);
	}

	@RequestMapping("deleteFile")
	@ResponseBody
	public TableModel deleteFile(Long id)
	{
		return calendarServiceImpl.deleteFile(id);
	}


	@RequestMapping("addFile")
	@ResponseBody
	public TableModel addFile(HttpServletRequest request, MultipartFile[] files)
	{
		return calendarServiceImpl.addFile(request,files);
	}

	@RequestMapping("down")
	public void down(HttpServletRequest request, HttpServletResponse response)
	{
		String fileName=request.getParameter("fileName");

		File file=new File(uploadUrl+"/"+fileName);

		if (file.exists())
		{
			FileInputStream fis = null;
			//BufferedInputStream bis = null;
			try
			{
				response.reset();
				// 设置强制下载不打开
				//response.setContentType("application/force-download");
				//避免中文乱码
				response.setHeader("Connection", "close");
				//设置传输的类型
				response.setHeader("Content-Type", "application/octet-stream");
				response.setHeader("Content-Transfer-Encoding", "chunked");
				response.setHeader("Access-Control-Allow-Origin", "*");
				response.setHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes("utf-8"), "ISO-8859-1"));
				response.setContentType("application/OCTET-STREAM");

				fis = new FileInputStream(file);
				byte[] buffer = new byte[fis.available()];
				OutputStream os = response.getOutputStream();
				fis.read(buffer);
				os.write(buffer);
				os.flush();
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
	}
}