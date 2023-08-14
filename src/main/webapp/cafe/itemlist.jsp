<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="UTF-8">
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        
        .container {
            max-width: 900px;
            margin: 20px auto;
        }
        
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        
        table, th, td {
            border: 1px solid #dddddd;
        }
        
        th, td {
            padding: 8px 15px;
            text-align: left;
        }
        
        th {
            background-color: #f2f2f2;
        }

        tbody tr:nth-child(even) {
            background-color: #f5f5f5;
        }

        .rows {
            text-align: right;
        }

        .gray {
            color: gray;
        }

        select {
            padding: 5px;
            margin-bottom: 2em;
        }

        ul.pagination {
            list-style-type: none;
            padding: 0;
            overflow: hidden;
            text-align: center;
        }

        ul.pagination li {
            display: inline-block;
            margin-right: 5px;
        }

        ul.pagination a {
            display: block;
            padding: 5px 10px;
            text-decoration: none;
            border: 1px solid #ddd;
        }

        ul.pagination li .active {
            background-color: #007BFF;
            color: white;
        }

        button.registraion {
            padding: 10px 15px;
            background-color: #353535;
            color: white;
            border: none;
            cursor: pointer;
        }

        button.registraion:hover {
            background-color: #5a5a5a;
        }
    </style>
    <script src="js/list.js"></script>
    <title>item list</title>
</head>
<body>
    <div class="container">
        <%-- 상품이 존재할 경우 --%>
        <c:if test="${listcount > 0}">
            <div class="rows">
                <span>줄보기</span> 
                <select id="viewcount">
                    <option value="1">1</option>
                    <option value="3">3</option>
                    <option value="5">5</option>
                    <option value="7">7</option>
                    <option value="10" selected>10</option>
                </select>
            </div>
            <table>
                <thead>
                    <tr>
                        <th colspan="3">item list</th>
                        <th colspan="2"><span>상품 수: ${listcount}</span></th>
                    </tr>
                    <tr>
                        <th>번호</th>
                        <th>고유번호</th>
                        <th>이름</th>
                        <th>가격</th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="num" value="${listcount-(page-1)*limit}" />
                    <c:forEach var="i" items="${itemlist}">
                        <tr>
                            <td><c:out value="${num}" /></td>
                            <td>${i.ITEM_UID}</td>
                            <td>${i.ITEM_NAME}</td>
                            <td>${i.ITEM_PRICE}</td>
                            <c:set var="num" value="${num-1}" />
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div class="center-block">
                <ul class="pagination">
                    <c:if test="${page <= 1}">
                        <li><a class="gray">이전</a></li>
                    </c:if>
                    <c:if test="${page > 1}">
                        <li><a href="admin.cafe?page=${page - 1}">이전</a></li>
                    </c:if>
                    <c:forEach var="a" begin="${startpage}" end="${endpage}">
                        <c:choose>
                            <c:when test="${a == page}">
                                <li><a class="active"><c:out value="${a}" /></a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="admin.cafe?page=${a}" class="gray"><c:out value="${a}" /></a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:if test="${page >= maxpage}">
                        <li><a class="gray">다음</a></li>
                    </c:if>
                    <c:if test="${page < maxpage}">
                        <li><a href="admin.cafe?page=${page + 1}">다음</a></li>
                    </c:if>
                </ul>
            </div>
        </c:if>
        <%-- 게시글이 없는 경우 --%>
        <c:if test="${listcount == 0}">
            <h3 style="text-align: center">등록된 글이 없습니다.</h3>
        </c:if>
        <button type="button" class="registraion">상품 등록</button>
    </div>
</body>
</html>
