package kr.green.springtest2.interceptor; 
 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
import javax.servlet.http.HttpSession; 
 
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter; 
 
public class AuthInterceptor extends HandlerInterceptorAdapter { 
	@Override 
	public boolean preHandle(HttpServletRequest request,  
			HttpServletResponse response,  
			Object handler) 
			throws Exception { 
		HttpSession session = request.getSession(); 
		Object user = session.getAttribute("user"); 
		
		if(user == null) { 
			response.sendRedirect(request.getContextPath()+"/"); // 이런식으로 표현하는 이유는 범용적으로 사용하기 위해 이렇게 써준다.
			return false; 
		} 
		return true; 
	} 
} 

/* 권한 부여 설정 시 , 보드가 안만들어진 상태에서 작동하는지 확인을 해보려면 if(user == null) 이 부분을 != 으로 수정 후 반대로 생각해서 테스트 해보면 된다. 
 이때 servlet-context에서 코드는 이런식으로 수정이 되는데 웹에서 확인할때 
 url에 직접 user/signup을 눌러서 검색해본다.
 위 조건식으로 바꿔준 경우에는 널 값이 아니면 메인으로 돌아간다고 표현 되기 때문에 로그인 한 상태에서 사인업을 들어가게되면 다시 메인으로 돌아가게된다.
<interceptor>
	<mapping path="/user/signup"/>
	<beans:ref bean="authInterceptor"/>
</interceptor>

*/
