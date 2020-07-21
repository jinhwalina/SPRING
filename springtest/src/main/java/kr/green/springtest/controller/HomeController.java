package kr.green.springtest.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
		// mv.addObject("id", inputUser.getId());
		if(user != null) {
			mv.setViewName("redirect:/board/list");
			mv.addObject("user", user);
		}
		return mv;
	}
	@RequestMapping(value = "/user/signin", method = RequestMethod.GET)
	public ModelAndView home(ModelAndView mv) {
		logger.info("URI:/signin");
		mv.setViewName("/main/home"); 
		return mv;
	}
	
	@RequestMapping(value = "/user/signup", method = RequestMethod.GET)
	public ModelAndView signupGet(ModelAndView mv) {
		logger.info("URI:/signup:GET");
		mv.setViewName("/user/signup"); 
		return mv;
	}
	@RequestMapping(value = "/user/signup", method = RequestMethod.POST)
	public ModelAndView signupPost(ModelAndView mv, UserVo user) {
		logger.info("URI:/signup:POST");
		if(userService.signup(user)) {
			mv.setViewName("redirect:/");
		}else
			mv.setViewName("redirect:/user/signup"); 
		return mv;
	}
	@RequestMapping(value = "/user/signout", method = RequestMethod.GET)
	public ModelAndView signoutGet(ModelAndView mv, HttpServletRequest request) {
		logger.info("URI:/signout:GET");
		mv.setViewName("redirect:/"); 
		request.getSession().removeAttribute("user");
		return mv;
	}
	
	
	
	
	
}
