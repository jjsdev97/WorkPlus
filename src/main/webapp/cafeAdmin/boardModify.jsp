<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<jsp:include page="cafefooter.jsp"/>
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
<script src="js/cafeitem_modifyform.js"></script>
<title>item - modify</title>
</head>
<body>
 <div class="container">
	<form action="ItemModify.ca" method="post" name="modifyform"
		  enctype="multipart/form-data">
	<input type="hidden" name="ITEM_UID" value="${boarddata.ITEM_UID}">
	<h1>item - modify</h1>
	<div class="form-group">
		<label for="board_name">관리자</label>
		<input name="board_name" id="board_name" type="text" class="form-control"
		 	   value="${id}" readOnly>
	</div>
	
	<div class="form-group">
		<label for="ITEM_NAME">상품 이름</label>
		<textarea name="ITEM_NAME" id="ITEM_NAME" maxlength="100"
			      rows="1" class="form-control">${boarddata.ITEM_NAME}
		</textarea>
	</div>
	
	<div class="form-group">
    <label for="ITEM_PRICE">상품 가격</label>
    <input name="ITEM_PRICE" id="ITEM_PRICE" type="text" maxlength="100"
           class="form-control" placeholder="price of item" 
           value="${boarddata.ITEM_PRICE}">
	</div>
	
	<div class="form-group">
		<label for="ITEM_MENU">상품 메뉴</label>
		<input name="ITEM_MENU" id="ITEM_MENU" type="text" maxlength="100"
			   class="form-control" placeholder="coffee/tea/ade/side" 
			   value="${boarddata.ITEM_MENU}">
	</div>
	
	<div class="form-group">
		<label for="ITEM_DETAIL">내용</label>
		<textarea name="ITEM_DETAIL" id="ITEM_DETAIL"
			      rows="10" class="form-control">${boarddata.ITEM_DETAIL}
		</textarea>
	</div>
	
	<%-- 원문글일 경우만 파일첨부 가능 --%>
	<div class="form-group">
		<label>파일첨부
			<img src="image/attach.png" alt="파일첨부" width="20px">
			<input type="file" id="upfile" name="ITEM_IMG_PATH">
		</label>
			<span id="filevalue">${boarddata.ITEM_IMG_PATH}</span>
			<img src="image/remove.png" alt="파일삭제" width="10px" class="remove">
	</div>
	
    <div class="form-group">
        <button type="submit" class="btn btn-primary">수정</button>
    	<button type="button" class="btn btn-danger" onclick="history.go(-1);">취소</button>
    </div>										<!-- onclick="history.back();" -->
 	</form>
 </div>
</body>
</html>
