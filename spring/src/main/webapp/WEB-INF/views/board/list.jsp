<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script src="https://kit.fontawesome.com/0e9b7a4ea6.js" crossorigin="anonymous"></script>

<h2>BOARD</h2>
  <table class="table table-hover">
    <thead>
      <tr>
        <th>번호</th>
        <th>제목</th>
        <th>작성자</th>
        <th>작성일</th>
        <th>조회수</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="board" items="${list}"> <!-- list에서 꺼내서 board라는 변수에 넣어줌-->
	      <tr>
	        <td>${board.num}</td>
	        <td><a href="<%= request.getContextPath() %>/board/detail?num=${board.num}&page=${pm.criteria.page}&type=${pm.criteria.type}&search=${pm.criteria.search}">${board.title}</a></td>
	        <td>${board.writer}</td>
	        <td>${board.registerDate}</td>
	        <td>${board.views}</td>
	      </tr>
      	</c:forEach>
    </tbody>
  </table>
  
  <ul class="pagination justify-content-center">
  <!--
  	<c:if test="${pm.prev}">  이전 버튼과 다음 버튼은 항상 보여지는게 아니라 조건에 만족하면 봉여기지때문에 조건문으로 써준다 
  		<li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/board/list?page=${pm.startPage-1}">이전</a></li>
  	</c:if>
  	<c:forEach var="index" begin="${pm.startPage}" end="${pm.endPage }">
	    <li class="page-item <c:if test="${index == pm.criteria.page }">active</c:if>"><a class="page-link" href="<%=request.getContextPath()%>/board/list?page=${index}">${index}</a></li>
	</c:forEach>
	<c:if test="${pm.next}">
	    <li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/board/list?page=${pm.endPage+1}">다음</a></li>
	</c:if>
  </ul> 
  -->
  	<c:if test="${pm.prev}">
  		<li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/board/list?page=${pm.startPage-1}&type=${pm.criteria.type}&search=${pm.criteria.search}"><i class="fas fa-angle-double-left"></i></a></li>
  	</c:if>
  	
  	<c:if test="${pm.criteria.page != 1}">
  		<li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/board/list?page=${pm.criteria.page-1}&type=${pm.criteria.type}&search=${pm.criteria.search}"> <i class="fas fa-angle-left"></i> </a></li>
  	</c:if>
  	<c:forEach var="index" begin="${pm.startPage}" end="${pm.endPage }">
	    <li class="page-item <c:if test="${index == pm.criteria.page }">active</c:if>"><a class="page-link" href="<%=request.getContextPath()%>/board/list?page=${index}&type=${pm.criteria.type}&search=${pm.criteria.search}">${index}</a></li>
	</c:forEach>
	<c:if test="${pm.criteria.page != pm.lastEndPage}">
	    <li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/board/list?page=${pm.criteria.page+1}&type=${pm.criteria.type}&search=${pm.criteria.search}"><i class="fas fa-angle-right"></i></a></li>
	</c:if>
	<c:if test="${pm.next}">
	    <li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/board/list?page=${pm.endPage+1}&type=${pm.criteria.type}&search=${pm.criteria.search}"><i class="fas fa-angle-double-right"></i></a></li>
	</c:if>
  </ul> 
  
<form action="<%=request.getContextPath()%>/board/list"> 
<!-- 검색같은 경우는 GET 방식을 주로 많이 쓴다! 정보를 전달해줄때는 form 태그로 감싸줌. a태그는 이미 지정된 링크를 눌러서 이동하기때문에 헷갈리지말자!  -->
	<div class="input-group mb-3">
		<select class="form-control" id="sel1" name="type" > <!-- 검색 타입 -->
			<option value="0" <c:if test="${pm.criteria.type == 0}">selected</c:if>>전체</option>
			<option value="1" <c:if test="${pm.criteria.type == 1}">selected</c:if>>작성자</option>
			<option value="2" <c:if test="${pm.criteria.type == 2}">selected</c:if>>제목</option>
			<option value="3" <c:if test="${pm.criteria.type == 3}">selected</c:if>>내용</option>
		</select>
		<input type="text" class="form-control" placeholder="검색할 내용을 입력해주세요!" name="search" value="${pm.criteria.search }">
		<div class="input-group-append">
			<button class="btn btn-success" type="submit">검색</button>  
		</div>
	</div>
</form>  
  