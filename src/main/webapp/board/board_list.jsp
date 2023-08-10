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
	<table class="table table-striped" >
	   <thead>
	     <tr>
	      <th><div>별</div></th>
	      <th><div>순번</div>></th>
	      <th><div>제목</div></th>
	      <td><div>작성자</div></td>
	      <td><div>작성일자</div></td>
	      <td><div>조회수</div></td>
	     </tr>
	   </thead>
	   <tbody>
	   <c:set var="num" value="${listcount-(page-1)*limit}"/>
	   <c:forEach var="b" items="${boardlist}">	
<tr>
	   <td><%--번호 --%>
		 <c:out value="${num}"/><%-- num 출력 --%>		
		 <c:set var="num" value="${num-1}"/>	<%-- num=num-1; 의미--%>	
	   </td>
	   <td><%--제목 --%>
	     <div>			
			<%-- 답변글 제목앞에 여백 처리 부분 
			      board_re_lev,  board_num, 
			      board_subject, board_name, board_date, 
			      board_readcount : property 이름 --%>
		    <c:if test="${b.board_re_lev != 0}">  <%--  답글인 경우 --%>
				<c:forEach var="a" begin="0" end="${b.board_re_lev*2}" step="1">
				&nbsp; 	
				</c:forEach>		
				<img src='image/line.gif'>
			</c:if>  
			 
			<c:if test="${b.board_re_lev == 0}">  <%-- 원문인 경우 --%>
				&nbsp;  
			</c:if> 			
			
			<a href="BoardDetailAction.bo?num=${b.board_num}">
			   <c:if test="${b.board_subject.length()>=20}">
				 <c:out value="${b.board_subject.substring(0,20)}..." />
			   </c:if>
			   <c:if test="${b.board_subject.length()<20}">
				 <c:out value="${b.board_subject}" />
			   </c:if>
			</a>[${b.cnt}]  
		  </div>
		</td>
		<td><div>${b.board_name}</div></td>
		<td><div>${b.board_date}</div></td>	
		<td><div>${b.board_readcount}</div></td>
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
					
			<c:forEach var="a" begin="${startpage}" end="${endpage}">
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
 </c:if><%-- <c:if test="${listcount > 0 }"> end --%>
	
 <%-- 게시글이 없는 경우--%>
 <c:if test="${listcount == 0 }">
	<h3 style="text-align:center">등록된 글이 없습니다.</h3>
 </c:if>

 <button type="button" class="btn btn-info float-right">글 쓰 기</button>
</div>  <%-- <div class="container"> end --%>
</body>
</html>