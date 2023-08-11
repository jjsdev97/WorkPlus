<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<jsp:include page="/header.jsp" />
<link href="css/approval.css" rel="stylesheet">

</head>
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
		
		<form action="#" method="post">
			<div class="approval-container">
				<div class="approval-header">
					양식 선택 <input type='text' name='approval-form'> 보존 기한 <input type='text' name='approval-period'>
				</div>
				<div class="approval-line-container">
					<table class="approval-line">
						<tr>
							<td rowspan='3' id="approval-line-type">결재</td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td id="approval-line-type">참조</td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td id="approval-line-type">수신</td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
					</table>


				</div>
				<!-- 결재선 div -->
				<button type="button" class="modal-btn">결재선 설정</button>
				<div class="approval-content-container">
					제목 : <input type="text" name="approval-subject">
					<div class="approval-content"></div>
				</div>

				<div class="approval-btn-container">
					<button type="submit">작성하기</button>
					<button type="reset">다시 작성하기</button>
				</div>

			</div>
		</form>
	</div>
</body>
</html>