<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>카페 메인 페이지</title>
    <link href="css/cafestyle.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="../header.jsp" />
    <div class="main-content">
        <div class="cafe-image-container">
            <img src="img/cafe_main.png" alt="카페 이미지">
            <a href="coffee.cafe" class="cafe-link"></a>
        </div>
        <a href="coffee.cafe" class="btn_cafe_order" id="btn_order">주문하기</a>
<!--         
        <p>
        <a href="login.cnet" class="btn_cafe_menu" id="btn_admin">카페 관리자</a>
        </p>
-->         
    </div>
<!--  
    <div class="footer">
    </div>
-->    
</body>
</html>
