<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="css/header.css" rel="stylesheet">
<script src="js/jquery-3.7.0.js"></script>
<script src="js/header.js"></script>

<nav class="navbar">
	<a href="#" class="nav_logo"><img src="img/logo.jpg" id="logo"></a>
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
		<li class="sidebar-item"><a href="#" class="sidebar-anchor">게시판<img class="coll-arrow" src="img/collapse-arrow.png"></a>
			<ul class="second-menu">
				<li class="second-item">작성하기</li>
				<li class="second-item">최근게시물</li>
				<li class="second-item">중요게시물</li>
				<li class="second-item">-공통게시판-</li>
				<li class="second-item">공지사항</li>
				<li class="second-item">자유게시판</li>
				<li class="second-item">-팀별게시판-</li>
				<li class="second-item">팀1</li>
				<li class="second-item">팀2</li>
			</ul>
		</li>
		<li class="sidebar-item"><a href="#" class="sidebar-anchor">일정</a></li>
		<li class="sidebar-item"><a href="#" class="sidebar-anchor">전자결재<img class="coll-arrow" src="img/collapse-arrow.png"></a>
			<ul class="second-menu">
				<li class="second-item">작성하기</li>
				<li class="second-item">-진행 중-</li>
				<li class="second-item">전체</li>
				<li class="second-item">대기</li>
				<li class="second-item">확인</li>
				<li class="second-item">예정</li>
				<li class="second-item">진행</li>
				<li class="second-item">-완료-</li>
				<li class="second-item">전체</li>
				<li class="second-item">기안</li>
				<li class="second-item">결재</li>
				<li class="second-item">수신</li>
				<li class="second-item">회람</li>
				<li class="second-item">반려</li>
			</ul>
		</li>
		<li class="sidebar-item"><a href="#" class="sidebar-anchor">인사</a></li>
		<li class="sidebar-item"><a href="#" class="sidebar-anchor">메신저<img class="coll-arrow" src="img/collapse-arrow.png"></a>
			<ul class="second-menu">
				<li class="second-item">메인</li>
				<li class="second-item">채팅</li>
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