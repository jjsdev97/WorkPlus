<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../header.jsp" />
<meta charset="utf-8">
<title>메신저 메인</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/chat.css">
</head>
<body>
	<div class="main">
		<div class="left">
			<div class="search">
				<input type="text" placeholder="search..."> 
				<img src="${pageContext.request.contextPath}/img/search.png" 
					width="25px" height="25px" style="vertical-align:middle">
				<img src="${pageContext.request.contextPath}/img/add.png"
					width="25px" height="25px" style="vertical-align:middle">
			</div>


			<table class="m_chat_list">
				<tr>
					<td><div>
							<img src="${pageContext.request.contextPath}/img/profile.png" width="40px" height="40px">
						</div></td>
					<td><div>홍귀동</div></td>
					<td><div>
							<img src="${pageContext.request.contextPath}/img/online.png" width="40px" height="40px">
						</div></td>
					<td><div class="dept">개발2팀</div> </td>
					<td><div>
							<img src="${pageContext.request.contextPath}/img/chat.png" width="20px" height="20px">
						</div>
					</td>
					<td><div>
							<img src="${pageContext.request.contextPath}/img/star_f.png" width="20px" height="20px">
						</div>
					</td>
				</tr>

				<tr>
					<td><div>
							<img src="${pageContext.request.contextPath}/img/profile.png" width="40px" height="40px">
						</div></td>
					<td><div>홍이동</div></td>
					<td><div>
							<img src="${pageContext.request.contextPath}/img/brb.png" width="40px" height="40px">
						</div></td>
					<td><div class="dept">개발1팀</div> </td>
					<td><div>
							<img src="${pageContext.request.contextPath}/img/chat.png" width="20px" height="20px">
						</div>
					</td>
					<td><div>
							<img src="${pageContext.request.contextPath}/img/star_e.png" width="20px" height="20px">
						</div>
					</td>
				</tr>
				
				
			</table>

		</div>


		<div class="right">
		  <div class="myProfile">
            <div>
            <img src="${pageContext.request.contextPath}/img/profile.png" 
            		width="200px" height="200px">
            </div>
            <div>
              <a href='status.jsp' onclick="window.open(this.href,'팝업창','width=800,height=800');return false;">
            	<img src="${pageContext.request.contextPath}/img/online.png"
            		width="30px" height="30px" style="vertical-align: middle;">
              </a>
            </div>
            <div>
               홍길동
             </div>
          </div>
          
          <div class="myProfile_desc">
             <div class="dept">개발1팀</div>
             <div class="dept">사원</div>
             <div class="dept" style="width:150px;">gildong@work.com</div>
          </div>
          
          <div>
          	<img src="${pageContext.request.contextPath}/img/settings.png"
          			width="30px" height="30px">
          </div>

	</div>
</div>
</body>
</html>