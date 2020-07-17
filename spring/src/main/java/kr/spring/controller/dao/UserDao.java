package kr.spring.controller.dao;

import org.apache.ibatis.annotations.Param;

import kr.spring.vo.UserVo;

public interface UserDao {

	public void insertUser(@Param("user")UserVo user);

	public UserVo getUser(@Param("id")String id);
	
}
