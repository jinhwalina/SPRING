package kr.spring.controller.dao;

import org.apache.ibatis.annotations.Param;

public interface UserDao {
	public String getPw(@Param("id")String id);
}
