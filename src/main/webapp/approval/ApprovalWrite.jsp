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
			<div class="modal-subject">결재선 설정</div>

			<div class="modal-content">

				<div class="modal-member-list-container">
					<div class="modal-member-list-subject">사원 목록</div>
					<div class="modal-member-list">
						<c:forEach var='dept' items="${deptList}">
							<div class="deptname" data-value="${dept.d_name}">${dept.d_name}
								<c:forEach var='member' items="${memberList}">
									<c:if test="${member.d_NUM eq dept.d_num }">
										<div class="modal-member-item">
											&nbsp;&nbsp;
											<div class="pname" data-value="${member.p_NUM}">${member.p_NUM}</div>
											&nbsp;
											<div class="mname" data-value="${member.m_NAME}">${member.m_NAME}</div>


											<div class="modal-approval-type-btn-container">
												<button class="modal-approval-type-btn" id="type-1">결재</button>
												<button class="modal-approval-type-btn" id="type-2">참조</button>
												<button class="modal-approval-type-btn" id="type-3">수신</button>
											</div>

										</div>
									</c:if>
								</c:forEach>
							</div>
						</c:forEach>
					</div>
				</div>
				<div class="modal-approval-line-container">
					<div class="modal-approval-line-subject">결재선 설정</div>

					<div class="modal-approval-line">
						<div class="modal-approval-line-type">
							<div class="modal-approval-line-type-subject">결재</div>
							<div class="modal-approval-line-item filled">
								<div class="approval-line-item-detail">
									<div class="item-deptname">${writer.m_ID}</div>
									<div class="item-pname">${writer.p_NUM }</div>
									<div class="item-mname">${writer.m_NAME}</div>
								</div>
							</div>
							<div class="modal-approval-line-item"></div>
							<div class="modal-approval-line-item"></div>
							<div class="modal-approval-line-item"></div>
							<div class="modal-approval-line-item"></div>
							<div class="modal-approval-line-item"></div>
							<div class="modal-approval-line-item"></div>
						</div>

						<div class="modal-approval-reference">참조 :</div>
						<div class="modal-approval-reciever">수신 :</div>
					</div>


				</div>
			</div>

			<div class='modal-btn-container'>
				<button type="button" class="modal-submitbtn">확인</button>
				<button type="button" class="modal-cancelbtn">취소</button>
			</div>
		</div>



		<form action="approvalAddAction.apv" method="post" id="approvalForm">

			<div class="approval-header">
				<div class="approval-header-select">
					양식 선택 <select name='approval-template' class='approval-select' id='select-template'>
						<c:forEach var='at' items='${atList}'>
							<option value='${at.a_template_num}'>${at.a_template_name}</option>
						</c:forEach>
					</select>
				</div>
				<div class="approval-header-select">
					보존 기한 <select name='approval-period' class='approval-select'>
						<option value='2'>2년</option>
						<option value='3'>3년</option>
						<option value='5'>5년</option>
						<option value='10'>10년</option>
					</select>
				</div>

			</div>

			<div class="approval-container">
				<div class="approval-line-container">

					<table class="approval-line">
						<tr id="approval-line-first">
							<th rowspan='3' id="approval-line-type">결재</th>
							<td id="approval-line-item">${writer.m_ID }</td>
							<!-- 부서 번호 형식때매 임시 -->
							<td id="approval-line-item"></td>
							<td id="approval-line-item"></td>
							<td id="approval-line-item"></td>
							<td id="approval-line-item"></td>
							<td id="approval-line-item"></td>
							<td id="approval-line-item"></td>
						</tr>
						<tr id="approval-line-second">

							<td id="approval-line-item"><input type='text' name='input-approval-item' id='input-approval-item' value='${writer.m_NAME}' readOnly></td>
							<td id="approval-line-item"></td>
							<td id="approval-line-item"></td>
							<td id="approval-line-item"></td>
							<td id="approval-line-item"></td>
							<td id="approval-line-item"></td>
							<td id="approval-line-item"></td>
						</tr>
						<tr id="approval-line-third">
							<td id="approval-line-item">${writer.p_NUM }</td>
							<td id="approval-line-item"></td>
							<td id="approval-line-item"></td>
							<td id="approval-line-item"></td>
							<td id="approval-line-item"></td>
							<td id="approval-line-item"></td>
							<td id="approval-line-item"></td>
						</tr>
					</table>



					<div class="approval-reference-container">참조 :</div>
					<div class="approval-reciever-container">수신 :</div>

					<div class="modal-start-btn-container">
						<button type="button" class="modal-btn">결재선 설정</button>
					</div>


				</div>

			</div>
			<!-- 결재선 div -->


			<div class="approval-content-subject">
				<div>제목 :</div>
				<input type='text' name='approval-subject' id='approval-subject'>
			</div>

			<div class="approval-content-container">

				<div class="approval-content"></div>
				<!-- 템플릿 양식에 따른 input값들 -->
			</div>

			<input type="hidden" id="hiddenField" name="hiddenContent" value="">

			<div class="approval-btn-container">
				<button class='approval-submitbtn' type="submit">작성완료</button>
			</div>


		</form>
	</div>

	<script>
		
	</script>
</body>
</html>
