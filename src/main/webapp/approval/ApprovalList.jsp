<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<jsp:include page="/header.jsp" />
<link href="css/approvalList.css" rel="stylesheet">

</head>
<body>
	<div class="main">

		<div class="approval-table-container">
			<table class="approval-table">
				<thead>
					<tr>
						<th><div>문서번호</div></th>
						<th><div>제목</div></th>
						<th><div>기안자</div></th>
						<th><div>기안일</div></th>
						<th><div>구분</div></th>
					</tr>
				</thead>
				<tbody>

					<c:forEach var="app" items="${applist}">
						<tr>
							<td>${app.approval_document}</td>
							<td>
								<div>
									<a href="approvalDetail.apv?num=${app.approval_num}"> ${app.approval_subject}</a>
								</div>
							</td>
							<td><div>${app.approval_writer}</div></td>
							<td><div>${app.approval_date}</div></td>
							<td><div>임시</div></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

	</div>
</body>
</html>