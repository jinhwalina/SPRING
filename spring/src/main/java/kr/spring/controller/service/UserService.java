package kr.spring.controller.service;

import javax.servlet.http.HttpServletRequest;

import kr.spring.vo.UserVo;

public interface UserService {

	boolean signUp(UserVo user);

	UserVo isSignin(UserVo user);

	UserVo getUser(HttpServletRequest request);

	UserVo getUser(String id);

}
