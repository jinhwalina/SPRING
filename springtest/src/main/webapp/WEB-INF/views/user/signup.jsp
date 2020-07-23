<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
    <style>
       
    </style>
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
            <div class="id-msg"></div>
            
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
		$('input[name=id]').change(function(){ // 내용이 바뀌면, 이 정보를 가져와서
			var id = $(this).val();
			$.ajax({
		        async:true,
		        type:'POST',
		        data:id,
		        url:"<%=request.getContextPath()%>/idCheck",
		        dataType:"json",
		        contentType:"application/json; charset=UTF-8",
		        success : function(data){
					var str;
					if(data['res']){
						str = 
							'<p style="color:green;">사용 가능한 아이디입니다.</p>'
					}else{
						str = 
							'<p style="color:red;">이미 가입되어 있거나 탈퇴한 아이디입니다.</p>'
					}
					$('.id-msg').html(str);
		        }
		    });
		})
	})
</script>