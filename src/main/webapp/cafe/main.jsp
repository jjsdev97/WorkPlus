<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>카페 메인 페이지</title>
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="../header.jsp" />

    <div class="main-content">
        <div class="cafe-image-container">
            <img src="img/cafe_main.png" alt="카페 이미지">
            <a href="coffee.cafe" class="cafe-link"></a>
        </div>
        <p>
        <a href="admin.cafe" class="btn_menu" id="btn_admin">카페 관리자</a>
        </p>
    </div>

    <div class="footer">
        <!-- 사내 카페 푸터 내용 -->
    </div>
</body>
</html>
