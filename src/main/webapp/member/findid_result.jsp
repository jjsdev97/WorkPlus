<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기 결과</title>
<link href="../css/find.css" rel="stylesheet">
<style>

</style>
</head>
<body>
<div class="main">
  <div class="container">
  <form name="find_idresultform" class="find_idresultform">
  <h3>아이디 찾기 결과</h3>
  	<div class="element_id">
  	<span claas="result_id">
  	홍길동 님의 아이디는<br>
  	honghong 입니다.
  	</span>
   </div>
	
 	<div class="clearfix_id">
	  	<button type="submit" class="submitbtn">확인</button>
   </div>
  </form>
  </div>
</div> <%-- main end --%>
</body>
</html>