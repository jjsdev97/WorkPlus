<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<link href="css/find.css" rel="stylesheet">
<style>

</style>
</head>
<body>
<script>
$(function(){
	
	$('.cancelbtn').click(function(){
		 location.href='login.et';
	 });
	
});
</script>
<div class="main">
  <div class="container">
  <form name="find_passform" class="find_passform">
  <h3>비밀번호 찾기</h3>
  	<div class="element">
  	<div class="input_email">
  	비밀번호를 찾고자 하는 이메일을 입력해주세요.
		<input type="text" name="email" value="" id="input_email" placeholder="" required>
		<button type="button" class="verify_email">인증번호 받기</button>
	</div>
	
	<div class="input_num">
		<input type="text" name="verifynum" value="" id="verifynum" placeholder="인증번호 6자리 숫자 입력">
		<button type="button" class="verify">확인</button>
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