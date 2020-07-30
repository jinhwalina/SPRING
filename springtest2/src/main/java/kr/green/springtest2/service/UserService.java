package kr.green.springtest2.service;

import kr.green.springtest2.vo.UserVo;

public interface UserService {
	 public String getPw(String id);

	public boolean signUp(UserVo user);

	public UserVo getUser(String id);

}
