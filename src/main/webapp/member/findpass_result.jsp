<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기 결과</title>
<link href="../css/find.css" rel="stylesheet">
<style>

</style>
</head>
<body>
<div class="main">
  <div class="container">
  <form name="find_passform" class="find_passform" action="passreset.et" method="post">
  <h3>비밀번호 찾기 결과</h3>
  	<div class="element_pass">
  	<div class="newpass">
  		<h4>비밀번호를 변경해주세요.</h4><br>
  		새 비밀번호<br>
		<input type="text" name="pass" value="" id="new_pass" placeholder="새 비밀번호를 입력해주세요" required><br><br>
		새 비밀번호 확인<br>
		<input type="text" name="pass" value="" id="new_passcheck" placeholder="새 비밀번호를 입력해주세요" required>
	</div>
   </div>
	
 	<div class="clearfix_p">
	  	<button type="submit" class="submitbtn">확인</button>
	  	<button type="button" class="cancelbtn">취소</button>
   </div>
  </form>
  </div>
</div> <%-- main end --%>
</body>
</html>