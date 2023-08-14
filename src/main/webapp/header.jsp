<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="/WorkPlus/css/header.css" rel="stylesheet">
<script src="/WorkPlus/js/jquery-3.7.0.js"></script>
<script src="/WorkPlus/js/header.js"></script>
<link rel="shortcut icon" href="#">



<div class="modal-background"></div>

<nav class="navbar">
	<a href="main.com" class="nav_logo"><img src="${pageContext.request.contextPath}/img/logo.jpg" id="logo"></a>
	<div class="navbar-nav">
		<div class="nav-item">
			<a href="logout.et" id="logout">로그아웃</a>
		</div>
		<div class="nav-item">
			<a href="#"><img src="${pageContext.request.contextPath}/img/header-alarm.png" id="header-alarm"></a>
		</div>
		<div class="nav-item">
			<a href="profileUpdate.et" id="profile"><img src="http://via.placeholder.com/55x55" alt="">홍길동</a>
		</div>
		<div class="nav-item">
			<a href="#"><img src="${pageContext.request.contextPath}/img/header-setting.png" id="header-setting"></a>
		</div>
	</div>
</nav>


<div class="sidebar sidebar-user">
	<ul class="sidebar-menu">
		<li class="sidebar-item"><a href="#" class="sidebar-anchor">이메일</a></li>
		<li class="sidebar-item"><a href="deptGetChart.dt" class="sidebar-anchor">조직도</a></li>
		<li class="sidebar-item"><a href="#" class="sidebar-anchor" id="board-sidebar">게시판<img class="coll-arrow" src="${pageContext.request.contextPath}/img/collapse-arrow.png"></a>
			<ul class="second-menu" id="board-second-menu">
				<li class="second-item"><a href="BoardWrite.bo" class="second-anchor">작성하기</a></li>
				<li class="second-item"><a href="BoardList.bo" class="second-anchor">전체게시물</a></li>
			</ul></li>
		<li class="sidebar-item"><a href="#" class="sidebar-anchor">일정</a></li>
		<li class="sidebar-item"><a href="#" class="sidebar-anchor" id="approval-sidebar">전자결재<img class="coll-arrow" src="${pageContext.request.contextPath}/img/collapse-arrow.png"></a>
			<ul class="second-menu" id="approval-second-menu">
				<li class="second-item"><a href="approvalWrite.apv" class="second-anchor">작성하기</a></li>
				<li class="second-item">-진행 중-</li>
				<li class="second-item"><a href="#" class="second-anchor">전체</a></li>
				<li class="second-item"><a href="#" class="second-anchor">대기</a></li>
				<li class="second-item"><a href="#" class="second-anchor">진행</a></li>
				<li class="second-item">-완료-</li>
				<li class="second-item"><a href="#" class="second-anchor">전체</a></li>
				<li class="second-item"><a href="#" class="second-anchor">결재</a></li>
				<li class="second-item"><a href="#" class="second-anchor">반려</a></li>
			</ul></li>
		<li class="sidebar-item"><a href="#" class="sidebar-anchor">인사</a></li>
		<li class="sidebar-item"><a href="#" class="sidebar-anchor" id="chat-sidebar">메신저<img class="coll-arrow" src="${pageContext.request.contextPath}/img/collapse-arrow.png"></a>
			<ul class="second-menu" id="chat-second-menu">
				<li class="second-item"><a href="Chatmain.chat" class="second-anchor">메인</a></li>
				<li class="second-item"><a href="Chatlist.chat" class="second-anchor">채팅</a></li>
			</ul></li>
		<li class="sidebar-item"><a href="main.cafe" class="sidebar-anchor">카페</a></li>
	</ul>

	<button class="btn_menu" id="btn_admin">관리자 페이지</button>
</div>



<div class="sidebar sidebar-admin">
	<ul class="sidebar-menu">
		<li class="sidebar-item">&nbsp;관리자 페이지</li>
		<li class="sidebar-item"><a href="deptAdmin.dt" class="sidebar-anchor">조직 관리</a></li>
		<li class="sidebar-item"><a href="memberList.et" class="sidebar-anchor">사용자 관리</a></li>
		<li class="sidebar-item"><a href="#" class="sidebar-anchor">전자결재 관리</a></li>
		<li class="sidebar-item"><a href="#" class="sidebar-anchor">카페 직원 관리</a></li>
	</ul>

	<button class="btn_menu" id="btn_user">일반 페이지</button>

</div>

<script>
	var admin_check = '<%=(String) session.getAttribute("id")%>';
	var menu = '<%=(String) session.getAttribute("menu")%>';
	var selectedmenu = '<%=(String) session.getAttribute("selectedmenu")%>';

	if (admin_check != 'admin') {
		$('.btn_menu').css('display', 'none');
	}

	if (selectedmenu == 'approval') {
		$('#approval-second-menu').css('transition', 'width 0s');
		$('#approval-sidebar').addClass('sidebar-anchor-collapse');

		$('#approval-second-menu').css('height', function() {
			var length = $(this).children().length;

			return 27.6 * length + "px";
		})

	} else if (selectedmenu == 'board') {
		$('#board-second-menu').css('transition', 'width 0s');
		$('#board-sidebar').addClass('sidebar-anchor-collapse');

		$('#board-second-menu').css('height', function() {
			var length = $(this).children().length;

			return 27.6 * length + "px";
		})

	} else if (selectedmenu == 'chat') {
		$('#chat-second-menu').css('transition', 'width 0s');
		$('#chat-sidebar').addClass('sidebar-anchor-collapse');

		$('#chat-second-menu').css('height', function() {
			var length = $(this).children().length;

			return 27.6 * length + "px";
		})
	}

	/* 어드민, 일반 사용자 메뉴 변경 */

	if (menu == 'admin') {
		$('.sidebar-user').css("visibility", "hidden");
		$('.sidebar-admin').css("visibility", "visible");
	} else if (menu == 'user') {
		$('.sidebar-user').css("visibility", "visible");
		$('.sidebar-admin').css("visibility", "hidden");
	}
</script>