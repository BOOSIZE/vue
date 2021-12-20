package com.example.vue.dao;

import com.example.vue.entity.Shop;
import com.example.vue.entity.ShopType;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ShopDao
{
	@Select("SELECT T.*,(SELECT COUNT(1) FROM SHOP WHERE TYPEID=T.ID) AS NUM FROM SHOPTYPE T WHERE T.NAME LIKE CONCAT('%',#{name},'%') ORDER BY ID  LIMIT #{limit} OFFSET #{start}")
	List<ShopType> getTypeList(String name,Integer limit,Integer start);

	@Select("SELECT COUNT(*) FROM SHOPTYPE WHERE NAME LIKE CONCAT('%',#{name},'%')")
	int getTypeSum(String name);

	@Select("SELECT COUNT(*) FROM SHOPTYPE WHERE NAME=#{name}")
	int checkName(String name);

	@Insert("INSERT INTO SHOPTYPE (NAME) VALUES (#{name})")
	int addType(String name);

	@Update("UPDATE SHOPTYPE SET NAME=#{name} WHERE ID=#{id}")
	int updateType(ShopType shopType);

	@Select("<script> SELECT A.* ,B.NAME AS typeName FROM SHOP A LEFT JOIN SHOPTYPE B ON A.TYPEID=B.ID WHERE 1=1 " +
			"<when test='name !=null and name.length>0'> and A.NAME LIKE CONCAT('%',#{name},'%') </when> " +
			"<when test='type !=null and type.length>0 '> and A.TYPEID =#{type} </when> " +
			"LIMIT #{limit} OFFSET #{start} </script>")
	List<Shop> getList(String name,String type,Integer limit,Integer start);

	@Select("<script> SELECT COUNT(*) FROM SHOP A WHERE 1=1" +
			"<when test='name !=null'> and A.NAME LIKE CONCAT('%',#{name},'%') </when> " +
			"<when test='type !=null and type.length>0 '> and A.TYPEID =#{type} </when> " +
			"</script>")
	int getSum(String name,String type);

	@Select("SELECT * FROM SHOPTYPE")
	List<ShopType> getAllType();

	@Delete("DELETE FROM SHOPTYPE WHERE ID=#{id}")
	int deleteType(Long id);

	@Select("SELECT COUNT(1) FROM SHOP WHERE TYPEID=#{id}")
	int checkTypeSum(Long id);

	@Select("SELECT COUNT(1) FROM SHOP WHERE NAME=#{name}")
	int checkShopName(String name);

	@Insert("INSERT INTO SHOP (NAME,TYPEID) VALUES (#{name},#{typeid})")
	int add(Shop shop);

	@Update("UPDATE SHOP SET NAME=#{name},TYPEID=#{typeid} WHERE ID=#{id}")
	int update(Shop shop);

	@Select("SELECT * FROM SHOP WHERE ID=#{id}")
	Shop getById(Long id);

	@Delete("DELETE FROM SHOP WHERE ID=#{id}")
	int delete(Long id);
}