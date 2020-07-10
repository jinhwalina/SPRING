<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
  