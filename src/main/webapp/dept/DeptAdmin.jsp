<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/header.jsp" />

<script src="js/deptView.js"></script>
</head>
<style>
</style>
<body>
	<div class="main">
		<div class="modal-container">
			<b>팀명</b> <input class="dept-input" type='text' size=10 name='dname' id='dname' required>
			<br>
			<b>팀레벨</b> <input class="dept-input" type='text' name='dlevel' id='dlevel' required>
			<br>
			<b>상위팀</b> <input class="dept-input" type='text' name='dupperlevel' id='dupperlevel' required>
			<br>
			<b>팀 색상</b> <input class="dept-input" type='color' name='dcolor' id='dcolor' required>
			<br>
			<hr>
			<button type="submit" class="submitbtn">추가</button>
			<button type="button" class="cancelbtn">취소</button>
		</div>
		
		
	
		<div class="dept-container">
			<ul class="deptList">

			</ul>
		</div>


		<button class="modal-btn">팀 추가</button>
		
		<hr>
		
		조직도 미리보기


	
	</div>
</body>
</html>