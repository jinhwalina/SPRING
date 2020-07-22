<%@page import="org.springframework.web.servlet.support.RequestContext"%>
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
    <div class="form-group">
      <label>추천수</label>
      <input type="text" class="form-control"name="like" value="${board.like}" readonly>
      <button type="button" class="btn btn-outline-success col-12" id="like">추천</button> 
    </div>
    <label>내용</label>
    <textarea class="form-control" rows="5" id="comment" name="content" readonly>${board.content}</textarea>
    <c:if test="${board.file != null }">
	    <div>
	    	<a href="<%=request.getContextPath()%>/board/download?fileName=${board.file}">${board.oriFile}</a>
	    </div>
    </c:if>
</form>
<a href="<%= request.getContextPath() %>/board/list?&page=${cri.page}&type=${cri.type}&search=${cri.search}"><button>목록</button></a>

<c:if test="${user != null }"><a href="<%= request.getContextPath() %>/board/register"><button>글쓰기</button></a></c:if>

<c:if test="${user != null && user.id == board.writer}"><a href="<%= request.getContextPath() %>/board/modify?num=${board.num}"><button>수정</button></a></c:if>

<c:if test="${user != null && user.id == board.writer}"><a href="<%= request.getContextPath() %>/board/delete?num=${board.num}"><button>삭제</button></a></c:if>

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
 
 <script>
	$(function(){
		$('#like').click(function(){
			var num = $('input[name=num]').val();
		    $.ajax({ // 기본적인 형태의 정보를 보내는 방법 
			    // 서버에 데이터를 전송하고, 서버에서는 받은 데이터를 처리하고 검색을 하거나 해서 다시 클라이언트에게 보냄
		        async:true, // 동기 비동기 형식으로 어떻게 보낼지 
		        type:'POST', // 데이터 접근 방법 
		        data:num, // 내가 보낼 데이터 중괄호를 통해서 여러개를 보낼 수도 있음
		        // 아이디 중복 체크는 동기 형식으로 처리
		        // 댓글 등록은 비동기 형식 
		        url:"<%=request.getContextPath()%>/board/like",
		        dataType:"json",
		        contentType:"application/json; charset=UTF-8",
		        success : function(data){ // 성공하면 서버에서 보내준 값을 콘솔로그를 통해 찍어 보여달라.
			        if(!data['isUser']){
						alert("로그인한 회원만 추천할 수 있습니다.")
				    }else{
						if(data['like']<0){
							alert('추천은 한 번만 가능합니다.')
						}else{
							$('input[name=like]').val(data['like'])
						}
					}
		        }
			})
		})
	})
</script>
	