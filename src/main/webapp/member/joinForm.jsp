<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link href="/WorkPlus/css/join.css" rel="stylesheet">
<jsp:include page="/header.jsp" />
<style>

</style>
</head>
<body>
<div class="main">
  <h3>회원정보 입력</h3><hr>
  <div class="joincontainer">
  <form name="joinform" class="joinform" action="/joinProcess.net">
  	<div class="joinname">
	<b>이름</b><br>
	<input type="text" name="name" value="" id="input_name" placeholder="이름을 입력하세요" required><br>
	</div>
	
	<div class="joinid">
	<b>아이디</b><br>
	<input type="text" name="id" id="input_id" value="" placeholder="아이디를 입력하세요" required>
	<input type="button" value="중복확인" id="idcheck">
	</div>
	
	<div class="joinempnum">
	<b>사원번호</b><br>
	<input type="text" name="empnum" value="" id="input_empnum" placeholder="사원번호를 입력하세요" required><br>
	</div>
	
	<div class="verify_email">
	<b>이메일</b><br>
	<input type="text" name="email" value="" id="email" required> @
	<input type="text" name="domain" value="" id="domain">
	<select name="email_sel" id="email_sel">
		<option value="">직접입력</option>
		<option value="naver.com">naver.com</option>
		<option value="daum.net">daum.net</option>
		<option value="gmail.com">gmail.com</option>
		<option value="nate.com">nate.com</option>
	</select>
	<br>
	</div>
	
	<div class="input_pass">
	<b>비밀번호</b><br>
	<input type="password" name="pass" value="" placeholder="비밀번호를 입력하세요" required><br>	
	
	<b>비밀번호 확인</b><br>
	<input type="password" name="passcheck" value=""  placeholder="비밀번호 확인" required><br>
	</div>
	
  <div class="clearfix">
  	<button type="submit" class="submitbtn">등록</button>
  	<button type="button" class="cancelbtn">취소</button>
  </div>
  </form>
  </div>
</div> <%-- main end --%>
</body>
</html>