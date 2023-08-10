<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/header.jsp" />
</head>
<body>
	<div class="main">
		<button class="modal-btn">팀 추가</button>

		<div class="modal-container">
			<form method='post' action='deptAdd.dt' name='addform' id='addform'>
				<b>팀명</b> <input type='text' size=10 name='dname' id='dname' required>
				<br>
				<b>팀레벨</b> <input type='text' name='dlevel' id='dlevel' required>
				<br>
				<b>상위팀</b> <input type='text' name='dupperteam' id='dupperteam' required>
				<br>
				<b>팀 색상</b> <input type='color' name='dcolor' id='dcolor' required>
				<br>
				<hr>
				<button type="submit" class="btn btn-primary">확인</button>
				<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
			</form>
		</div>
	</div>
</body>
</html>