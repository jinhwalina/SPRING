package kr.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.controller.service.UserService;
import kr.spring.vo.UserVo;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	private UserService userService;
	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(ModelAndView mv) {
		logger.info("URI:/");
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
	public ModelAndView signupGPost(ModelAndView mv, UserVo user) {
		logger.info("URI:/signup:GET");
		mv.setViewName("/user/signup"); 
		if(userService.signUp(user)) {
			mv.setViewName("redirect:/");
		}else {
			mv.setViewName("redirect:/user/signup");
			mv.addObject("user",user);
		}
		return mv;
	}
	

}
