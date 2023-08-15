<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>회원관리 시스템 관리자모드(회원 목록 보기)</title>
<jsp:include page="/cafeAdmin/cafeadminheader.jsp" />
<%--
1. 검색어를 입력한 후 다시 memberList.net으로 이동한  경우 검색 필드와 검색어가 나타나도록 합니다.

2. 검색 필드를 변경하면 검색어 입력창에 placeholder 나타나도록 합니다
   예로 아이디를 선택하면 placeholder로 "아이디 입력하세요"라고 나타납니다.
   예로 이름을 선택하면 placeholder로 "이름 입력하세요"라고 나타납니다.
   예로 나이를 선택하면 placeholder로 "나이 입력하세요"라고 나타납니다.
   예로 성별을 선택하면 placeholder로 "여 또는 남 입력하세요"라고 나타납니다.

3. 검색 버튼 클릭시 다음을 체크합니다.
   1) 검색어를 입력하지 않은 경우 "검색어를 입력하세요" 라고 대화상자가 나타나게 합니다.
   2) 나이는 두 자리 숫자가 아닌 경우 "나이는 형식에 맞게 입력하세요(두자리 숫자)" 라고 대화상자가 나타나게 합니다.
   3) 성별은 "남" 또는 "여"가 아닌 경우 "남 또는 여를 입력하세요" 라고 대화상자가 나타나게 합니다.
   
4. 회원 목록의 삭제를 클릭한 경우
   confirm("정말 삭제하시겠습니까?")를 실행해서 취소를 클릭하면 "membereDelete.net"로 이동하지 않습니다.
 --%>

<style>
table caption {
	caption-side: top;
	text-align: center
}

h1 {
	text-align: center
}

li .gray {
	color: gray;
}

body>div>table>tbody>tr>td:last-child>a {
	color: red
}

form {
	margin: 0 auto;
	width: 80%;
	text-align: center
}

select {
	color: #495057;
	background-color: #fff;
	background-clip: padding-box;
	border: 1px solid #ced4da;
	border-radius: .25rem;
	transition: border-color .15s ease-in-out, box-shadow .15s ease-in-out;
	outline: none;
}

.container {
	width: 60%
}

td:nth-child(1) {
	width: 33%
}

.input-group {
	margin-bottom: 3em
}
</style>

</head>
<body>
	<script>
		$(function() {
			let selectedValue;
			const $input = $("input[name=search_word]");

			$('#viewcount').change(function() {
				
				selectedValue = $(this).val();
				
				if (selectedValue != '-1') {
					$('#viewcount').val(selectedValue);
				} else {
					selectedValue = 0;
				}
				//검색 후 selectedValue값에 따라 placeholder 가 나타나도록 합니다.

				const message = [ "아이디", "이름"];

				$input.attr("placeholder", message[selectedValue] + " 입력하세요");
				
			})

			//검색 버튼을 클릭한 경우
			$('button').click(function() {

				if ($input.val() == '') {
					alert("검색어를 입력하세요");
					$input.focus();
					return false;
				}
				const word = $input.val();

			}); //button click end

			//회원 목록의 삭제 클릭
			$("tr > td:nth-child(3) > a").click(function(event) {
				const answer = confirm("정말 삭제하시겠습니까?");
				console.log(answer); //취소를 클릭한 경우 - false

				if (!answer) {
					event.preventDefault(); // 취소를 클릭시 페이지 이동 막음
				}
			})// 삭제클릭 end

		})//ready end
	</script>
	<div class="container">
		<form action="memberList.cnet" method="post">
<!--		
			<div class="input-group">
				<select id="viewcount" name="search_field">
					<option value="0" selected>아이디</option>
					<option value="1">이름</option>
				</select> <input name="search_word" type="text" class="form-control" placeholder="아이디 입력하세요" value="${search_word}">
 		    <button class="btn btn-primary" type="submit">검색</button> 
			</div>
-->			
		</form>
		<c:if test="${listcount > 0 }">
			<%-- 회원이 있는 경우 --%>
			<table class="table table-striped">
				<caption style="font-weight: bold">회원 목록</caption>
				<thead>
					<tr>
						<th colspan="2">MVC 게시판 - 회원 정보 list</th>
						<th><span>회원 수 : ${listcount }</span></th>
					</tr>
					<tr>
						<td>아이디</td>
						<td>이름</td>
						<td>삭제</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="m" items="${totallist}">
						<tr>
							<td><a href="memberlist.cnet?id=${m.id}">${m.id}</a></td>
							<td>${m.name}</td>
							<td><a href="memberDelete.cnet?id=${m.id}">삭제</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div>
				<ul class="pagination justify-content-center">
					<c:if test="${page <= 1 }">
						<li class="page-item"><a class="page-link gray">이전&nbsp;</a></li>
					</c:if>
					<c:if test="${page > 1 }">
						<li class="page-item"><a href="memberList.cnet?page=${page-1}&search_field=${search_field}&search_word=${search_word}" class="page-link">이전</a>&nbsp;</li>
					</c:if>

					<c:forEach var="a" begin="${startpage}" end="${endpage}">
						<c:if test="${a == page }">
							<li class="page-item  active"><a class="page-link">${a}</a></li>
						</c:if>
						<c:if test="${a != page }">
							<c:url var="go" value="memberList.cnet">
								<c:param name="search_field" value="${search_field}" />
								<c:param name="search_word" value="${search_word}" />
								<c:param name="page" value="${a}" />
							</c:url>
							<li class="page-item"><a href="${go}" class="page-link">${a}</a></li>
						</c:if>
					</c:forEach>

					<c:if test="${page >= maxpage }">
						<li class="page-item"><a class="page-link gray">&nbsp;다음</a></li>
					</c:if>
					<c:if test="${page < maxpage }">
						<c:url var="next" value="memberList.cnet">
							<c:param name="search_field" value="${search_field}" />
							<c:param name="search_word" value="${search_word}" />
							<c:param name="page" value="${page+1}" />
						</c:url>
						<li class="page-item"><a href="${next}" class="page-link">&nbsp;다음</a></li>
					</c:if>
				</ul>
			</div>
		</c:if>
	</div>

	<%-- 회원이 없는 경우 --%>
	<c:if test="${listcount == 0 && empty search_word }">
		<h1>회원이 없습니다.</h1>
	</c:if>

	<c:if test="${listcount == 0 &&  !empty search_word}">
		<h1>검색 결과가 없습니다.</h1>
	</c:if>
</body>
</html>