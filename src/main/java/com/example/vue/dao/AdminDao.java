package com.example.vue.dao;

import com.example.vue.entity.Admin;
import com.example.vue.entity.PageData;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface AdminDao
{
	@Select(" <script> SELECT A.*,(CASE WHEN A.SEX='man' then '男' WHEN A.SEX='woman' THEN '女' ELSE '' END) AS sexStr," +
			" (CASE WHEN A.TYPE='1' then '普通用户' WHEN A.TYPE='2' THEN 'VIP用户' ELSE '' END) AS typeStr " +
			" FROM ADMIN A  WHERE A.ROLE='2'" +
			"<when test='name!=null and name.length>0'> AND A.NAME LIKE CONCAT('%',#{name},'%') </when> " +
			"<when test='type!=null and type.length>0'> AND A.TYPE =#{type} </when> " +
			"LIMIT #{limit} OFFSET #{start} </script>")
	List<Admin> getList(String name, String type, Integer limit, Integer start);

	@Select("<script> SELECT COUNT(*) FROM ADMIN WHERE ROLE='2' " +
			"<when test='name!=null and name.length>0'> AND NAME LIKE CONCAT('%',#{name},'%') </when> " +
			"<when test='type!=null and type.length>0'> AND TYPE =#{type} </when> " +
			"</script>")
	int getSum(String name,String type);

	@Delete("DELETE FROM ADMIN WHERE ID=#{id}")
	int delete(Long id);

	@Insert("INSERT INTO ADMIN (ACCOUNT,PASS,NAME,TEL,ADDR,SEX,TYPE) VALUES (" +
			"#{account},#{pass},#{name},#{tel},#{addr},#{sex},#{type}" +
			")")
	int add(Admin admin);

	@Update("UPDATE ADMIN SET NAME=#{name},TEL=#{tel},ADDR=#{addr},SEX=#{sex},TYPE=#{type} WHERE ID=#{id}")
	int update(Admin admin);

	@Select("SELECT COUNT(*) FROM ADMIN WHERE ACCOUNT=#{account}")
	int checkSum(String account);

	@Select("SELECT * FROM ADMIN WHERE ACCOUNT=#{account} ")
	Admin getAdminByAccount(String account);

	@Select("SELECT * FROM ADMIN WHERE ROLE='2'")
	List<Admin> getAllAdmin();



}