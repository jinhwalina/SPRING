<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
</head>
<body>
<form method="post" action="<%=request.getContextPath()%>/user/signup">
    <div class="container-body">
        <div class="container-id">
        <br>
            <div class="text-id">아이디</div>
            <div class="box-id">
                <input type="text" name="id" id="id">
                <label for="id">@naver.com</label>
            </div>
            <div class="dup-fail-msg display-none">이미 사용중이거나 탈퇴한 아이디입니다</div>
            <div class="dup-suc-msg display-none">멋진 아이디네요 !</div>
        </div>
        <div class="container-pw">
            <div class="text-pw">비밀번호</div>
            <div class="box-pw">
                <input type="password" name="pw" id="pw">
                <a href="#"></a>
            </div>
        </div>
        <div class="container-pw">
            <div class="text-pw">비밀번호 확인</div>
            <div class="box-pw">
                <input type="password" name="pw2" id="pw2">
                <a href="#"></a>
            </div>
        </div>
        <div class="container-gender">
            <div class="text-gender">성별</div>
            <div class="box-gender">
                <select name="gender" id="gender">
                    <option value="">성별</option>
                    <option value="male">남자</option>
                    <option value="female">여자</option>
                </select>
            </div>
        </div>
        <div class="container-email">
            <div class="text-bold">본인확인 이메일</div>
            <div class="box-email">
                <input type="text" name="email" id="email">
            </div>
        </div>
        <button class="btn-submit">Sign up</button>
    </div>
</form>
</body>

<script>
	$(function(){
		$('#id').change(function(){
			var id = $(this).val();
		    $.ajax({ // 기본적인 형태의 정보를 보내는 방법 
			    // 서버에 데이터를 전송하고, 서버에서는 받은 데이터를 처리하고 검색을 하거나 해서 다시 클라이언트에게 보냄
		        async:true, // 동기 비동기 형식으로 어떻게 보낼지 
		        type:'POST', // 데이터 접근 방법 
		        data:id, // 내가 보낼 데이터 중괄호를 통해서 여러개를 보낼 수도 있음
		        // 아이디 중복 체크는 동기 형식으로 처리
		        // 댓글 등록은 비동기 형식 
		        url:"<%=request.getContextPath()%>/idCheck",
		        dataType:"json",
		        contentType:"application/json; charset=UTF-8",
		        success : function(data){ // 성공하면 서버에서 보내준 값을 콘솔로그를 통해 찍어 보여달라.
					if(data['check']){
						$('.dup-suc-msg').removeClass('display-none')
						$('.dup-fail-msg').addClass('display-none')
					}else{
						$('.dup-suc-msg').addClass('display-none')
						$('.dup-fail-msg').removeClass('display-none')
					}
		            console.log(data);
		        }
			});
		})
	})
</script>