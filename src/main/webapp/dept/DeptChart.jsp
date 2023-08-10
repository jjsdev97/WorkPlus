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
<body>
	<div class='main'>

		<ul id="organisation">
		</ul>


		<div id="chart"></div>

		<c:forEach var="dept" items="${deptList}" varStatus="status">
			<c:if test="${dept.memberCnt > 0}">
				<c:set var="dept_num" value="${dept.d_num}" />

				<div class="dept-table-container">
					<h2>${dept.d_name}</h2>

					<table class='dept-table'>
						<thead>
							<tr>
								<th>성명</th>
								<th>직책</th>
								<th>이메일</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="m" items="${memberList}" varStatus="status">
								<c:if test="${m.d_NUM eq dept.d_num }">
									<tr>
										<td>${m.m_NAME}</td>
										<td>${m.p_NUM }</td>
										<td>${m.m_ID }<span>@workplus.net</span></td>
									</tr>
								</c:if>
							</c:forEach>
						</tbody>
					</table>
				</div> <!-- dept테이블 --> 
			</c:if>
		</c:forEach>
	</div>
</body>
</html>