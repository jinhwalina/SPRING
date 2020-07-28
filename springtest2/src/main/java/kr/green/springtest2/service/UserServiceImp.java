package kr.green.springtest2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.green.springtest2.dao.UserDao;

@Service
public class UserServiceImp implements UserService {
	
	@Autowired
    UserDao userDao;
	
	@Override
	public String getPw(String id) {
		return userDao.getPw(id);

	}

}
