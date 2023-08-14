<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="js/jquery-3.7.0.js"></script>
<link href="css/find.css" rel="stylesheet">
<style>
</style>
</head>
<body>
<script>
$(function(){
	
	 let checkemailaddress = false;
		
	 $('.cancelbtn').click(function(){
		 location.href='login.et';
	 });
	
	 
	 $('.verify_email').on('click', function(){
		
		 const email = $("input[name=email]").val();
		 const pattern = /^\w+@\w+[.]\w{3}$/;
		 if(!pattern.test(email)){
			 alert("형식에 맞게 입력해주세요.");
			 email.focus();
			 checkemailaddress = false;
		 } //이메일 주소 유효성 검사
		 
		 alert('인증번호가 발송되었습니다.');
		 
		 $.ajax({
			 url : "findpassprocess.et",
			 data : {"email" : email},
			 success : function(resp){
				$("#authRadnum").val(resp); 
			 }
		 }); //ajax end
	 }); //인증번호 받기 end
	 
	 function ChkAuthmailnum(){
		 var inputAuthnum = $("input[name=verifynum]").val();
		 
		 if(inputAuthnum === ''){
			 $("#verifymessage").html('인증번호를 입력하세요'.css('color', 'red'));
			 return;
		 }
		 
		 if(inputAuthnum == $("authRadnum").val()){
			 alert("인증에 성공하였습니다.");
		 } else {
			 alert("인증에 실패하였습니다.");
		 }
		 
	 }
	 
});
</script>
<div class="main">
  <div class="container">
  <form name="find_passform" class="find_passform" action="findpassprocess.et" method="post">
  <h3>비밀번호 찾기</h3>
  	<div class="element">
  	<div class="input_email">
  	비밀번호를 찾고자 하는 이메일을 입력해주세요.
		<input type="text" name="email" value="" id="input_email" placeholder="" required>
		<button type="button" class="verify_email">인증번호 받기</button><br>
		<span id="email_message"></span>
	</div>
	
	<div class="input_num">
		<input type="text" name="verifynum" value="" id="verifynum" placeholder="인증번호 6자리 숫자 입력">
		<button type="button" class="verify">확인</button>
		<div id="verifymessage"></div>
		<input type="hidden" id="authRadnum" name="authRadnum" />
	</div>
   </div>
	
 	<div class="clearfix">
	  	<button type="submit" class="submitbtn">다음</button>
	  	<button type="button" class="cancelbtn">취소</button>
   </div>
  </form>
  </div>
</div> <%-- main end --%>
</body>
</html>