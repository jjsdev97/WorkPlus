<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<jsp:include page="/header.jsp" />
<link href="css/approval.css" rel="stylesheet">
<script src="js/approvalWrite.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
</head>
<body>
	<div class="main">
		<div class="modal-container">
			<div class="approval-subject">결재선 설정</div>

			<div class="modal-content">

				<div class="modal-member-list-container">
					<div class="modal-member-list-subject">제목</div>
					<div class="modal-member-list">
						<c:forEach var='dept' items="${deptList}">
							<div>${dept.d_name }</div>
							<c:forEach var='member' items="${memberList}">
								<c:if test="${member.d_NUM eq dept.d_num }">
									<div class="modal-member-item">ㄴ ${member.p_NUM} | ${member.m_NAME }</div>
								</c:if>
							</c:forEach>
						</c:forEach>
					</div>
				</div>
				<div class="modal-approval-line-container">
					<div class="modal-approval-line-subject">제목</div>

					<div class="modal-approval-line">
						<div class="modal-approval-line-type">
							<div class="modal-approval-line-type-subject">결재</div>
							<div class="modal-approval-line-item"></div>
							<div class="modal-approval-line-item"></div>
							<div class="modal-approval-line-item"></div>
							<div class="modal-approval-line-item"></div>
							<div class="modal-approval-line-item"></div>
							<div class="modal-approval-line-item"></div>
							<div class="modal-approval-line-item"></div>
						</div>
					</div>
				</div>
			</div>

			<div class='modal-btn-container'>
				<button type="button" class="submitbtn">확인</button>
				<button type="button" class="cancelbtn">취소</button>
			</div>
		</div>

		<form action="#" method="post">
			<div class="approval-container">
				<div class="approval-header">
					양식 선택 <select name='approval-period'>
						<option value='1'>양식1</option>
						<option value='2'>양식2</option>
						<option value='3'>양식3</option>
					</select> 보존 기한 <select name='approval-period'>
						<option value='2'>2년</option>
						<option value='3'>3년</option>
						<option value='5'>5년</option>
						<option value='10'>10년</option>
					</select>
				</div>
				<div class="approval-line-container">
					<table class="approval-line">
						<tr>
							<th rowspan='3' id="approval-line-type">결재</th>
							<td id="approval-line-item"></td>
							<td id="approval-line-item"></td>
							<td id="approval-line-item"></td>
							<td id="approval-line-item"></td>
							<td id="approval-line-item"></td>
							<td id="approval-line-item"></td>
							<td id="approval-line-item"></td>
						</tr>
						<tr>
							<td id="approval-line-item"></td>
							<td id="approval-line-item"></td>
							<td id="approval-line-item"></td>
							<td id="approval-line-item"></td>
							<td id="approval-line-item"></td>
							<td id="approval-line-item"></td>
							<td id="approval-line-item"></td>
						</tr>
						<tr>
							<td id="approval-line-item"></td>
							<td id="approval-line-item"></td>
							<td id="approval-line-item"></td>
							<td id="approval-line-item"></td>
							<td id="approval-line-item"></td>
							<td id="approval-line-item"></td>
							<td id="approval-line-item"></td>
						</tr>
					</table>

					<div class="approval-reference-container"></div>
					<div class="approval-reciever-container"></div>


				</div>
				<!-- 결재선 div -->

				<div class="modal-start-btn-container">
					<button type="button" class="modal-btn">결재선 설정</button>
				</div>



			</div>
		</form>
	</div>
</body>
</html>
