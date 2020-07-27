package kr.spring.controller;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
	@Autowired
	private JavaMailSender mailSender;

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
	
	@RequestMapping(value = "/mail/mailSending")
	public String mailSending(HttpServletRequest request) {

	    String setfrom = "stajun@naver.com";         
	    String tomail  = request.getParameter("tomail");     // 받는 사람 이메일
	    String title   = request.getParameter("title");      // 제목
	    String id = request.getParameter("content");    // 내용
	    
	    // 비밀번호를 찾을 때 ,
	    
	    // 랜덤으로 비밀번호를 생성
	    int len = 13;
	    String newPw = "";
	    for(int i = 0; i<13; i++) {
	    	// 0-9는 숫자 0-9
	    	// 10-35는 소문자 a-z
	    	// 36-51는 대문자 A-Z
	    	int r = (int)(Math.random()*62);
	    	char ch;
	    	if(r<=9) {
	    		ch = (char)('0'+r);
	    	}else if(r<=35) {
	    		ch = (char)('a'+(r-10));
	    	}else {
	    		ch = (char)('A'+(r-36));
	    	}
	    	newPw += ch;
	    }
	    // 생성된 비밀번호를 외원 정보에 저장 (DB에 저장)
	    userService.newPw(id, newPw);
	    // 메일로 변경된 비밀번호를 전송
	    
	    try {
	        MimeMessage message = mailSender.createMimeMessage();
	        MimeMessageHelper messageHelper 
	            = new MimeMessageHelper(message, true, "UTF-8");
	        String format1 = "<h1>새로운 비밀번호 입니다.<h1>" + "<div>" + "<h2>";
	        String format2 = "</h2>";

	        messageHelper.setFrom(setfrom);  // 보내는사람 생략하거나 하면 정상작동을 안함
	        messageHelper.setTo(tomail);     // 받는사람 이메일
	        messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
	        messageHelper.setText(format1 + newPw + format2, true);  // 메일 내용

	        mailSender.send(message);
	    } catch(Exception e){
	        System.out.println(e);
	    }

	    return "redirect:/";
	}
	@RequestMapping(value = "/mail", method = RequestMethod.GET)
	public ModelAndView mailGet(ModelAndView mv) {
		logger.info("URI:/signout:GET");
		mv.setViewName("/main/mail"); 
		return mv;
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