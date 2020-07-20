<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-md bg-dark navbar-dark">
	<div class="container">
	  	<a class="navbar-brand" href="#">TEST</a>
	  	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
	    	<span class="navbar-toggler-icon"></span>
	  	</button>
	  	<div class="collapse navbar-collapse" id="collapsibleNavbar">
	    	<ul class="navbar-nav">
		      	<li class="nav-item">
		        	<a class="nav-link" href="<%= request.getContextPath()%>/">Main</a>
		      	</li>
		      	<li class="nav-item">
		        	<a class="nav-link" href="<%= request.getContextPath()%>/board/list">Board</a>
		      	</li>
		      	<c:if test="${user == null}">
		      	<li class="nav-item">
		        	<a class="nav-link" href="<%= request.getContextPath()%>/user/signup">Sign up</a>
		      	</li>    
		      	</c:if>
		      	<c:if test="${user != null}">
		      	<li class="nav-item">
		        	<a class="nav-link" href="<%= request.getContextPath()%>/user/signout">Logout</a> <!-- 로그인 했을때 네비에 회원가입 버튼이 로그아웃 버튼으로 바뀌게 하는 코드 -->
		      	</li>    
		      	</c:if>
	    	</ul>
		</div> 
	</div> 
</nav>