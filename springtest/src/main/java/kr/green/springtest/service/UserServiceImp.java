package kr.green.springtest.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.green.springtest.dao.UserDao;
import kr.green.springtest.vo.UserVo;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Override
	public UserVo getUser(String id) {
		return userDao.getUser(id); // id를 줄테니, id를 요청
	}

	@Override
	public UserVo isUser(UserVo inputUser) {
		/* 일반적으로 로그인 과정은 DB에서 아이디와 일치하는 정보를 가져와서 입력받은 아이디와 가져온 정보 중 비밀번호를 비교하여 로그인을 결정한다.
		 * => 쿼리로 비밀번호를 비교하지 않는다.
		 * => 입력한 비밀번호는 실제 비밀번호이고 DB에 저장된 비밀번호는 암호화된 비밀번호이기 때문에 쿼리로 직접 비교할 수 없다.
		 * => 다른 이유로는 pw에 이상한 작업을 하면 로그인이 될 수 있기 때문에 
		 * 		( 블라인드 SQL 인젝션) 
		 * */
		UserVo user = userDao.getUser(inputUser.getId());
		if(user == null)
			return null;
		if(passwordEncoder.matches(inputUser.getPw(), user.getPw())) {
			return user;
		}
		return null;
	}

	@Override
	public boolean signup(UserVo user) {
		if(user == null)
			return false;
		// 중복된 아이디면, 리턴 false로 
		if(userDao.getUser(user.getId()) != null || user.getId().length() == 0)
			return false;
		// 비밀번호 체크
		if(user.getPw() == null || user.getPw().length() == 0)
			return false;
		if(user.getEmail() == null
			|| user.getEmail().length() == 0
			|| !user.getEmail().contains("@"))
			return false;
		// 성별 체크
		if(user.getGender() == null || user.getGender().length() == 0)
			user.setGender("male");
		user.setAuth("USER");
		user.setIsDel("N");
		// 비밀번호 암호화
		String encodePw = passwordEncoder.encode(user.getPw());
		user.setPw(encodePw);
		userDao.insertUser(user);
		return true;
	}

	@Override
	public UserVo getUser(HttpServletRequest r) {
		
		return (UserVo)r.getSession().getAttribute("user");
	}
}
