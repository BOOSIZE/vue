package com.example.vue.dao;

import com.example.vue.entity.Ware;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface WareDao
{
	@Select("<script> SELECT * FROM WARE WHERE 1=1  " +
			"<when test='name!=null' > AND NAME LIKE CONCAT('%',#{name},'%') </when>" +
			"<when test='startTime!=null and startTime.length>0' > AND CREATEDATE &gt;= #{startTime} </when>" +
			"<when test='endTime!=null and endTime.length>0' > AND CREATEDATE &lt;= #{endTime} </when>" +
			" ORDER BY CREATEDATE DESC LIMIT #{limit} OFFSET #{start} </script> ")
	List<Ware> getList(String name,String startTime,String endTime,Integer limit,Integer start);

	@Select("<script> SELECT COUNT(1) FROM WARE WHERE 1=1" +
			"<when test='name!=null' > AND NAME LIKE CONCAT('%',#{name},'%') </when>" +
			"<when test='startTime!=null and startTime.length>0' > AND CREATEDATE &gt;= #{startTime} </when>" +
			"<when test='endTime!=null and endTime.length>0' > AND CREATEDATE &lt;= #{endTime} </when>" +
			"</script>")
	int getSum(String name,String startTime,String endTime);

	@Insert("INSERT INTO WARE (NAME,MSG,IMG,CREATEDATE) VALUES (#{name},#{msg},#{img},now())")
	int add(Ware ware);

	@Delete("DELETE FROM WARE WHERE ID=#{id}")
	int delete(Long id);

	@Update("<script> UPDATE WARE SET NAME=#{name},MSG=#{msg}" +
			"<when test='img!=null and img.length>0'> ,IMG=#{img}</when>" +
			" WHERE ID=#{id} </script>")
	int update(Ware ware);
}