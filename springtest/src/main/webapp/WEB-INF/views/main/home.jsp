<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<html>
<head>
	<title>Home</title>
</head>
<body>
		<h1>로그인</h1>
	<form action="<%= request.getContextPath()%>/" method="post">
		<input type="text" name="id" placeholder="아이디">
		<input type="password" name="pw" placeholder="비밀번호">
		<button>로그인</button>
	</form>
	<input type="hidden" value=${isLogin} id="isLogin">
	<input type="hidden" value=${id} id="id">
	<script type="text/javascript">
		$(function(){
			var id = $('#id').val();
			var isLogin = $('#isLogin').val()
			if(isLogin == 'false' && id != '')
				alert(id + '가 없거나 비밀번호가 잘못 되었습니다.')
		})
	</script>
</body>
</html>


