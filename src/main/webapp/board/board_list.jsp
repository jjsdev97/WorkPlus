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
	<table class="table table-striped">
	   <thead>
	     <tr>
	      <th><div>별</div></th>
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
        <td>★
       </td>
	   <td><%--번호 --%>
		 <c:out value="${num}"/><%-- num 출력 --%>		
		 <c:set var="num" value="${num-1}"/>	<%-- num=num-1; 의미--%>	
	   </td>
	   <td><%--제목 --%>
	     <div>			
			<a href="BoardDetailAction.bo?num=${b.BOARD_NUM}"> <!-- 엥커태그 위치 프론트컨트롤러 이동 -->
			   <c:if test="${b.BOARD_SUBJECT.length()>=20}">
				 <c:out value="${b.BOARD_SUBJECT.substring(0,20)}..." />
			   </c:if>
			   <c:if test="${b.BOARD_SUBJECT.length()<20}">
				 <c:out value="${b.BOARD_SUBJECT}" />
			   </c:if>
			</a>[${b.cnt}]  <!-- cnt는 게시글이 가진 댓글의 갯수 -->
		  </div>
		</td>
		<td><div>${b.BOARD_NAME}</div></td>
		<td><div>${b.BOARD_DATE}</div></td>	
		<td><div>${b.BOARD_READCOUNT}</div></td>
	   </tr>
	  </c:forEach>
	 </tbody>	
	</table>
		
	<div class="center-block">
		  <ul class="pagination justify-content-center">		
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
 <%-- <c:if test="${listcount > 0 }"> end --%>
	
 <%-- 게시글이 없는 경우--%>
 <c:if test="${listcount == 0 }">
	<h3 style="text-align:center">등록된 글이 없습니다.</h3>
 </c:if>

<button type="button" class="btn btn-info float-right">글 쓰 기</button>
</div>  <%-- <div class="container"> end --%>
</body>
</html>