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
		양식 : ${app.approval_template_str}
		<br>
		보관 기간 : ${app.approval_period}
		<br>
		문서 번호 : ${app.approval_document}
		<br>
		기안자 : ${app.approval_writer}
		<br>
		문서 제목 : ${app.approval_subject}
		<br>
		작성 날짜 : ${app.approval_date}
		<br>
		본문 : ${app.approval_content}
		<br>

		<hr>

		<c:forEach var="line" items="${alList}">
			결재선 : ${line.a_line_target} | ${line.a_line_type } | ${line.a_line_check} <br>
		</c:forEach>

	</div>
</body>
</html>