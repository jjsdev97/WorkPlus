<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<jsp:include page="/header.jsp" />
<style>

</style>
</head>
<body>
	<div class="main">
	<input type="hidden" id="loginid" value="${id}" name="loginid"><%--view.js에서 사용하기 위해 추가합니다. --%>
	<div class="container">
		<table class="table">
		    <tr>
		       <td><div><c:out value="${boarddata.BOARD_SUBJECT}" /></div></td><!-- 제목 -->
		    </tr>
		    <tr>
		       <td><div><c:out value="${boarddata.BOARD_WRITER}"/></div></td> <!-- 작성자  -->
		       <td><div><c:out value="${boarddata.BOARD_TYPE}"/></div></td> <!-- 게시판 종류-->
		       <td><div><c:out value="${boarddata.BOARD_READCOUNT}"/></div></td> <!-- 조회수 -->
		       <td><div><button type=submit>수정</button></div></td>
		       <td><div><button type=submit>삭제</button></div></td>
		    </tr>
		    
		    <tr>
		       <td><div><textarea>${boarddata.BOARD_CONTENT}</textarea></div></td> <!-- 내용 -->
		       <td><div><c:out value="${boarddata.BOARD_DATE}"/></div></td> <!-- 작성일자 -->
		    </tr>
		       
		  <%--  <c:if test="${boarddata.board_re_lev==0}"> 
				원문글인 경우에만 첨부파일을 추가 할 수 있습니다.
				<tr>
					<td><div>첨부파일</div></td>
					
					파일을 첨부한 경우
					<c:if test="${!empty boarddata.BOARD_FILE}">
						<td><img src="image/down.png" width="10px"> 
							<a href="BoardFileDown.bo?filename=${boarddata.board_file}">${boarddata.board_file}</a></td>
					</c:if>
					
					파일을 첨부하지 않은 경우
					<c:if test="${empty boarddata.BOARD_FILE}">
						 <td></td>
					</c:if>
				</tr>
			</c:if> --%>

			<tr>
				<td colspan="2" class="center">
				   <c:if test="${boarddata.board_name == id || id == 'admin' }">
						<a href="BoardModifyView.bo?num=${boarddata.board_num}">
							<button class="btn btn-info">수정</button>
						</a>
						<%-- href의 주소를 #으로 설정합니다. --%>
						<a href="#">
							<button class="btn btn-danger" data-toggle="modal"
								data-target="#myModal">삭제</button>
						</a>
					</c:if> 
					 <a href="BoardList.bo">
						<button class="btn btn-warning">목록</button>
				     </a> 
				     <a href="BoardReplyView.bo?num=${boarddata.board_num}">
						<button class="btn btn-success">답변</button>
				    </a>
				 </td>
			</tr>
		</table>
		<%-- 게시판 view  end --%>

		<%-- modal 시작 --%>
		<div class="modal" id="myModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<%-- Modal body --%>
					<div class="modal-body">
						<form name="deleteForm" action="BoardDeleteAction.bo"	method="post">
							<%--http://localhost:8088/Board/BoardDetailAction.bo?num=22
				                                주소를 보면 num을 파라미터로 넘기고 있습니다. 
				                                 이 값을 가져와서  ${param.num}를 사용
				                                 또는 ${boarddata.board_num}
			                 --%>
							<input type="hidden" name="num" value="${param.num}"  
							                     id="comment_board_num">
							<div class="form-group">
								<label for="pwd">비밀번호</label> 
								<input type="password"
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
		<%-- id="myModal" end --%>

		<div class="comment-area">
			<div class="comment-head">
				<h3 class="comment-count">
					댓글 <sup id="count"></sup><%--superscript(윗첨자) --%>
				</h3>
				<div class="comment-order">
					<ul class="comment-order-list">
					</ul>
				</div>
			</div><%-- comment-head end--%>
			<ul class="comment-list">
			</ul>
			<div class="comment-write">
				<div class="comment-write-area">
					<b class="comment-write-area-name" >${id}</b> <span
						class="comment-write-area-count">0/200</span>
					<textarea placeholder="댓글을 남겨보세요" rows="1"
						class="comment-write-area-text" maxLength="200"></textarea>
				</div>
				<div class="register-box" >
					<div class="button btn-cancel" >취소</div><%-- 댓글의 취소는 display:none, 등록만 보이도록 합니다.--%>
					<div class="button btn-register" >등록</div>
				</div>
			</div><%--comment-write end--%>
		</div><%-- comment-area end--%>
	</div> <%-- class="container" end --%>
	</div>
</body>
</html>