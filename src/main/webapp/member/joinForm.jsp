<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewpoint" content="width=device-width, initial-scale=1">
<link href="/WorkPlus/css/join.css" rel="stylesheet">
<title>회원가입</title>
<jsp:include page="../header.jsp" />
</head>
<body>
<script src="js/join.js"></script>
<div class="main">
  <div class="joincontainer">
  <form name="joinform" class="joinform" action="joinProcess.et" method="post">
  <h3>회원정보 입력</h3><hr>
	  	
	  	<div class="joinname">	<%-- 이름 입력 --%>
			<b>이름</b><br>
			<input type="text" name="name" id="name" placeholder="이름을 입력하세요" required><br>
		</div>
		
		<div class="joinid">	<%-- 아이디 입력--%>
			<b>아이디</b><br>
			<input type="text" name="id" id="id"  maxLength="12" placeholder="아이디를 입력하세요" required>
			<input type="button" value="중복확인" id="idcheck">
		</div>
		
		<div class="joinempnum"> <%-- 사원번호 입력 --%>
			<b>사원번호</b><br>
			<input type="text" name="empnum" id="empnum" placeholder="사원번호 6자리 숫자를 입력하세요" required><br>
			<span id="empnum_message"></span>
		</div>
	
		<div class="verify_email"> <%-- 이메일 입력 --%>
			<b>이메일</b><br>
			<input type="text" name="email" id="email" required> @
			<input type="text" name="domain" id="domain">
			<select name="email_sel" id="email_sel">
					<option value="">직접입력</option>
					<option value="naver.com">naver.com</option>
					<option value="daum.net">daum.net</option>
					<option value="gmail.com">gmail.com</option>
					<option value="nate.com">nate.com</option>
			</select>
				<button type="button" class="verify_emailbtn" id="verify_emailbtn">인증번호 받기</button>	
			<br>
			<input type="text" name="verifynum" class="verifynum" id="verifynum" placeholder="인증번호 6자리 숫자 입력">
				<button type="button" class="verify" id="verify">확인</button>
				<input type="hidden" class="authRadnum" id="authRadnum" name="authRadnum" />
		</div>
	
		<div class="input_pass"> <%-- 비밀번호 입력 --%>
			<b>비밀번호</b><br>
			<input type="password" name="pass" id="pass"  placeholder="비밀번호를 입력하세요" required><br>	
			
			<b>비밀번호 확인</b><br>
			<input type="password" name="passcheck" id="passcheck" placeholder="비밀번호 확인" required><br>
			<span id="pass_message"></span>
		</div>
		
	  <div class="clearfix">
	  	<button type="submit" class="submitbtn">등록</button>
	  	<button type="reset" class="cancelbtn">취소</button>
	  </div>
  </form>
  </div>
</div> <%-- main end --%>
</body>
</html>