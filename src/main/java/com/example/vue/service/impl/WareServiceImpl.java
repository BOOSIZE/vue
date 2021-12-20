package com.example.vue.service.impl;

import com.example.vue.dao.WareDao;
import com.example.vue.entity.TableModel;
import com.example.vue.entity.Ware;
import com.example.vue.service.WareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Service
@Transactional
public class WareServiceImpl implements WareService
{
	@Autowired(required =  false)
	private WareDao wareDao;

	@Value("${fileUrl}")
	private String uploadUrl;

	@Override
	public TableModel getList(String name,String startTime,String endTime, Integer limit, Integer page)
	{
		TableModel tableModel=new TableModel();
		try
		{
			if(endTime!=null && !"".equals(endTime))
			{
				endTime+=" 23:59:59";
			}
			tableModel.setCount(wareDao.getSum(name,startTime,endTime));
			tableModel.setData(wareDao.getList(name,startTime,endTime,limit,limit*(page-1)));
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
			wareDao.delete(id);
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
	public TableModel add(HttpServletRequest request, MultipartFile file)
	{
		TableModel tableModel=new TableModel();
		try
		{
			Ware ware=new Ware();
			ware.setName(request.getParameter("name"));
			ware.setMsg(request.getParameter("msg"));
			if(file!=null && file.getOriginalFilename().length()>0)
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
				ware.setImg(newFile.getName());
				wareDao.add(ware);
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
	public TableModel update(HttpServletRequest request, MultipartFile file)
	{
		TableModel tableModel=new TableModel();
		try
		{
			Ware ware=new Ware();
			ware.setId(Long.valueOf(request.getParameter("id")));
			ware.setName(request.getParameter("name"));
			ware.setMsg(request.getParameter("msg"));
			if(file!=null && file.getOriginalFilename().length()>0)
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
				ware.setImg(newFile.getName());
			}
			wareDao.update(ware);
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