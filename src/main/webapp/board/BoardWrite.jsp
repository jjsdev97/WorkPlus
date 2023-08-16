<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<jsp:include page="../header.jsp" />
<link href="css/B_BoardWrite.css" rel="stylesheet">
</head>
<body>
	<div class="main">
		<!-- BoardWrite.bo로 넘어감 (frontcont에서 받음) -->
		<!-- 입력된 값을 액션 서블릿으로 넘김 -->
		<form id="B-main" action="BoardAddAction.bo" method="post"
			enctype="multipart/form-data" name="boardform">
			<div class="board_drop" id="board_type">
				<div class="board_select">
					게시판 종류 <select class="select_option" id="select_board1">
						<option>공통 게시판</option>
						<option>팀별 게시판</option>
					</select>
				</div>

				<div class="board_select">
					게시판 선택 <select class="select_option" id="select_board2"
						name="BOARD_TYPE">
						<option value="notification">공지사항</option>
						<option value="free">자유게시판</option>
						<option value="strategy">전략기획팀</option>
						<option value="HRM">인사노무팀</option>
						<option value="accounting">경리회계팀</option>
					</select>
				</div>
			</div>
			<div>
				<div>
					<div class="board_subject">제 목
					<input class="board_subject_main" type="text" id="board_subject"
						name="BOARD_SUBJECT" placeholder="제목을 입력하세요">
						</div>
				</div>
			</div>

			<!-- 글씨체, 폰트, 등 조절하는 라벨 한줄 끌어올 예정 -->

			<textarea id="board_content" name="BOARD_CONTENT" cols="60" rows="30"></textarea>
            
			
            <div class="file_select">
				<input type="file" id="upfile" name="board_file">
				<!-- <span id="filevalue"></span> -->
            </div>
            
			<div class="board_main_buttons">
				<button class="B_button" onclick="board_noti()" type="submit" id="board_notification">
				공지로 등록</button>
				<button class="B_button" type="reset" id="board_cancel">취소</button>
				<!-- <button type=submit id="sub_save">임시보관</button> -->
				<button class="B_button" type="submit" id="board_save">저장</button>
			</div>
		</form>
	</div>
	<!-- main -->
	<script>
		$(document).ready(function() {

			$("#upfile").change(function() {
				console.log($(this).val())
				const inputfile = $(this).val().split('\\');
				$('#fileValue').text(inputfile[inputfile.length - 1]);
			});

		})
		
		
		function board_noti() {
			if (!confirm("공지글로 등록 하시겠습니까?")) {
				alert("취소 되었습니다.");
			} else {
				alert("공지글로 등록 되었습니다.");
			}
		}
	</script>
</body>
</html>