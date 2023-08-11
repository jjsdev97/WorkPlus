<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/header.jsp" />

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript">
	var $jqchart = jQuery.noConflict();
</script>
<script src="js/jquery.orgchart.js"></script>


<script src="js/deptView.js"></script>
<link rel="stylesheet" href="css/jquery.orgchart.css" />
<link href="css/dept.css" rel="stylesheet">


</head>
<style>
</style>
<body>
	<div class="main">
		<div class="modal-container">
			<div class="dept-subject">부서 관리</div>

			<div class="modal-content">
				<div class="dept-input-item">
					<b>팀 명</b> <input class="dept-input" type='text' size=10 name='dname' id='dname' required>
				</div>
				<div class="dept-input-item">
					<b>팀레벨</b> <select class="dept-select" name='dlevel' id='dlevel'>
						<option value='1'>1</option>
						<option value='2'>2</option>
						<option value='3'>3</option>
					</select>
				</div>
				<div class="dept-input-item">
					<b>상위팀</b> <select class="dept-select" name='dupperlevel' id='dupperlevel'>
						<option value='0'>대표이사</option>
					</select>
				</div>
				<div class="dept-input-item">
					<b>팀 색상</b> <input class="dept-input" type='color' name='dcolor' id='dcolor' required>
				</div>
			</div>

			<div class='modal-btn-container'>
				<button type="submit" class="submitbtn">추가</button>
				<button type="submit" class="updatebtn">수정</button>
				<button type="button" class="cancelbtn">취소</button>
			</div>
		</div>



		<div class="dept-container">
			<div class="dept-subject">부서 관리</div>
			<div class="deptList-container">
				<ul class="deptList">
				</ul>
			</div>




		</div>

		<div class="dept-btn-container">
			<button class="modal-btn">팀 추가</button>
		</div>


		<hr>


		<ul id="organisation">

		</ul>


		<div id="chart"></div>



	</div>
</body>
</html>