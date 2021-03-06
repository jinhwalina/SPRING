package kr.green.springtest2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.green.springtest2.dao.UserDao;
import kr.green.springtest2.vo.UserVo;

@Service
public class UserServiceImp implements UserService {
	
	@Autowired
    UserDao userDao;
	
	@Autowired 
	BCryptPasswordEncoder passwordEncoder; 
	
	@Override
	public String getPw(String id) {
		return userDao.getPw(id);
	}

	@Override
	public boolean signUp(UserVo user) {
		if(user == null) return false; 
		if(user.getId() == null || user.getId().length() == 0) return false; 
		if(user.getPw() == null || user.getId().length() == 0) return false; 
		if(user.getEmail() == null || user.getId().length() == 0) return false; 
		if(user.getGender() == null) 
			user.setGender("female"); 
		if(!user.getGender().equals("male") && !user.getGender().equals("female")) return false; 
		 
		// 아이디가 있는 경우 
		if(userDao.getUser(user.getId()) != null) 
			return false; 
		 
		// 비밀번호 암호화 
		String encodePw = passwordEncoder.encode(user.getPw()); 
		user.setPw(encodePw); 
		 
		// 회원가입 진행 
		userDao.insertUser(user); 
		 
		return true; 
	}

	@Override
	public UserVo getUser(String id) {
		return userDao.getUser(id);
	}

	@Override
	public UserVo isSignin(UserVo user) {
		UserVo dbUser = userDao.getUser(user.getId());
		if(dbUser != null && passwordEncoder.matches(user.getPw(),dbUser.getPw())) {
			
			return  dbUser;
		}
		return null;
	}
	

}
