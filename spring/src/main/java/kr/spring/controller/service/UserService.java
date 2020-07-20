package kr.spring.controller.service;

import kr.spring.vo.UserVo;

public interface UserService {

	boolean signUp(UserVo user);

	UserVo isSignin(UserVo user);

}
