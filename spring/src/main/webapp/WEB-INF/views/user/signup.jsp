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
            
            <label id="id-error" class="error" for="id"></label>
            
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
        
        <label id="pw-error" class="error" for="pw"></label>
        
        <div class="container-pw">
            <div class="text-pw">비밀번호 확인</div>
            <div class="box-pw">
                <input type="password" name="pw2" id="pw2">
                <a href="#"></a>
            </div>
        </div>
        
        <label id="pw2-error" class="error" for="pw2"></label>
        
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
        
        <label id="gender-error" class="error" for="gender"></label>
        
        <div class="container-email">
            <div class="text-bold">본인확인 이메일</div>
            <div class="box-email">
                <input type="text" name="email" id="email">
            </div>
        </div>
        
        <label id="email-error" class="error" for="email"></label>
        
        <button class="btn-submit">Sign up</button>
    </div>
</form>
</body>

<script>
	$(function(){
		$('#id').keyup(function(){
			var id = $(this).val();
			if(id.length >= 4)
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

			else {
				$('.dup-suc-msg').addClass('display-none')
				$('.dup-fail-msg').addClass('display-none')
			}
		})

		$("form").validate({ // form태그의 유효성을 검사하겠다. submit 될 때 동작함.
	        rules: { // 규칙
	            id: { // id는 , 
	                required : true, // 필수항목
	                minlength : 4 // 최대 4글자
	            },
	            pw: {
	                required : true,
	                minlength : 8,
	                maxlength : 20,
	                regex: /^\w*(\d[A-z]|[A-z]\d)\w*$/ // 정규 표현식. 숫자, 영문이 하나 이상 포함되게끔
		                	// (?=\w{8,20}$)글자가 이런 조건을 만족 한다. 라는 뜻 \w 영문,숫자
	            },
	            pw2: {
	                required : true,
	                equalTo : pw // 누구랑 같은지 확인하는거.
	            },
	            //age: {
	                // digits : true  digits > 숫자만! 입력하게끔. 
	            //},
	            email: {
	                required : true,
	                email : true
	            },
	            gender : {
	                required : true
	            }
	        },
	        //규칙체크 실패시 출력될 메시지
	        messages : {
	            id: {
	                required : "필수로입력하세요",
	                minlength : "최소 {0}글자이상이어야 합니다"
	            },
	            pw: {
	                required : "필수로입력하세요",
	                minlength : "최소 {0}글자이상이어야 합니다",
	                maxlength : "최대 {0}글자이상이어야 합니다",
	                regex : "영문자, 숫자로 이루어져있으며 최소 하나이상 포함"
	            },
	            pw2: {
	                required : "필수로입력하세요",
	                equalTo : "비밀번호가 일치하지 않습니다."
	            },
	            email: {
	                required : "필수로입력하세요",
	                email : "메일규칙에 어긋납니다"
	            },
	            gender: {
	            	required : "필수로입력하세요",
	            }
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