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
            <label for="" id="id-error" class="error"></label>
            <div class="id-msg"></div>
        </div>
        <div class="container-pw">
            <div class="text-pw">비밀번호</div>
            <div class="box-pw">
                <input type="password" name="pw" id="pw">
                <a href="#"></a>
            </div>
            <label for="pw" id="pw-error" class="error"></label>
        </div>
        <div class="container-pw">
            <div class="text-pw">비밀번호 확인</div>
            <div class="box-pw">
                <input type="password" name="pw2" id="pw2">
                <a href="#"></a>
            </div>
            <label for="pw2" id="pw2-error" class="error"></label>
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
            <label for="gender" id="gender-error" class="error"></label>
        </div>
        <div class="container-email">
            <div class="text-bold">본인확인 이메일</div>
            <div class="box-email">
                <input type="text" name="email" id="email">
            </div>
            <label for="email" id="email-error" class="error"></label>
        </div>
        <button class="btn-submit">Sign up</button>
    </div>
</form>
</body>
 
<script>
	$(function(){
		$('input[name=id]').keyup(function(){ // 내용이 바뀌면, 이 정보를 가져와서
			var id = $(this).val();
			if(id.length >= 3){
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
						$('#id-error').html(str);
			        }
			    });
			}else{
				if(id.length == 0)
					$('#id-error').html('필수 항목입니다.');
				else
					$('#id-error').html('아이디는 세글자 이상이어야 합니다.');
			}
		})

		// 유효성 검사

		$("form").validate({
	        rules: {
	            
	            pw: {
	                required : true,
	                minlength : 8,
	                maxlength : 20,
	                regex: /^(?=\w{8,20}$)\w*(\d[A-z]|[A-z]\d)\w*$/
	            },
	            pw2: {
	                required : true,
	                minlength : 8,
	                equalTo : pw
	            },
	            email: {
	                required : true,
	                email : true
	            },
	            gender: {
	            	required : true,
	                minlength : 4
	            }
	        },
	        //규칙체크 실패시 출력될 메시지
	        messages : {
	           
	            pw: {
	                required : "필수로입력하세요",
	                minlength : "최소 {0}글자이상이어야 합니다",
	                maxlength : "최대 {0}글자이하이어야 합니다",
	                regex : "영문자, 숫자로 이루어져있으며 최소 하나이상 포함"
	            },
	            pw2: {
	                required : "필수로입력하세요",
	                equalTo : "비밀번호가 일치하지 않습니다."
	            },
	            gender: {
	            	required : "남자/여자 중 하나를 반드시 선택해야합니다.",
	            	minlength : "남자/여자 중 하나를 반드시 선택해야합니다.",
	            },
	            email: {
	                required : "필수로입력하세요",
	                email : "메일규칙에 어긋납니다"
	            },
	        }
	    });

		$.validator.addMethod(
		    "regex",
		    function(value, element, regexp) {
		        var re = new RegExp(regexp);
		        return this.optional(element) || re.test(value);
		    },
		    "Please check your input."
		);
	})
</script>