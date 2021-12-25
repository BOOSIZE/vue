package com.example.vue.dao;

import com.example.vue.entity.Quest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestDao
{
	@Select("<script> SELECT * FROM QUEST WHERE TYPE=#{type}" +
			"<when test='name !=null'> AND NAME LIKE CONCAT('%',#{name},'%')</when>" +
			" LIMIT #{limit} OFFSET #{start} </script>")
	List<Quest> getList(String type,String name,Integer limit,Integer start);

	@Select("<script> SELECT COUNT(1) FROM QUEST WHERE TYPE=#{type}" +
			"<when test='name !=null'> AND NAME LIKE CONCAT('%',#{name},'%')</when>" +
			"</script>")
	int getSum(String type,String name);

	@Insert("INSERT INTO QUEST (NAME,TYPE,ONE,TWO,THREE,FOUR,ANSWER) VALUES " +
			"(#{name},#{type},#{one},#{two},#{three},#{four},#{answer}) ")
	int add(Quest quest);

	@Delete("DELETE FROM QUEST WHERE ID=#{id}")
	int delete(Long id);

	@Update("UPDATE QUEST SET NAME =#{name},ONE=#{one},TWO=#{two},THREE=#{three},FOUR=#{four},ANSWER=#{answer} WHERE ID=#{id}")
	int update(Quest quest);
}