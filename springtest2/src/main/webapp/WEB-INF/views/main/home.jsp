<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<div style="height: 800px">
	
	<h1>Hello :)</h1>
	<h3>Login</h3>
	<div class="container">
		<form class="form-inline" action="<%=request.getContextPath() %>/" method="post">
			<label for="usr" class="mb-2 mr-sm-2">ID:</label>
			<input type="text" class="form-control mb-2 mr-sm-2" id="usr" placeholder="아이디" name="id">
			
			<label for="pwd2" class="mb-2 mr-sm-2">PW:</label>
			<input type="password" class="form-control mb-2 mr-sm-2" id="pwd" placeholder="비밀번호" name="pw">  
			<button type="submit" class="btn btn-primary mb-2">login</button>
		</form>
	</div>
</div>