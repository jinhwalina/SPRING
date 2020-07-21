package kr.green.springtest.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.green.springtest.vo.UserVo;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	@Override
	public void postHandle(
	    HttpServletRequest request, 
	    HttpServletResponse response, 
	    Object handler, 
	    ModelAndView modelAndView)
	    throws Exception {
	    ModelMap modelMap = modelAndView.getModelMap(); // 컨트롤러가 거친 후에 유저 정보가 있는지 확인하는 코드
	    UserVo user = (UserVo)modelMap.get("user");

	    if(user != null) { // 널값이 아니면
	        HttpSession session = request.getSession(); // 세션에 유저 정보 저장
	        session.setAttribute("user", user);
	    }
	}

}
