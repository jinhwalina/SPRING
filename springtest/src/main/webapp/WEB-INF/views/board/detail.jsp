<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="https://kit.fontawesome.com/0e9b7a4ea6.js" crossorigin="anonymous"></script>
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
		      <input type="text" class="form-control" value= "${board.num }" readonly id="num">
		    </div>
		     <div class="form-group">
		      <label>제목</label>
		      <input type="text" class="form-control" value= "${board.title }" readonly>
		    </div>
		     <div class="form-group">
		      <label>작성자</label>
		      <input type="text" class="form-control" value= "${board.writer }" readonly>
		    </div>
		     <div class="form-group">
		      <label>작성일</label>
		      <input type="text" class="form-control" value= "${board.registerDate }" readonly>
		    </div>
		     <div class="form-group">
		      <label>조회수</label>
		      <input type="text" class="form-control" value= "${board.views }" readonly>
		    </div>
		     <div class="form-group">
		      <label>추천수</label>
		      <input type="text" class="form-control btn-like" name="up" value= "${board.up }" readonly>
		      <button type="button" id="up" class="text-like">추천 <i class="far fa-thumbs-up"></i></button>
		    </div>
		    <div class="form-group">
	      		<label>Comment</label>
	      		<textarea name="content" class="form-control" rows="5">${board.content}</textarea>
	    	</div>
	    	
		 </form>
		 <c:if test="${board.file != null }">
		 	<a href="<%=request.getContextPath()%>/board/download?fileName=${board.file}">${board.oriFile }</a>
		 </c:if>
		<a href="<%=request.getContextPath()%>/board/list?page=${cri.page}&search=${cri.search}&type=${cri.type}"><button>목록</button></a>
		<c:if test="${user != null }">
			<a href="<%=request.getContextPath()%>/board/register"><button>등록</button></a>
			<c:if test="${user.id == board.writer}">
				<a href="<%=request.getContextPath()%>/board/modify?num=${board.num}"><button>수정</button></a>
				<a href="<%=request.getContextPath()%>/board/delete?num=${board.num}"><button>삭제</button></a>
			</c:if>
		</c:if>
	</c:if>
</c:if>
 <!-- 정보를 가져와서 안보이게 처리할때 이런식으로 hidden을 많이 쓴다 -->
<script>
	$(function(){
		$('#up').click(function(){ 
			var num = $('#num').val();
			$.ajax({
		        async:true,
		        type:'POST',
		        data:num,
		        url:"<%=request.getContextPath()%>/board/up",
		        dataType:"json",
		        contentType:"application/json; charset=UTF-8",
		        success : function(data){
					// 로그인 한 회원이면
			        if(data['isUser']){
				        // 게시글의 추천수가 0보다 크면=>추천수를 증가시켜야하면
				        // => 처음 추천을 누른다면
						if(data['up'] > 0){
							$('input[name=up]').val(data['up'])
						}
						// 이미 추천을 눌렀다면
						else{
							alert('이미 추천 하셨습니다!')
						}
					// 로그인하지 않았으면
					}else{
						alert('추천은 로그인을 해야 가능합니다.')
					}
		        }
		    });
		})
	})
</script>