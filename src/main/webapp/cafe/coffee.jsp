<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>커피 주문하기</title>
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="../../header.jsp" />

    <div class="main-content">
        <h2>커피 주문하기</h2>
        <table>
            <tr>
                <th>메뉴</th>
                <th>가격</th>
            </tr>
            <c:forEach items="${coffeeItemList}" var="item">
                <tr>
                    <td>${item.itemName}</td>
                    <td>${item.itemPrice}</td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div class="footer">
        <!-- 사내 카페 푸터 내용 -->
    </div>

    <a href="Admin.cafe" class="btn_menu" id="btn_admin">카페 관리자</a>
</body>
</html>
