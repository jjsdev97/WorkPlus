<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<!-- <jsp:include page="../header.jsp"/> -->
<meta charset="UTF-8">
<style>
	h1{font-size: 1.5rem; text-align: center; color: #1a92b9}
	
	.container {
		width: 60%
	}
	
	label {
		font-weight: bold;
	}
	
	#upfile {
		display: none;
	}
	
	img {
		width: 20px;
	}
</style>
<script src="js/writeform.js"></script>
<title>item-write</title>
</head>
<body>
 <div class="container">
	<form action="itemadd.cafe" method="post" enctype="multipart/form-data" name="boardform">
	<h1>item-write</h1>
	<div class="form-group">
		<label for="item_name">상품 이름</label>
		<input name="item_name" id="item_name" type="text" maxlength="100"
			   class="form-control" placeholder="Enter item_name">
	</div>
	<div class="form-group">
		<label for="item_price">상품 가격</label>
		<input name="item_price" id="item_price" type="text" maxlength="100"
			   class="form-control" placeholder="Enter item_price">
	</div>
	<div class="form-group">
		<button type=submit class="btn btn-primary">등록</button>
		<button type=reset class="btn btn-danger">취소</button>
	</div>
 	</form>
 </div>
</body>
</html>
