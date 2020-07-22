<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<c:if test="${user == null }">
<div style="height: 800px">
	<h1>Hello :)</h1>
	<h3>Login</h3>
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
</div>
</c:if>