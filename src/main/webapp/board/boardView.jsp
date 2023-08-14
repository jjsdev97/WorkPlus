<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<jsp:include page="/header.jsp" />
<script src="js/comment.js"></script>
<style>
</style>
</head>
<body>
	<div class="main">
		<input type="hidden" id="loginid" value="${session_id}" name="loginid">
		<div class="container">
			<div>
				<c:out value="${boarddata.BOARD_SUBJECT}" />
			</div>
			<!-- 제목 -->
			<div>
				<c:out value="${boarddata.BOARD_WRITER}" />
			</div>
			<!-- 작성자  -->
			<%-- <div><c:out value="${boarddata.BOARD_TYPE}"/></div> --%>
			<!-- 게시판 종류-->
			<div>
				조회수 :
				<c:out value="${boarddata.BOARD_READCOUNT}" />
			</div>
			<!-- 조회수 -->

			<form name="deleteForm" action="BoardDeleteAction.bo" method="post">
				<input type="hidden" name="delete-num" value="${boarddata.BOARD_NUM}"
					id="comment_board_num">
				<button type="submit" class="btn btn-primary">삭제</button>
			</form>
			<form name="modifyForm" action="BoardModifyView.bo" method="post">
				<input type="hidden" name="modify-num" value="${boarddata.BOARD_NUM}"
					id="comment_board_num">
				<button type="submit" class="btn btn-primary">수정</button>
			</form>
		</div>
		<div>
			<div>
				<textarea>${boarddata.BOARD_CONTENT}</textarea>
			</div>
			<!-- 내용 -->
			<div>
				<c:out value="${boarddata.BOARD_DATE}" />
			</div>
			<!-- 작성일자 -->
		</div>
		<div>첨부파일</div>

		<c:if test="${!empty boarddata.BOARD_FILE}">
			<div>
				<img src="image/down.png" width="10px"> <a
					href="BoardFileDown.bo?filename=${boarddata.BOARD_FILE}">${boarddata.BOARD_FILE}</a>
			</div>
		</c:if>

		<c:if test="${empty boarddata.BOARD_FILE}">
			<div></div>
		</c:if>


		<div>
			<a href="BoardList.bo">
				<button class="btn btn-warning">목록</button>
			</a>
		</div>
		<%-- 게시판 view  end --%>

		<div class="comment-area">
			<div class="comment-head">
				<h3 class="comment-count">
					댓글 <sup id="count"></sup>
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
					<b class="comment-write-area-name">${session_id}</b> <span
						class="comment-write-area-count">0/200</span>
					<textarea placeholder="댓글을 남겨보세요" rows="1"
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
	</div>
	<%-- class="container" end --%>
</body>
</html>