package com.example.vue.dao;

import com.example.vue.entity.Orderinfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderDao
{
	@Select("<script> SELECT O.* ,A.NAME  FROM ORDERINFO O INNER JOIN ADMIN A ON A.ID=O.ADMINID" +
			" WHERE 1=1 " +
			"<when test='adminid!=null and adminid.length>0'> AND O.ADMINID=#{adminid}</when>" +
			"<when test='startTime!=null and startTime.length>0'> AND O.TIME &gt;= #{startTime}</when>" +
			"<when test='endTime!=null and endTime.length>0'> AND O.TIME &lt;= #{endTime}</when>" +
			" ORDER BY O.TIME " +
			" LIMIT #{limit} OFFSET #{start} </script>")
	List<Orderinfo> getList(String adminid,String startTime,String endTime,Integer limit,Integer start);

	@Select("<script> SELECT COUNT(*) FROM ORDERINFO O WHERE 1=1" +
			"<when test='adminid!=null and adminid.length>0'> AND O.ADMINID=#{adminid}</when>" +
			"<when test='startTime!=null and startTime.length>0'> AND O.TIME &gt;= #{startTime}</when>" +
			"<when test='endTime!=null and endTime.length>0'> AND O.TIME &lt;= #{endTime}</when>" +
			"</script>")
	int getSum(String adminid,String startTime,String endTime);

	@Select("SELECT COUNT(1) FROM ORDERINFO WHERE ADMINID=#{adminid} AND TIME=#{time}")
	int check(Orderinfo orderinfo);

	@Insert("INSERT INTO ORDERINFO (ADMINID,TIME) VALUES (#{adminid},#{time})")
	int add(Orderinfo orderinfo);

	@Update("UPDATE ORDERINFO SET ADMINID=#{adminid},TIME=#{time} WHERE ID=#{id}")
	int update(Orderinfo orderinfo);

	@Delete("DELETE FROM ORDERINFO WHERE ID=#{id}")
	int delete(Long id);

	@Select("SELECT * FROM ORDERINFO WHERE ID=#{id}")
	Orderinfo getById(Long id);
}