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
		<form>
		    <div class="form-group">
		      <label>글번호</label>
		      <input type="text" class="form-control" value= ${board.num } readonly>
		    </div>
		     <div class="form-group">
		      <label>제목</label>
		      <input type="text" class="form-control" value= ${board.title } readonly>
		    </div>
		     <div class="form-group">
		      <label>작성자</label>
		      <input type="text" class="form-control" value= ${board.writer } readonly>
		    </div>
		     <div class="form-group">
		      <label>작성일</label>
		      <input type="text" class="form-control" value= ${board.registerDate } readonly>
		    </div>
		     <div class="form-group">
		      <label>조회수</label>
		      <input type="text" class="form-control" value= ${board.views } readonly>
		    </div>
		    <div class="form-group">
	      		<label>Comment</label>
	      		<textarea name="content" class="form-control" rows="5">${board.content}</textarea>
	    	</div>
		 </form>
		<a href="<%=request.getContextPath()%>/board/list?page=${cri.page}&search=${cri.search}&type=${cri.type}"><button>목록</button></a>
		<a href="<%=request.getContextPath()%>/board/register"><button>등록</button></a>
		<a href="<%=request.getContextPath()%>/board/modify?num=${board.num}"><button>수정</button></a>
		<a href="<%=request.getContextPath()%>/board/delete?num=${board.num}"><button>삭제</button></a>
	</c:if>
</c:if>