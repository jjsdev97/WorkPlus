<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 관리</title>
<link href="/WorkPlus/css/M-memberlist.css" rel="stylesheet">
<jsp:include page="/header.jsp" />
<script src="js/m_memberlist.js"></script>
</head>
<body>
<div class="main">
	<div class="modal-container"> <!-- 수정하기 모달 -->
		<b>이름</b><input type="text" name="id" value="${m.m_NAME}" ><br>
		<b>이메일</b><input type="text" name="id" value="${m.VERIFY_EMAIL}" ><br>		
		<b>부서명</b><input type="text" name="id" value="" ><br>		
		<b>직책</b><input type="text" name="id" value="" ><br>		
		<b>사원번호</b><input type="text" name="id" value="${m.e_NUM}"><br>		
	</div>
 <h3>사용자 관리</h3><hr>
	
 <div class="container"><%-- tab, 검색 container--%>
   <ul class="tabs">
   	 <li class="tab-link current" id="wait" data-tab="tab-1">가입대기[${listcount.wait }]</li>
   	 <li class="tab-link" id="stop" data-tab="tab-2">이용중지[${listcount.stop}]</li>
   	 <li class="tab-link" id="complete" data-tab="tab-3">승인완료[${listcount.complete }]</li>
   </ul>
 </div>
   
   <form action="memberList.et" method="post" name="memberlist" class="search_memberlist">
		<input type="hidden" id="searchcheck" name="page" value="${page}">	
		<input type="hidden" id="tab" name="tab" value="${tab}">
			<div class="input-group">
				<select id="viewcount" name="search_field">
					<option value="0" selected>이름</option>
					<option value="1">사원번호</option>
					<option value="2">부서</option>
				</select> 
				   <input name="search_word" type="text" name="search_word" class="form-control"
					         value="${search_word}">
				   <button class="search_btn" type="submit">검색</button>
			</div>
	</form>
   
   <%-- 표시 내용 --%>
   <div class="content">
   <select name="orderby" id="order">
	   	<option value="name">이름 순</option>
	   	<option value="empnum">사원번호 순</option>
	   	<option value="dept">부서 순</option>
   </select><br>
  <%--  <c:if test="${listcount > 0 }"> --%>
   <div id="tab-1" class="tab-content current"> <%-- 가입 대기 --%>
   	<table class="table table-striped" >
		 <thead>
		   <tr>
		   		<th>이름</th>
		   		<th>사원번호</th>
		   		<th>부서</th>
		   		<th>직책</th>
		   		<th>이메일</th>
		   		<th>가입 요청일</th>
		   		<th>설정</th>
		   </tr>
		 </thead>
		 <tbody>
		  <c:forEach var="m" items="${totallist1}">
		  <input type="hidden" value="${m.m_ID}" id="m_id">
		   <tr>
		   	   <td>${m.m_NAME}</td>
		   	   <td>${m.e_NUM}</td>
		   	   <td><%--부서 선택--%>
		   	   		<select name="dnum" id="dept">
					   	<c:forEach var="d" items="${deptlist}">
					   		<option value="${d.d_num}">${d.d_name}</option>
					   	</c:forEach>
		   	   		</select>
		   	   </td> 
		   	   <td>
		   	   		<select name="pnum" id="pnum">
					   	<c:forEach var="p" items="${position}">
					   		<option value="${p.p_NUM}">${p.m_JOB}</option>
					   	</c:forEach>
		   	   		</select>
		   	   
		   	   </td>
		   	   <td>${m.VERIFY_EMAIL}</td>
		   	   <td>${m.m_HIREDATE}</td>
		   	   <td>
			   	   <span id="admit" style="color: blue;">[가입승인]</span></a>&nbsp;
			   	   <a href="memberDelete.et?id=${m.m_ID}&tab=1"><span id="reject" style="color: red;">[가입거절]</span></a>
		   	   </td>
		   </tr>
		  </c:forEach>
		 </tbody>
	 </table>
   
   </div>
   <div id="tab-2" class="tab-content"> 		<%-- 이용 중지 --%>
     <table class="table table-striped">
		 <thead>
		   <tr>
		   		<th>이름</th>
		   		<th>사원번호</th>
		   		<th>부서</th>
		   		<th>직책</th>
		   		<th>이메일</th>
		   		<th>가입 요청일</th>
		   		<th>설정</th>
		   </tr>
		 </thead>
		 <tbody>
		  <c:forEach var="m" items="${totallist2}">
		   <tr>
		   	   <td>${m.m_NAME}</td>
		   	   <td>${m.e_NUM}</td>
		   	   <td>
		   	    	<select name="dept" id="dept">
					   	<c:forEach var="d" items="${deptlist}">
					   		<option value="${d.d_num}">${d.d_name}</option>
					   	</c:forEach>
		   	   		</select>
		   	   </td>
		   	   <td>${m.p_NUM}</td>
		   	   <td>${m.VERIFY_EMAIL}</td>
		   	   <td>${m.m_HIREDATE}</td>
		   	   <td>
			   	   <a href="memberClearblock.et?id=${m.m_ID}&tab=2"><span id="clear"  style="color: blue;">[중지해제]</span></a>&nbsp; 
			   	   <a href="memberDelete.et?id=${m.m_ID}&tab=2"><span class="delete" style="color: red;">[계정삭제]</span></a>
		   	   </td>
		   </tr>
		  </c:forEach>
		 </tbody>
	 </table>
   </div>
   <div id="tab-3" class="tab-content">			<%-- 승인 완료 --%>
     <table class="table table-striped">
		 <thead>
		   <tr>
		   		<th>이름</th>
		   		<th>사원번호</th>
		   		<th>부서</th>
		   		<th>직책</th>
		   		<th>이메일</th>
		   		<th>가입 요청일</th>
		   		<th>설정</th>
		   </tr>
		 </thead>
		 <tbody>
		  <c:forEach var="m" items="${totallist3}">
		   <tr>
		   	   <td>${m.m_NAME}</td>
		   	   <td>${m.e_NUM}</td>
		   	   <td>
				${m.d_NAME}	
		   	   </td>
		   	   <td>${m.m_JOB}</td>
		   	   <td>${m.VERIFY_EMAIL}</td>
		   	   <td>${m.m_HIREDATE}</td>
		   	   <td>
			   	   <span id="update"  style="color: blue;">[수정]</span>&nbsp;
			   	   <a href="memberDelete.et?id=${m.m_ID}&tab=3"><span class="delete" style="color: red;">[계정삭제]</span></a>
		   	   </td>
		   </tr>
		   </c:forEach>
		 </tbody>
	 </table>
   </div>
 <%-- </c:if> --%>
 </div>
 </div><%-- container end --%>
</div><%-- main end --%>
<script>
$('#update').click(function() {
	$('.modal-background').css('display', 'block');
	$('.modal-container').css('display', 'block');
	
	$('body').css('overflow', 'hidden');
})

</script>
</body>
</html>