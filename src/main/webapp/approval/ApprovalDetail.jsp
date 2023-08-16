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
		${app.approval_template_str}
		<br>
		${app.approval_period}
		<br>
		${app.approval_document}
		<br>
		${app.approval_writer}
		<br>
		${app.approval_subject}
		<br>
		${app.approval_date}
		<br>
		${app.approval_content}
		<br>

		<hr>


		<c:forEach var="line" items="${alList}">
			${line.a_line_target} - ${line.a_line_type } - ${line.a_line_check} <br>
		</c:forEach>

	</div>
</body>
</html>