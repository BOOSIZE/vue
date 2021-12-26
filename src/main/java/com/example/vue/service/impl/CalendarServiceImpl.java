package com.example.vue.service.impl;

import com.example.vue.dao.ChapterDao;
import com.example.vue.entity.Chapter;
import com.example.vue.entity.Fileinfo;
import com.example.vue.entity.TableModel;
import com.example.vue.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Service
@Transactional
public class CalendarServiceImpl implements CalendarService
{
	@Autowired(required = false)
	private ChapterDao chapterDao;

	@Value("${fileUrl}")
	private String uploadUrl;

	@Override
	public TableModel getList(String name, Integer limit, Integer page)
	{
		TableModel tableModel=new TableModel();
		try
		{
			tableModel.setCount(chapterDao.getSum(name));
			tableModel.setData(chapterDao.getList(name,limit,limit*(page-1)));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tableModel.setSuccess(false);
			tableModel.setCode(500);
		}
		return tableModel;
	}


	@Override
	public TableModel addFile(HttpServletRequest request, MultipartFile[] files)
	{
		TableModel tableModel=new TableModel();
		try
		{
			Long cid=Long.valueOf(request.getParameter("cid"));
			if(files!=null)
			{
				for(MultipartFile file : files)
				{
					String fileName=file.getOriginalFilename();
					int index=fileName.lastIndexOf('.');
					String fName=fileName.substring(0,index);
					String type=fileName.substring(index);
					File newFile=new File(uploadUrl+"/"+fileName);
					int count=1;
					while (newFile.exists())
					{
						newFile=new File(uploadUrl+"/"+fName+"副本"+count+type);
						count++;
					}
					file.transferTo(newFile);
					Fileinfo fileinfo=new Fileinfo();
					fileinfo.setCid(cid);
					fileinfo.setName(newFile.getName());
					chapterDao.addFile(fileinfo);
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tableModel.setSuccess(false);
			tableModel.setCode(500);
		}
		return tableModel;
	}

	@Override
	public TableModel fileList(Long cid, Integer limit, Integer page)
	{
		TableModel tableModel=new TableModel();
		try
		{
			tableModel.setCount(chapterDao.getFileSum(cid));
			tableModel.setData(chapterDao.getFileList(cid,limit,limit*(page-1)));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tableModel.setSuccess(false);
			tableModel.setCode(500);
		}
		return tableModel;
	}

	@Override
	public TableModel add(String name)
	{
		TableModel tableModel=new TableModel();
		try
		{
			int count=chapterDao.checkCountr(name);
			if(count>0)
			{
				tableModel.setSuccess(false);
				tableModel.setCode(500);
				tableModel.setMsg("该名称已存在");
				return tableModel;
			}
			chapterDao.add(name);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tableModel.setSuccess(false);
			tableModel.setCode(500);
		}
		return tableModel;
	}

	@Override
	public TableModel update(Long id, String name)
	{
		TableModel tableModel=new TableModel();
		try
		{
			Chapter chapter=chapterDao.getById(id);
			int count=chapterDao.checkCountr(name);
			if(!name.equals(chapter.getName()) && count>0)
			{
				tableModel.setSuccess(false);
				tableModel.setCode(500);
				tableModel.setMsg("该名称已存在");
				return tableModel;
			}
			chapterDao.update(id, name);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tableModel.setSuccess(false);
			tableModel.setCode(500);
		}
		return tableModel;
	}

	@Override
	public TableModel delete(Long id)
	{
		TableModel tableModel=new TableModel();
		try
		{
			int count=chapterDao.getFileSum(id);
			if(count>0)
			{
				tableModel.setSuccess(false);
				tableModel.setCode(500);
				tableModel.setMsg("该章节下有文件，无法删除");
				return tableModel;
			}
			chapterDao.delete(id);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tableModel.setSuccess(false);
			tableModel.setCode(500);
		}
		return tableModel;
	}

	@Override
	public TableModel deleteFile(Long id)
	{
		TableModel tableModel=new TableModel();
		try
		{
			chapterDao.deleteFile(id);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tableModel.setSuccess(false);
			tableModel.setCode(500);
		}
		return tableModel;
	}
}