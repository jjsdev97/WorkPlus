<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<jsp:include page="cafeadminheader.jsp"/>
<meta charset="UTF-8">
<style>
body {
	background-color: #D9D9D9;
	text-decoration: none;
}
.rows span {
	color: #353535;
}
.table thead tr {
	background-color: #353535;
	color: #FFFFFF;
}

.table tbody tr td a {
 	color: #FFFFFF; /* 원하는 색상으로 변경하세요 */
 	text-decoration: none;
}

.table tbody tr td {
	color: #FFFFFF; /* 이 값은 흰색입니다. 원하는 색상으로 변경할 수 있습니다. */
}

.table-striped tbody tr:nth-of-type(odd) {
	background-color: #284B63; /* 변경할 색상을 여기에 지정 */
}
.table-striped tbody tr:nth-of-type(even) {
	background-color: #3C6E71; /* 변경할 색상을 여기에 지정 */
}

select.form-control {
	width: auto;
	margin-bottom: 2em;
	display: inline-block;
}

.rows {
	text-align: right;
}

.btn-item-add {
	background-color: #3C6E71;
	color: FFFFFF;
	border: none;
	padding: 10px 20px;
	border-radius: 20px;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
	transition: transform 0.2s, box-shadow 0.2s;
}

.gray {
	color: #3C6E71;
}

body>div>table>thead>tr:nth-child(2)>th:nth-child(1) {
	width: 8%
}

body>div>table>thead>tr:nth-child(2)>th:nth-child(2) {
	width: 50%
}

body>div>table>thead>tr:nth-child(2)>th:nth-child(3) {
	width: 14%
}

body>div>table>thead>tr:nth-child(2)>th:nth-child(4) {
	width: 17%
}

body>div>table>thead>tr:nth-child(2)>th:nth-child(5) {
	width: 11%
}
</style>
<script src="js/cafeitem_list.js"></script>
<title>item list</title>
</head>
<body>
	<div class="container">
	<%-- 게시글이 존재할 경우 --%>
	<c:if test="${listcount > 0}">
		<div class="rows">
			<span>줄보기</span>
			<select class="form-control" id="viewcount">
				<option value="1">1</option>
				<option value="3">3</option>
				<option value="5">5</option>
				<option value="7">7</option>
				<option value="10" selected>10</option>
			</select>
		</div>
		<table class="table table-striped">
			<thead>
			<tr>
				<th colspan="3">item list</th>
				<th colspan="2">
					<span>상품의 수: ${listcount}</span>
				</th>
			</tr>
			<tr>
				<th><div>UID</div></th>
				<th><div>상품 이름</div></th>
				<th><div>상품 가격</div></th>
				<th><div>상품 메뉴</div></th>
			</tr>
			</thead>
			<tbody>
				<c:set var="num" value="${listcount-(page-1)*limit}"/>
				<c:forEach var="i" items="${itemlist}">
				<tr>
					<td><%-- 번호 --%>
						<c:out value="${num}"/> <%-- num 출력 --%>
						<c:set var="num" value="${num-1}"/> <%-- num = num-1 --%>
					</td>
					<td><%-- 상품 이름 --%>
						<div>
							<a href="ItemDetail.ca?num=${i.ITEM_UID}">
								<c:if test="${i.ITEM_NAME.length() >= 20}">
								 <c:out value="${i.ITEM_NAME.substring(0,20)}..." />
								</c:if>
								<c:if test="${i.ITEM_NAME.length() < 20}">
								 <c:out value="${i.ITEM_NAME}" />
								</c:if>
							</a>
						</div>
					</td>
					<td><div>${i.ITEM_PRICE}</div></td>
					<td><div>${i.ITEM_MENU}</div></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		
	<div class="center-block">
    <ul class="pagination justify-content-center">
        <c:if test="${page <= 1}">
            <%-- 1페이지는 이전 버튼 비활성화(gray) --%>
            <li class="page-item">
                <a class="page-link gray">이전&nbsp;</a>
            </li>
        </c:if>
        <c:if test="${page > 1}">
            <li class="page-item">
                <a href="ItemList.ca?page=${page - 1}" class="page-link ">이전&nbsp;</a>
            </li>
        </c:if>

        <c:forEach var="a" begin="${startpage}" end="${endpage}">
            <c:if test="${a == page}">
                <%-- 현재 페이지 활성화(blue) --%>
                <li class="page-item active">
                    <a class="page-link">
                        <c:out value="${a}"/>
                    </a>
                </li>
            </c:if>
            <c:if test="${a != page}">
                <%-- 현재 페이지 외에는 클릭 가능한 링크(gray) --%>
                <li class="page-item">
                    <a href="ItemList.ca?page=${a}" class="page-link gray">
                        <c:out value="${a}"/>
                    </a>
                </li>
            </c:if>
        </c:forEach>

        <c:if test="${page >= maxpage}">
            <%-- 마지막 페이지는 다음 버튼 비활성화(gray) --%>
            <li class="page-item">
                <a class="page-link gray">&nbsp;다음</a>
            </li>
        </c:if>
        <c:if test="${page < maxpage}">
            <li class="page-item">
                <a href="ItemList.ca?page=${page + 1}" class="page-link gray">&nbsp;다음</a>
            </li>
        </c:if>
    </ul>
</div>
	</c:if>
	
	<%-- 게시글이 없는 경우 --%>
	<c:if test="${listcount == 0}">
		<h3 style="text-align:center">등록된 글이 없습니다.</h3>
	</c:if>
	
	<button type="button" class="btn btn-item-add float-right">상품 등록</button>
	</div>
</body>
</html>
