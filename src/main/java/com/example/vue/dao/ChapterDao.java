package com.example.vue.dao;

import com.example.vue.entity.Chapter;
import com.example.vue.entity.Fileinfo;
import org.apache.ibatis.annotations.*;

import java.util.Calendar;
import java.util.List;

@Mapper
public interface ChapterDao
{
	@Select("<script> SELECT * FROM CHAPTER WHERE 1=1 " +
			"<when test='name!=null'> AND NAME LIKE CONCAT('%',#{name},'%') </when>" +
			" LIMIT #{limit} OFFSET #{start} </script>")
	List<Chapter> getList(String name,Integer limit,Integer start);

	@Select("<script> SELECT COUNT(1) FROM CHAPTER WHERE 1=1 " +
			"<when test='name!=null'> AND NAME LIKE CONCAT('%',#{name},'%') </when>" +
			"</script>")
	int getSum(String name);

	@Insert("INSERT INTO CHAPTER (NAME) VALUES (#{name})")
	int add(String name);

	@Delete("DELETE FROM CHAPTER WHERE ID=#{id}")
	int delete(Long id);

	@Update("UPDATE CHAPTER  SET NAME=#{name} WHERE ID=#{id}")
	int update(Long id,String name);

	@Select("SELECT * FROM CHAPTER WHERE ID=#{id}")
	Chapter getById(Long id);

	@Select("SELECT COUNT(1) FROM CHAPTER WHERE NAME=#{name}")
	int checkCountr(String name);


	@Select("SELECT * FROM FILE WHERE CID=#{cid} ORDER BY CREATEDATE DESC LIMIT #{limit} OFFSET #{start}")
	List<Fileinfo> getFileList(Long cid,Integer limit,Integer start);

	@Select("SELECT COUNT(1) FROM FILE WHERE CID=#{cid}")
	int getFileSum(Long cid);

	@Insert("INSERT INTO FILE (CID,NAME,CREATEDATE) VALUES (#{cid},#{name},now()) ")
	int addFile(Fileinfo fileinfo);

	@Delete("DELETE FROM FILE WHERE ID=#{id}")
	int deleteFile(Long id);
}