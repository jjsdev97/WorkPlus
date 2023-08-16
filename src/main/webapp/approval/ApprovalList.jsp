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
									<a href="approvalDetail.apv?num=${app.approval_num}" id="subject_link"> <c:if test="${app.approval_subject.length()>=10 }">
											<c:out value="${app.approval_subject.substring(0,10)}..." />
										</c:if> <c:if test="${app.approval_subject.length()<10 }">
											<c:out value="${app.approval_subject}" />
										</c:if></a>
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

		<div class="pagination-container">
			<ul class="pagination">
				<c:if test="${page <= 1 }">
					<li class="page-item"><a class="page-link gray">이전&nbsp;</a></li>
				</c:if>
				<c:if test="${page > 1 }">
					<li class="page-item "><a href="approvalList.apv?page=${page-1}" class="page-link">이전&nbsp;</a></li>
				</c:if>

				<c:forEach var="a" begin="${startpage}" end="${endpage}">
					<c:if test="${a == page }">
						<li class="page-item active"><a class="page-link">${a }</a></li>
					</c:if>
					<c:if test="${a != page }">
						<li class="page-item"><a href="approvalList.apv?page=${a }" class="page-link"> ${a} </a></li>
					</c:if>
				</c:forEach>

				<c:if test="${page >= maxpage }">
					<li class="page-item"><a class="page-link gray">&nbsp;다음</a></li>
				</c:if>
				<c:if test="${page < maxpage }">
					<li class="page-item"><a href="approvalList.apv?page=${page+1 }" class="page-link">&nbsp;다음</a></li>
				</c:if>
			</ul>

		</div>

	</div>
</body>
</html>