package kr.green.springtest2.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.green.springtest2.service.UserService;
import kr.green.springtest2.vo.UserVo;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	UserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	@RequestMapping(value="/")
    public ModelAndView main(ModelAndView mv) throws Exception{
        mv.setViewName("/main/home");
        mv.addObject("setHeader", "타일즈테스트");
        System.out.println(userService.getPw("abc456"));
        return mv;
    }
	@RequestMapping(value="/user/signup", method=RequestMethod.GET)
    public ModelAndView signupGet(ModelAndView mv){
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
	@RequestMapping(value="/idCheck")
	@ResponseBody
	public Map<Object, Object> idcheck(@RequestBody String id){
	    Map<Object, Object> map = new HashMap<Object, Object>();
	    UserVo user = userService.getUser(id);
	    boolean check = user == null ? true : false;
	    map.put("check", check);
	    return map;
	}
}
