<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<style>
.footer-nav {
    height: auto;  /* 높이는 자동으로 설정하되, 내용에 따라 조절됩니다. */
    max-height: 300px; /* 원하는 최대 높이를 설정. 여기에서는 300px로 예시 */
    width: 200px;  /* 사이드바의 너비 지정 */
    position: fixed; /* 고정 위치 */
    top: 50%;  /* 화면 중앙에서 시작 */
    transform: translateY(-50%); /* 세로 방향으로 중앙 정렬 */
    right: 0; /* 오른쪽에 고정 */
    left: auto; /* 추가 */
    background: #3C6E71; /* 배경색 변경 */
    overflow-y: auto; /* 내용이 많아지면 스크롤바 추가 */
}

.footer-nav .navbar {
    flex-wrap: nowrap; /* 이 속성을 추가하여 항목들이 강제로 다음 줄로 넘어가지 않도록 합니다. */
}

.footer-nav .navbar-nav .nav-item {
    margin-right: 10px; /* 각 nav-item 사이의 간격 */
}

.navbar {
    flex-direction: column; /* 항목들을 세로로 배치 */
    margin-right: 0; /* 마진 제거 */
    padding-right: 1em; /* 패딩 값 조정 */
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
					<li class="nav-item"><a class="nav-link" href="main.cafe">home</a></li>
			</c:if>
		</c:if>
	</ul>
</nav>




