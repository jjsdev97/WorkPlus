<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<!-- <jsp:include page="../header.jsp"/> -->
<link rel="stylesheet" href="css/cafeitemview.css" type="text/css">
<meta charset="UTF-8">
<script src="js/view.js"></script>
<title>item - view</title>
</head>
<body>
	<div class="container">
		<table class="table">
			<tr>
				<th colspan="2">MVC 게시판 - view 페이지</th>
			</tr>
			<tr>
				<td><div>고유번호</div></td>
				<td><div>${itemdata.item_uid}</div></td>
			</tr>
			<tr>
				<td><div>이름</div></td>
				<td><div>${itemdata.item_name}</div></td>
			</tr>
			<tr>
				<td><div>가격</div></td>
				<td><div>${itemdata.item_price}</div></td>
			</tr>

			<tr>
				<td><div>첨부파일</div></td>
				<c:if test="${!empty boarddata.board_file}">
					<td><img src="image/down.png" width="10px"> <a
						href="BoardFileDown.bo?filename=${boarddata.board_file}"> 파일
							다운로드 </a></td>
				</c:if>
			</tr>
		
			<tr>
				<td colspan="2" class="center"><c:if
						test="${boarddata.board_name == id || id eq 'admin' }">
						<a href="BoardModifyView.bo?num=${boarddata.board_num}">
							<button class="btn btn-info">수정</button>
						</a>
						<%-- href 주소를 #으로 설정 --%>
						<a href="#">
							<button class="btn btn-danger" data-toggle="modal"
								data-target="#myModal">삭제</button>
						</a>
					</c:if> <a href="BoardList.bo">
						<button class="btn btn-warning">목록</button>
				</a> <a href="BoardReplyView.bo?num=${boarddata.board_num}">
						<button class="btn btn-success">답변</button>
				</a></td>
			</tr>
		</table>
	</div>

	<div class="modal" id="myModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<%-- Modal body --%>
				<div class="modal-body">
					<form name="deleteForm" action="BoardDeleteAction.bo" method="post">
						<%-- http://localhost:8088/Board_Ajax/BoardDetailAction.bo?num=12
    				주소에서 num 파라미터를 넘기는 것을 ${param.num} or ${boarddata.board_num}
    				를 사용해서 catch --%>
						<input type="hidden" name="num" value="${param.num}"
							id="comment_board_num">
						<div class="form-group">
							<label for="pwd">비밀번호</label> <input type="password"
								class="form-control" placeholder="Enter password"
								name="board_pass" id="board_pass">
						</div>
						<button type="submit" class="btn btn-primary">전송</button>
						<button type="button" class="btn btn-danger" data-dismiss="modal">취소</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
