<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<jsp:include page="/header.jsp" />
<style>

</style>
</head>
<body>
	<div class="main">
			<button class="modal-btn">모달버튼</button><!-- 모달창 버튼 -->			
			
			<div class="modal-container">	<!-- 내부 채우기 -->	
			<h3>사용자 수정</h3><hr>
			<div>
			<b>이름</b><input type="text" name="name" value="${m.m_Name}">
			</div>
			<div><b>이메일</b><input type="text" name="name" value="${m.m_Name}"></div>
			<div><b>부서명</b><input type="text" name="name" value="${m.m_Name}"></div>
			<div><b>직책</b><input type="text" name="name" value="${m.m_Name}"></div>
			<div><b>사원번호</b><input type="text" name="name" value="${m.m_Name}"></div>	
		
			</div>
	</div>
</body>
</html>