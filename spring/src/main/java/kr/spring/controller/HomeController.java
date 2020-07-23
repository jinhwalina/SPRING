package kr.spring.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView homePost(ModelAndView mv, UserVo user) {
		logger.info("URI:/");
		UserVo dbUser = userService.isSignin(user);
		if(dbUser != null) {
			mv.setViewName("redirect:/board/list"); // 로그인 성공 시 게시판으로 넘어가고,
			mv.addObject("user",dbUser);
		} else
			mv.setViewName("redirect:/"); // 실패할 시 그냥 현재 페이지에 !
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
	@RequestMapping(value = "/user/signout", method = RequestMethod.GET)
	public ModelAndView signoutGet(ModelAndView mv, HttpServletRequest request) {
		logger.info("URI:/signout:GET");
		mv.setViewName("redirect:/");
		request.getSession().removeAttribute("user");
		return mv;
	}
	
	@RequestMapping(value ="/idCheck")
	@ResponseBody
	public Map<Object, Object> idcheck(@RequestBody String id){
	    Map<Object, Object> map = new HashMap<Object, Object>();
	    UserVo user = userService.getUser(id);
	    boolean check = user == null ? true : false;
	    map.put("check", check);
	    return map;
	}
	
	@RequestMapping(value ="/test2")
	@ResponseBody
	public Map<Object, Object> test2(@RequestBody TestVo test){
	    Map<Object, Object> map = new HashMap<Object, Object>();
	    System.out.println(test);
	    map.put("res", "success!!");
	    return map;
	}

	
}
class TestVo{ // 잠시 사용하기위해 이렇게 밑에 만들어도 된다.
	private String id;
	private int num;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	@Override
	public String toString() {
		return "TestVo [id=" + id + ", num=" + num + "]";
	}
	
}