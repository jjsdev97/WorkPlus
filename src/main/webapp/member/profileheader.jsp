<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="/WorkPlus/css/join.css" rel="stylesheet">
<script src="/WorkPlus/js/jquery-3.7.0.js"></script>
<script src="/WorkPlus/js/header.js"></script>
<link rel="shortcut icon" href="#">

<nav class="navbar">
	<a href="#" class="nav_logo"><img src="${pageContext.request.contextPath}/img/logo.jpg" id="logo"></a>
	<div class="navbar-nav">
		<div class="nav-item">
			<a href="#"><img src="http://via.placeholder.com/55x55" alt=""></a>
		</div>
		<div class="nav-item">
			<a href="#" id="profile"><img src="http://via.placeholder.com/55x55" alt="">홍길동</a>
		</div>
		<div class="nav-item">
			<a href="#"><img src="http://via.placeholder.com/55x55" alt=""></a>
		</div>
	</div>

</nav>

<div class="sidebar sidebar-user">
	<ul class="sidebar-menu">
		<li class="sidebar-item"><a href="#" class="sidebar-anchor">이메일</a></li>
		<li class="sidebar-item"><a href="#" class="sidebar-anchor">조직도</a></li>
		<li class="sidebar-item"><a href="#" class="sidebar-anchor">게시판</a>
			<ul class="second-menu">
				<li class="second-item"><a href="#" class="second-anchor">작성하기</a></li>
				<li class="second-item"><a href="#" class="second-anchor">최근게시물</a></li>
				<li class="second-item"><a href="#" class="second-anchor">중요게시물</a></li>
				<li class="second-item">-공통게시판-</li>
				<li class="second-item"><a href="#" class="second-anchor">공지사항</a></li>
				<li class="second-item"><a href="#" class="second-anchor">자유게시판</a></li>
				<li class="second-item">-팀별게시판-</li>
				<li class="second-item"><a href="#" class="second-anchor">팀1</a></li>
				<li class="second-item"><a href="#" class="second-anchor">팀2</a></li>
			</ul>
		</li>
		<li class="sidebar-item"><a href="#" class="sidebar-anchor">일정</a></li>
		<li class="sidebar-item"><a href="#" class="sidebar-anchor">전자결재</a>
			<ul class="second-menu">
				<li class="second-item"><a href="#" class="second-anchor">작성하기</a></li>
				<li class="second-item">-진행 중-</li>
				<li class="second-item"><a href="#" class="second-anchor">전체</a></li>
				<li class="second-item"><a href="#" class="second-anchor">대기</a></li>
				<li class="second-item"><a href="#" class="second-anchor">확인</a></li>
				<li class="second-item"><a href="#" class="second-anchor">예정</a></li>
				<li class="second-item"><a href="#" class="second-anchor">진행</a></li>
				<li class="second-item">-완료-</li>
				<li class="second-item"><a href="#" class="second-anchor">전체</a></li>
				<li class="second-item"><a href="#" class="second-anchor">기안</a></li>
				<li class="second-item"><a href="#" class="second-anchor">결재</a></li>
				<li class="second-item"><a href="#" class="second-anchor">수신</a></li>
				<li class="second-item"><a href="#" class="second-anchor">회람</a></li>
				<li class="second-item"><a href="#" class="second-anchor">반려</a></li>
			</ul>
		</li>
		<li class="sidebar-item"><a href="#" class="sidebar-anchor">인사</a></li>
		<li class="sidebar-item"><a href="#" class="sidebar-anchor">메신저</a>
			<ul class="second-menu">
				<li class="second-item"><a href="#" class="second-anchor">메인</a></li>
				<li class="second-item"><a href="#" class="second-anchor">채팅</a></li>
			</ul>
		</li>
		<li class="sidebar-item"><a href="#" class="sidebar-anchor">카페</a></li>
	</ul>

	<button class="btn_menu" id="btn_admin">관리자 페이지</button>
</div>

<div class="sidebar sidebar-admin">
	<ul class="sidebar-menu">
		<li class="sidebar-item">&nbsp;관리자 페이지</li>
		<li class="sidebar-item"><a href="#" class="sidebar-anchor">조직 관리</a></li>
		<li class="sidebar-item"><a href="#" class="sidebar-anchor">사용자 관리</a></li>
		<li class="sidebar-item"><a href="#" class="sidebar-anchor">전자결재 관리</a></li>
		<li class="sidebar-item"><a href="#" class="sidebar-anchor">카페 직원 관리</a></li>
	</ul>

	<button class="btn_menu" id="btn_user">일반 페이지</button>

</div>