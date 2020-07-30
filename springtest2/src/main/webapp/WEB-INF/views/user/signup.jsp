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
            
            <label id="id-error" class="error" for="id"></label>
            
            <div class="dup-fail-msg display-none">이미 사용중이거나 탈퇴한 아이디 입니다</div>
            <div class="dup-suc-msg display-none">사용 가능합니다 :)</div>
        </div> 
        <div class="container-pw"> 
            <div class="text-pw">비밀번호</div> 
            <div class="box-pw"> 
                <input type="password" name="pw" id="pw"> 
                <a href="#"></a> 
            </div> 
            
            <label id="pw-error" class="error" for="pw"></label>
            
        </div> 
        <div class="container-pw"> 
            <div class="text-pw">비밀번호 확인</div> 
            <div class="box-pw"> 
                <input type="password" name="pw2" id="pw2"> 
                <a href="#"></a> 
            </div>
            
            <label id="pw2-error" class="error" for="pw2"></label>
            
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
            
            <label id="gender-error" class="error" for="gender"></label>
            
        </div> 
        <div class="container-email"> 
            <div class="text-bold">본인확인 이메일</div> 
            <div class="box-email"> 
                <input type="text" name="email" id="email"> 
            </div> 
            
            <label id="email-error" class="error" for="email"></label>
            
        </div> 
        <button class="btn-submit">Sign up</button> 
    </div> 
</form> 
</body> 

<script>
	$(function(){
		//id가 dup인 중복 확인 버튼
		$("#id").keyup(function(){
			var id = $(this).val();
			if(id.length >= 4)
			$.ajax({
				async:true,
				type:'POST',
				data:id,
				url:"<%=request.getContextPath()%>/idCheck",
				dataType:"json",
				contentType:"application/json; charset=UTF-8",
				success : function(data){
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
		});
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