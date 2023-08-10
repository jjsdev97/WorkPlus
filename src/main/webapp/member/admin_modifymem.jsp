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
  <h3>사용자 수정</h3><hr>
	  	
	  	<div class="joinname">	<%-- 이름 입력 --%>
		<b>이름</b><input type="text" name="id" value="${m.m_NAME}" ><br>
		<b>이메일</b><input type="text" name="id" value="${m.VERIFY_EMAIL}" ><br>		
		<b>부서명</b><input type="text" name="id" value="" ><br>		
		<b>직책</b><input type="text" name="id" value="" ><br>		
		<b>사원번호</b><input type="text" name="id" value="${m.e_NUM}"><br>		
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