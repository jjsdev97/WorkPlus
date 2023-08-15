<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>회원관리 시스템 회원 수정 페이지</title>
<meta charset="UTF-8">
<link href="css/cafejoin.css" type="text/css" rel="stylesheet"> <!-- 실행(index.jsp) 기준 -->
<style>
h3 {
	text-align: center;
	color: #1a92b9;
}
input[type=file] {
	display: none;
}
</style>
</head>
<body>
	<jsp:include page="../cafeAdmin/cafeadminheader.jsp"/>
   <form name="joinform" action="updateProcess.cnet" method="post"
   		 enctype="multipart/form-data" >
   <h3>회원 정보 수정</h3>
   <hr>
   <b>아이디</b>
   <input type="text" name="id" value="${memberinfo.id}" readonly>
   
   <b>비밀번호</b>
   <input type="password" name="pass" value="${memberinfo.password}" readonly>
   
   <b>이름</b>
   <input type="text" name="name" value="${memberinfo.name}" placeholder="Enter name" required>
   	<div class="clearfix">
   		<button type="submit" class="submitbtn">수정</button>
   		<button type="reset" class="cancelbtn">취소</button>
   	</div>
   </form>
   
<script>
	$(".cancelbtn").click(function() {
		history.back();
	});
</script> 
</body>
</html>