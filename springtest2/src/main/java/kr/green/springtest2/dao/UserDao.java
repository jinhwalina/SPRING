package kr.green.springtest2.dao;

import org.apache.ibatis.annotations.Param;

public interface UserDao {
	public String getPw(@Param("id")String id);
}
