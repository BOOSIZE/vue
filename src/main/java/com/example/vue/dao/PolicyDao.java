package com.example.vue.dao;

import com.example.vue.entity.PolicyType;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PolicyDao
{
	@Select("<script> SELECT * FROM POLICYTYPE WHERE 1=1 " +
			"<when test='name!=null'> AND NAME LIKE CONCAT('%',#{name},'%')</when>" +
			" ORDER BY LEVEL LIMIT #{limit} OFFSET #{start} </script>")
	List<PolicyType> getTypeList(String name,Integer limit,Integer start);

	@Select("<script> SELECT COUNT(1) FROM POLICYTYPE WHERE 1=1 " +
			"<when test='name!=null'> AND NAME LIKE CONCAT('%',#{name},'%')</when>" +
			"</script>")
	int getTypeSum(String  name);


	@Select("SELECT * FROM POLICYTYPE WHERE PARENTID=#{parentId}")
	List<PolicyType> getByParentId(String parentId);

	@Insert("INSERT INTO POLICYTYPE (NAME,PARENTID,LEVEL) VALUES (#{name},#{parentId},#{level}) ")
	int addType(PolicyType policyType);

	@Update("UPDATE POLICYTYPE SET NAME =#{name},PARENTID=#{parentId},LEVEL=#{level} WHERE ID=#{id}")
	int updateType(PolicyType type);

	@Delete("DELETE FROM POLICYTYPE WHERE ID=#{id}")
	int deleteType(Long id);

	@Select("SELECT * FROM POLICYTYPE WHERE ID=#{id}")
	PolicyType getTypeById(Long id);

	@Select("SELECT COUNT(1) FROM POLICYTYPE WHERE PARENTID=#{parentId}")
	int checkCount(Long parentId);
}