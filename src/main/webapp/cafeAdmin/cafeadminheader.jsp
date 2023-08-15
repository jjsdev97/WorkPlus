<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<style>
.footer-nav {
    /* ... 기존의 스타일 ... */
    background-color: #284B63;
    top: 0;  /* 상단에서 시작 */
    bottom: auto;  /* 이전의 fixed-bottom 속성 때문에 명시적으로 지정 */
    position: static;  
    transform: none;  /* 중앙 정렬 제거 */
    margin-bottom: 15px;
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
<c:if test = "${empty id}">
	<script>
		location.href = 'login.cnet';
	</script>
</c:if>	
<nav class="navbar navbar-expand-sm right-block navbar-dark fixed-bottom footer-nav">
	<ul class="navbar-nav">
		<c:if test = "${!empty id}">
			<li class="nav-item"><a class="nav-link" href="logout.cnet">${id}님 (logout)</a></li>
			<li class="nav-item"><a class="nav-link" href="memberUpdate.cnet">정보수정</a></li>
			<c:if test="${id=='cafe1'}">
				<li class='nav-item dropdown'>
					<a class="nav-link dropdown-toggle" href="#" id="navbardrop" 
					   data-toggle="dropdown">관리자</a>
					<div class="dropdown-menu">
    					<a class="dropdown-item" href="memberlist.cnet">회원정보</a>
    					<a class="dropdown-item" href="ItemList.ca">게시판</a>
					</div>
					</li>
	<!-- 				
		    <li class="nav-item"><a class="nav-link" href="main.cafe">home</a></li>
	 -->				
			</c:if>
		</c:if>
	</ul>
</nav>
