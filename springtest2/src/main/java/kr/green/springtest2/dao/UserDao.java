package kr.green.springtest2.dao;

import org.apache.ibatis.annotations.Param;

import kr.green.springtest2.vo.UserVo;

public interface UserDao {
	public String getPw(@Param("id")String id);

	public UserVo getUser(@Param("id")String id);

	public void insertUser(@Param("user")UserVo user);
}
