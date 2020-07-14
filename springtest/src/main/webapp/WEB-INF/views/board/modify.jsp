<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<c:if test="${board eq null }">
	<h1>해당 게시물은 없는 게시물입니다.</h1>
</c:if>
<c:if test="${board ne null }">
	<c:if test="${board.isDel == 'Y'.charAt(0)}">
		<h1>해당 게시물은 삭제 되었습니다.</h1>
	</c:if>
	<c:if test="${board.isDel == 'N'.charAt(0)}">
		<form method="post" action="<%=request.getContextPath()%>/board/modify">
			<div class="form-group">
				<label>글번호</label>
			    <input type="text" name="num" class="form-control" value= "${board.num }" readonly>
			</div>
			<div class="form-group">
				<label>제목</label>
				<input type="text" name="title" class="form-control" value= "${board.title }">
			</div>
			<div class="form-group">
				<label>작성자</label>
				<input type="text" name="writer" class="form-control" value= "${board.writer }" readonly>
			</div>
			<div class="form-group">
				<label>작성일</label>
				<input type="text" name="registerDate" class="form-control" value= "${board.registerDate }" readonly>
			</div>
			<div class="form-group">
				<label>조회수</label>
				<input type="text" name="views" class="form-control" value= "${board.views }" readonly>
			</div>
			<div class="form-group">
			    <label>Content</label>
			    <textarea  class="form-control" rows="5" name="content">${board.content}</textarea>
			</div>
		<a href="<%=request.getContextPath()%>/board/list"><button type="button">목록</button></a> <!-- form 태그 안에 잇어서 타입이 자동으로 submit이 안되게 타입 지정을 해준다 -->
		<a href="<%=request.getContextPath()%>/board/register"><button>등록</button></a>
		<button>수정하기</button>
		</form>
	</c:if>
</c:if>