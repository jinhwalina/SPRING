<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<form action="<%=request.getContextPath() %>/board/register" method="post" enctype="multipart/form-data">
<!-- 정볼를 보낼 주소와, 정보를 어떤타입으로 보낼지 결정해주는 코드  -->
	<div class="form-group">
	     <label>제목</label>
	     <input type="text" class="form-control" name="title" value= ${board.title }>
	</div>
	<div class="form-group">
		<label>작성자</label> <!-- 로그인 구현을 했다면 필요 없지만, 지금은 아직 안한 상태기때문에 적어준다!  -->
		<input type="text" class="form-control" name="writer" value= ${board.writer }>
	</div>
	<div class="form-group">
		<label>Content</label>
		<textarea name="content" class="form-control" rows="5" id="comment">${board.content}</textarea>
	</div>
	<div class="form-group">
		<input type="file" name="file2" class="form-control">
	</div>
	<button type="submit">등록</button> <!-- submit은 써도 되고 안써도 된다 왜? form태그 안에서는 기본 태그가 submit이기 때문에! -->
</form>