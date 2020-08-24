package kr.green.project.dao;

import org.apache.ibatis.annotations.Param;
 
public interface UserDao {

	public String getNickname(@Param("mail")String mail);
}
