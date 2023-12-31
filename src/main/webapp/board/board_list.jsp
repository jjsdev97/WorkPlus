<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/header.jsp" />
<link href="css/B_BoardList.css" rel="stylesheet">
<style>
</style>
</head>
<body>
	<div class="main">
	<form id="b-main">
	<div class="board_recent"><h4>전체게시물</h4></div>
	<table class="board_table">
	   <thead id="t-head">
	     <tr class="List_subject">
	      <th><div class="list">★</div></th>
	      <th><div>순번</div></th>
	      <th><div>제목</div></th>
	      <th><div>작성자</div></th>
	      <th><div>작성일자</div></th>
	      <th><div>조회수</div></th>
	     </tr>
	   </thead>
	   <tbody> <!-- 아직 이해안됨 이부분은 꼭 봐야함-->
	   <c:set var="num" value="${listcount-(page-1)*limit}"/> 
	   <c:forEach var="b" items="${boardlist}">  <!-- DB에서 데이터를 뽑아오는 과정 반복-->
       <tr>
        <td>☆
       </td>
	   <td><%--번호 --%>
		 <c:out value="${num}"/><%-- num 출력 --%>		
		 <c:set var="num" value="${num-1}"/>	<%-- num=num-1; 의미--%>	
	   </td>
	   <td><%--제목 --%>
	     <div>			
			<a href="BoardDetailAction.bo?num=${b.BOARD_NUM}" style ="text-decoration:none"> <!-- 엥커태그 위치 프론트컨트롤러 이동 -->
			   <c:if test="${b.BOARD_SUBJECT.length()>=20}">
				 <c:out value="${b.BOARD_SUBJECT.substring(0,20)}..." />
			   </c:if>
			   <c:if test="${b.BOARD_SUBJECT.length()<20}">
				 <c:out value="${b.BOARD_SUBJECT}" />
			   </c:if>
			</a>[${b.cnt}]  <!-- cnt는 게시글이 가진 댓글의 갯수 -->
		  </div>
		</td>
		<td><div>${b.BOARD_WRITER}</div></td>
		<td><div>${b.BOARD_DATE}</div></td>	
		<td><div>${b.BOARD_READCOUNT}</div></td>
	   </tr>
	  </c:forEach>
	 </tbody>	
	</table>
		
		<div><button type="button" id="board_write">글 쓰 기</button></div>
		
	<div class="center-block">
		  <ul class="pagination">		
			 <c:if test="${page <= 1 }">
				<li class="page-item">
				  <a class="page-link gray">이전&nbsp;</a>
				</li>
			 </c:if>
			 <c:if test="${page > 1 }">			
				<li class="page-item ">
				   <a href="BoardList.bo?page=${page-1}" 
				      class="page-link">이전&nbsp;</a>
				</li> 
			 </c:if>
					
			<c:forEach var="a" begin="${startpage}" end="${endpage}"> <!-- 시작값, 종료값 -->
				<c:if test="${a == page }">
					<li class="page-item active" >
					   <a class="page-link">${a}</a>
					</li>
				</c:if>
				<c:if test="${a != page }">
				    <li class="page-item">
					   <a href="BoardList.bo?page=${a}" 
					      class="page-link">${a}</a>
				    </li>	
				</c:if>
			</c:forEach>
			
			<c:if test="${page >= maxpage }">
				<li class="page-item">
				   <a class="page-link gray">&nbsp;다음</a> 
				</li>
			</c:if>
			<c:if test="${page < maxpage }">
			  <li class="page-item">
				<a href="BoardList.bo?page=${page+1}" 
				   class="page-link">&nbsp;다음</a>
			  </li>	
			</c:if>
		 </ul>
	</div>
	</form>
 <%-- <c:if test="${listcount > 0 }"> end --%>
	
 <%-- 게시글이 없는 경우--%>
 <c:if test="${listcount == 0 }">
	<h3 style="text-align:center">등록된 글이 없습니다.</h3>
 </c:if>


</div>  <%-- <div class="container"> end --%>
<script>
$('#board_write').click(function(){
location.href = "BoardWrite.bo";
});

/* 버튼을 누르면 액션이 일어나도록 */
</script>

</body>
</html>