<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기 결과</title>
<link href="css/find.css" rel="stylesheet">
<style>
</style>
<script src="js/jquery-3.7.0.js"></script>
</head>
<body>
<script>
$(function(){
	
	$(".submitbtn").click(function(){
		 location.href = "login.et";
	});
	
});

</script>
<div class="main">
  <div class="container">
  <form name="find_idresultform"  method="post" class="find_idresultform" >
  <h3>아이디 찾기 결과</h3>
  	<div class="result_id">
  		${member.m_NAME} 님의 아이디는<span id="idresult">${member.m_ID}</span>입니다.
   </div>
	
 	<div class="clearfix_id">
	  	<button type="button" class="submitbtn" id="gologin">확인</button>
   </div>
  </form>
  </div>
</div> <%-- main end --%>
</body>
</html>