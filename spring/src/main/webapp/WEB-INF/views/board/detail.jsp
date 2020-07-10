<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<form>
	<div class="form-group">
      <label>게시글번호</label>
      <input type="text" class="form-control"name="num" value="${board.num}" readonly> 
      <!-- input태그는 수정이 가능하기때문에 readonly 추가 후 읽기 전용으로 바꿔준다 -->
    </div>
    <div class="form-group">
      <label>게시글제목</label>
      <input type="text" class="form-control"name="title" value="${board.title}" readonly> 
    </div>
    <div class="form-group">
      <label>작성자</label>
      <input type="text" class="form-control"name="writer" value="${board.writer}" readonly> 
    </div>
    <div class="form-group">
      <label>작성일</label>
      <input type="text" class="form-control"name="registerDate" value="${board.registerDate}" readonly> 
    </div>
    <div class="form-group">
      <label>조회수</label>
      <input type="text" class="form-control"name="views" value="${board.views}" readonly> 
    </div>
    <label>내용</label>
    <textarea class="form-control" rows="5" id="comment" name="content" readonly>${board.content}</textarea>
</form>
<a href="<%= request.getContextPath() %>/board/register"><button>글쓰기</button></a>
<a href="<%= request.getContextPath() %>/board/modify?num=${board.num}"><button>수정</button></a>
<a href="<%= request.getContextPath() %>/board/delete?num=${board.num}"><button>삭제</button></a>

<!-- 
<h2>${board.title}</h2>
<p>게시글 번호 : ${board.num}</p>
<p>조회수 : ${board.views}</p>
<p>작성자 : ${board.writer}</p>

	<form action="/action_page.php">
    	<div class="form-group">
      	<label for="comment">CONTENT:</label>
      	<textarea class="form-control" rows="5" id="comment" name="text">${board.content}</textarea>
      	<p>작성일 : ${board.registerDate}</p>
    	</div>
  	</form>
 -->
	