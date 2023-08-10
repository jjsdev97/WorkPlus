<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../header.jsp" />
<meta charset="utf-8">
<title>메신저 chatList</title>
<link rel="stylesheet" type="text/css" href="/WorkPlus/css/chat.css">
</head>
<body>
	<div class="main">
	<div class="parent" style="display:flex">
		<div class="chatlist" style="flex:0.8">
		   <div class="search">
				<input type="text" placeholder="search..."> 
				<img src="${pageContext.request.contextPath}/img/search.png" 
					width="25px" height="25px" style="vertical-align:middle">
				<img src="${pageContext.request.contextPath}/img/add.png"
					width="25px" height="25px" style="vertical-align:middle">
			</div>
			
			<table class="chatList">
				<tr>
					<td>
					<div>
						<img src="${pageContext.request.contextPath}/img/profile.png" width="40px" height="40px">

					</div>
					</td>
					<td>
					<div>
						<p class="chatRoomName">홍귀동</p>
						<p class="lastMessage">서브웨이 좋아요</p>
					</div>
					</td>
					<td><div>
							<img src="${pageContext.request.contextPath}/img/pin_f.png" width="20px" height="20px">
						</div>
					</td>
				</tr>
			</table>
			
		</div>
		
		<div class="chatroom" style="flex:1">
		<div>
			<img src="${pageContext.request.contextPath}/img/profile.png"
					width="40px" height="40px">
			<span style="vertical-align: top;">홍귀동</span>
		</div>
		<div class="wrap">
        <div class="chat ch1">
            <div class="icon"><i class="fa-solid fa-user"></i></div>
            <div class="textbox">안녕하세요. 반갑습니다.</div>
        </div>
        <div class="chat ch2">
            <div class="icon"><i class="fa-solid fa-user"></i></div>
            <div class="textbox">안녕하세요. 친절한효자손입니다. 그동안 잘 지내셨어요?</div>
        </div>
        <div class="chat ch1">
            <div class="icon"><i class="fa-solid fa-user"></i></div>
            <div class="textbox">넹. 점심으로는 서브웨이 어떠세요?</div>
        </div>
        <div class="chat ch2">
            <div class="icon"><i class="fa-solid fa-user"></i></div>
            <div class="textbox">서브웨이 좋아요</div>
        </div>
    </div>
    
    	<div class="inputWindow">
    		<input type=text>
    		<img src="${pageContext.request.contextPath}/img/chat_clip.png"
    			width="30px" height="30px" style="vertical-align:middle">
    		<img src="${pageContext.request.contextPath}/img/send.png"
    			width="30px" height="30px" style="vertical-align:middle">
    	</div>
		</div>




		<div class="profiles" style="flex:1">
		
		<div>
			<div>
			<img src="${pageContext.request.contextPath}/img/profile.png"
				width="80px" height="80px">
			</div>
			<div>
			<span style="font-size:13px">홍귀동</span>
			</div>
		</div>
		<div class="participants_desc">
             <div class="dept">개발1팀</div>
             <div class="dept">사원</div>
             <div class="dept" style="width:150px;">gildong@work.com</div>
        </div>

		</div>
</div>
</div>
</body>
</html>