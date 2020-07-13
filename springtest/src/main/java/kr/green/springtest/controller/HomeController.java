package kr.green.springtest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.green.springtest.service.UserService;
import kr.green.springtest.vo.UserVo;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST}) // get 방식과 post 방식을 한곳에서 처리하겠다는 의미. 따로 만들어도 됨.
	public ModelAndView home(ModelAndView mv, UserVo inputUser) {
		logger.info("URI:/");
		mv.setViewName("/main/home");
		UserVo user = userService.isUser(inputUser); // id와 pw가 일치하면 회원 정보를 주는 코드. 이 전에 했던 코드는 id만 일치하면
		// userDao.getUser(inputUser.getId());
		
		// 서버가 클라이언트한테 정보를 전달하는코드. 아이디를 전송하고, 로그인을 했는 지 안했는 지
		mv.addObject("id", inputUser.getId());
		if(user == null) {
			mv.addObject("isLogin", false);
		}
		return mv;
	}
	
	
	
	
	
}
