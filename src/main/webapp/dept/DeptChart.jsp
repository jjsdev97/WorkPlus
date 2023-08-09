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


	</div>
</body>
</html>