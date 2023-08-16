<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<jsp:include page="/header.jsp" />
<link href="css/B_BoardView.css" rel="stylesheet">
<script src="js/comment.js"></script>
<style>
</style>
</head>
<body>
	<div class="main">
		<div class="basic">
			<input type="hidden" id="loginid" value="${session_id}"
				name="loginid">
			<div class="container">
				<div id="V-subject">
					<!-- 제목 -->
					<c:out value="${boarddata.BOARD_SUBJECT}" />
				</div>


				<div class="B-inline">
					<!-- 작성자  -->
					<c:out value="${boarddata.BOARD_WRITER}" />
				</div>

				<div class="B-inline">최근게시판</div>
				<!-- 게시판명 -->
				<%-- <div><c:out value="${boarddata.BOARD_TYPE}"/></div> --%>

				<div class="B-inline">
					<!-- 조회수 -->
					조회수 :
					<c:out value="${boarddata.BOARD_READCOUNT}" />
				</div>
				<form class="B-inline" name="modifyForm" action="BoardModifyView.bo"
					method="post">
					<input type="hidden" name="modify-num"
						value="${boarddata.BOARD_NUM}" id="comment_board_num">
					<button type="submit" class="btn btn-primary" id="V-button1">수정</button>
				</form>
				<form class="B-inline" name="deleteForm"
					action="BoardDeleteAction.bo" method="post">
					<input type="hidden" name="delete-num"
						value="${boarddata.BOARD_NUM}" id="comment_board_num">
					<button type="submit" class="btn btn-primary" id="V-button2">삭제</button>
				</form>
			</div>
			<!-- container end -->

			<div>
				<!-- 내용 -->
				<div>
					<textarea id="V-content">${boarddata.BOARD_CONTENT}</textarea>
				</div>
			</div>

			<div class="last-line-container">
				<div class="last-line" id="V-file">첨부파일</div>

				<c:if test="${!empty boarddata.BOARD_FILE}">
					<div class="last-line">
						<img src="image/down.png" width="10px"> <a
							href="BoardFileDown.bo?filename=${boarddata.BOARD_FILE}"
							id="image_blank">${boarddata.BOARD_FILE}</a>
					</div>
				</c:if>

				<c:if test="${empty boarddata.BOARD_FILE}">
					<<div class="last-line">></div>
				</c:if>

				<div class="last-line">
					작성일자 :
					<!-- 작성일자 -->
					<c:out value="${boarddata.BOARD_DATE}" />
				</div>
			</div>
			<!-- Last-line end -->

			<div class='boardlist-btn-container'>
				<a href="BoardList.bo">
					<button class="listbtn">목록</button>
				</a>
			</div>
			<%-- 게시판 view  end --%>


		</div>

		<div class="comment-area">
			<div class="comment-head">
				<h3 class="comment-count">
					<%--superscript(윗첨자) --%>
				</h3>
				<div class="comment-order">
					<ul class="comment-order-list">
					</ul>
				</div>
			</div>
			<%-- comment-head end--%>
			<ul class="comment-list">
			</ul>
			<div class="comment-write">
				<div class="comment-write-area">
					<b class="comment-write-area-name">${session_id}</b>
					
					<span class="comment-write-area-count">0/200</span>
					<textarea placeholder="댓글을 작성하세요" rows="1"
						class="comment-write-area-text" maxLength="200"></textarea>
				</div>
				<div class="register-box">
					<div class="button btn-cancel">취소</div>
					<%-- 댓글의 취소는 display:none, 등록만 보이도록 합니다.--%>
					<div class="button btn-register">등록</div>
				</div>
			</div>
			<%--comment-write end--%>
		</div>
		<%-- comment-area end--%>

		<!-- basic end -->
	</div>
	<!-- 총묶음 -->
	<%-- class="container" end --%>
</body>
</html>