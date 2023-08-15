<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<jsp:include page="cafeadminheader.jsp"/>
<meta charset="UTF-8">
<style>
	
	body {
		background-color: #D9D9D9;
	}
	
	h1{font-size: 1.5rem; text-align: center; color: #353535}
	
	.container {
		width: 60%
	}
	
	.btn-primary {
		background-color: #3C6E71;
	}
	
	.btn-danger {
		background-color: #284B63;
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
<script src="js/cafeitem_writeform.js"></script>
<title>상품 등록</title>
</head>
<body>
 <div class="container">
	<form action="ItemAdd.ca" method="post" enctype="multipart/form-data" name="boardform">
	<h1>상품 등록</h1>
	<div class="form-group">
		<label for="board_name">관리자</label>
		<input name="board_name" id="board_name" value="${id}" readOnly
			   type="text" class="form-control" placeholder="Enter board_name">
	</div>
	<div class="form-group">
		<label for="ITEM_NAME">상품 이름</label>
		<input name="ITEM_NAME" id="ITEM_NAME" type="text" maxlength="100"
			   class="form-control" placeholder="name of item">
	</div>
	<div class="form-group">
		<label for="ITEM_PRICE">상품 가격</label>
		<input name="ITEM_PRICE" id="ITEM_PRICE" type="text" maxlength="100"
			   class="form-control" placeholder="price of item">
	</div>
	<div class="form-group">
		<label for="ITEM_MENU">상품 메뉴</label>
		<input name="ITEM_MENU" id="ITEM_MENU" type="text" maxlength="100"
			   class="form-control" placeholder="coffee/tea/ade/side">
	</div>
	<div class="form-group">
		<label for="ITEM_DETAIL">상품 내용</label>
		<textarea name="ITEM_DETAIL" id="ITEM_DETAIL"
			      rows="10" class="form-control">
		</textarea>
	</div>
	<div class="form-group">
		<label>파일첨부
			<img src="image/attach.png" alt="파일첨부">
			<input type="file" id="upfile" name="ITEM_IMG_PATH">
		</label>
			<span id="filevalue"></span>
	</div>
	<div class="form-group">
		<button type=submit class="btn btn-primary">상품 등록</button>
		<button type=reset class="btn btn-danger">취소</button>
	</div>
 	</form>
 </div>
</body>
</html>
