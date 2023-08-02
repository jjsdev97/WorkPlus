<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="header.jsp" />
<meta charset="utf-8">
<title>메신저 메인</title>
<style>
div.left {
	width: 30%;
	float: left;
	box-sizing: border-box;
	height: 100%
}

div.right {
	width: 70%;
	float: right;
	box-sizing: border-box;
	height: 100%;
	border-left: 1px solid black;
}

div.search {
	border-bottom: 1px solid black;
}


table.m_chat_list {
	width: 100%;
	border-bottom: 1px solid black;
}

div.dept{
	width:70px;
	height: 20px;
	border-radius: 2em;
	background: #A0C1B9;
	color: white;
	font-size: 13px;
	text-align: center;
}

tr:nth-child(1){
	width:
}

</style>
</head>
<body>
	<div class="main">
		<div class="left">
			<div class="search">
				<input type="text" placeholder="search..."> <img
					src="img/search.png" width="25px" height="25px"> <img
					src="img/add.png" width="25px" height="25px">
			</div>


			<table class="m_chat_list">
				<tr>
					<td><div>
							<img src="img/profile.png" width="40px" height="40px">
						</div></td>
					<td><div>홍귀동</div></td>
					<td><div>
							<img src="img/online.png" width="40px" height="40px">
						</div></td>
					<td><div class="dept">개발2팀</div> </td>
					<td><div>
							<img src="img/chat.png" width="20px" height="20px">
						</div>
					</td>
					<td><div>
							<img src="img/star_f.png" width="20px" height="20px">
						</div>
					</td>
				</tr>
			</table>

		</div>


		<div class="right">
			<img src="img/profile.png" width="200px" height="200px">
		</div>

	</div>
</body>
</html>