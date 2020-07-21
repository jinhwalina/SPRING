<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    


<title>Home</title>
<c:if test="${user == null }">
	<h1>Login</h1>
	<div class="container">
	  <form action="<%= request.getContextPath()%>/" method="post">
	    <div class="form-group">
	      <label for="usr">ID</label>
	      <input type="text" class="form-control" id="usr" name="id" placeholder="아이디">
	    </div>
	    <div class="form-group">
	      <label for="pwd">PASSWORD</label>
	      <input type="password" class="form-control" id="pwd" name="pw" placeholder="비밀번호">
	      <br>
	      <button>로그인</button>
	    </div>
	  </form>
	</div>
	<input type="hidden" value="${isLogin}" id="isLogin">
	<input type="hidden" value="${id}" id="id">
</c:if>
	<script type="text/javascript">
		$(function(){
			var id = $('#id').val();
			var isLogin = $('#isLogin').val()
			if(isLogin == 'false' && id != '')
				alert(id + '가 없거나 비밀번호가 잘못 되었습니다.')
		})
	</script>



