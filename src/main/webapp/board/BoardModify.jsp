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

		<form action="BoardModifyAction.bo" method="post"
			enctype="multipart/form-data" name="boardform">
			<input type="hidden" name="board_num" value="${boarddata.BOARD_NUM}">

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
					<div class="board_subject">제 목</div>
					<input class="board_subject_main" type="text" id="board_subject"
						name="BOARD_SUBJECT" value="${boarddata.BOARD_SUBJECT }">
				</div>
			</div>

			<!-- 글씨체, 폰트, 등 조절하는 라벨 한줄 끌어올 예정 -->

			<textarea id="board_content" name="BOARD_CONTENT" cols="60" rows="30">${boarddata.BOARD_CONTENT}</textarea>

			<div>
				<label> 파일 첨부 <img src="image/attach.png" alt="파일첨부">
					<input type="file" id="upfile" name="board_file">
				</label> <span id="filevalue">${boarddata.BOARD_FILE}</span>
			</div>

			<div class="board_main_buttons">
				<button onclick="board_noti()" type="submit" id="board_notification">공지로
					등록</button>
				<button type="reset" id="board_cancel">취소</button>
				<!-- <button type=submit id="sub_save">임시보관</button> -->
				<button type="submit" id="board_save">수정</button>
			</div>
		</form>
	</div>
	<!-- main -->
	<script>
		$(document).ready(function() {

			function show() {
				// 파일 이름이 있는 경우  remove 이미지를 보이게 하고 
				// 파일 이름이 없는 경우  remove 이미지 보이지 않게 합니다.
				if ($('#filevalue').text() == '') {
					$(".remove").css('display', 'none');
				} else {
					$(".remove").css({
						'display' : 'inline-block',
						'position' : 'relative',
						'top' : '-5px'
					});
				}
			}

			show();

			$("#upfile").change(function() {
				check++;
				const inputfile = $(this).val().split('\\');

				$('#filevalue').text(inputfile[inputfile.length - 1]);
				show();
				console.log(check);
			});

			// remove 이미지를 클릭하면 파일명을 ''로 변경하고 remove 이미지를 보이지 않게 합니다.
			$(".remove").click(function() {
				$('#filevalue').text('');
				$(this).css('display', 'none');
			})

		})
	</script>
</body>
</html>