<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<style>
.footer-nav {
	background-color: #284B63;  /* 배경색을 #353535로 설정 */
	position: relative;         /* 상대적 위치 지정 */
	width: 100%;                /* 너비를 전체로 설정 */
	padding: 10px 0;           /* 상단과 하단에 패딩 추가 */
}

.footer-nav .navbar-nav .nav-item {
	margin-right: 10px; /* 각 nav-item 사이의 간격 */
}

body > nav.navbar {
	justify-content: flex-end; /* 오른쪽 정렬 */
}

.dropdown-menu {
	min-width: 0rem;
}

.navbar-dark .navbar-nav .nav-link {
	color: rgb(255, 255, 255);
}

textarea {
	resize: none;
}
</style>

<nav
	class="navbar navbar-expand-sm right-block navbar-dark fixed-bottom footer-nav">
	<ul class="navbar-nav">
		<li class="nav-item"><a class="nav-link" href="#">주문내역</a></li>
		<li class="nav-item"><a class="nav-link" href="WorkPlus/main.cafe">cafemain</a></li>
	</ul>
</nav>


