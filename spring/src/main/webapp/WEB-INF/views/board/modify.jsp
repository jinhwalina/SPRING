<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="https://kit.fontawesome.com/0e9b7a4ea6.js" crossorigin="anonymous"></script>

<form method="post" action="<%= request.getContextPath()%>/board/modify" enctype="multipart/form-data">
	<div class="form-group">
      <label>게시글번호</label>
      <input type="text" class="form-control"name="num" value="${board.num}" readonly> 
      <!-- input태그는 수정이 가능하기때문에 readonly 추가 후 읽기 전용으로 바꿔준다 -->
    </div>
    <div class="form-group">
      <label>게시글제목</label>
      <input type="text" class="form-control"name="title" value="${board.title}"> 
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
    	<label>내용</label>
    	<textarea class="form-control" rows="5" id="comment" name="content">${board.content}</textarea>
    </div>
   
	    <div class="box-file">
	    	<c:if test="${board.file != null }">
		    	<span>${board.oriFile}</span>
		    	<button type="button" id="btn-file-close"><i class="fas fa-times"></i></button>
	    	</c:if>
	    	<input type="hidden" name="file" value="${board.file }">
	    </div>
	    <div>
		    <input type="file" name="file2">
		</div>
    <button>수정하기</button>
</form>
<a href="<%= request.getContextPath() %>/board/register"><button>글쓰기</button></a>
<script>
	$(function(){
		$('#btn-file-close').click(function(){
			$('.box-file').css('display','none');
			$('input[name=file]').val('');
		})
		$('input[name=file2]').change(function(){
			if($('input[name=file]').val() != ''){
				$(this).val('')
				alert('첨부파일을 추가하려면 기존 첨부파일을 삭제해야합니다.')
			}
		})
	})
</script>
