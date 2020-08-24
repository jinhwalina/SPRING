package kr.green.project.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.green.project.dao.UserDao;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	UserDao userDao;
	
	@Override
	public String getUser(String mail) {
		return userDao.getNickname(mail);
	}
	
}
