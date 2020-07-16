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
	        <td><a href="<%= request.getContextPath() %>/board/detail?num=${board.num}">${board.title}</a></td>
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
  		<li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/board/list?page=${pm.startPage-1}"><i class="fas fa-angle-double-left"></i></a></li>
  	</c:if>
  	
  	<c:if test="${pm.criteria.page != 1}">
  		<li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/board/list?page=${pm.criteria.page-1}"> <i class="fas fa-angle-left"></i> </a></li>
  	</c:if>
  	<c:forEach var="index" begin="${pm.startPage}" end="${pm.endPage }">
	    <li class="page-item <c:if test="${index == pm.criteria.page }">active</c:if>"><a class="page-link" href="<%=request.getContextPath()%>/board/list?page=${index}">${index}</a></li>
	</c:forEach>
	<c:if test="${pm.criteria.page != pm.lastEndPage}">
	    <li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/board/list?page=${pm.criteria.page+1}"><i class="fas fa-angle-right"></i></a></li>
	</c:if>
	<c:if test="${pm.next}">
	    <li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/board/list?page=${pm.endPage+1}"><i class="fas fa-angle-double-right"></i></a></li>
	</c:if>
  </ul> 
  
  