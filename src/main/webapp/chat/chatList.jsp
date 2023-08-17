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
	<script src="${pageContext.request.contextPath}/js/chatting.js"></script>
	<div class="main">
		<div class="parent" style="display: flex">
			

			<div class="chatroom" style="flex: 0.8">
				<div>
					<img src="${pageContext.request.contextPath}/img/chat.png"
						width="40px" height="40px"> <span
						style="vertical-align: top;">WorkPlus</span> <span>: chat
						for anonymous</span>
				</div>
				<textarea id="messageTextArea" class="message-area" rows="30"cols="72"
							style="margin-top: 10px;"></textarea>
				<div class="inputWindow">
					<form>
						<input id="user" type=text value="${m.m_ID}"style="width: 50px; height: 30px;"> <input
							id="textMessage" type="text" style="width: 70%; height: 30px;margin-top: 20px;">
						<button type="button" id="sendButton">
							<img src="${pageContext.request.contextPath}/img/send.png"
								width="30px" height="30px" style="vertical-align: middle">
						</button>
					</form>
				</div>
			</div>




			<div class="profiles" style="flex: 1">

				<div class="container" style="width: 350px;">
					<div class="header">
					이곳은 WorkPlus 직원들을 위한 <br> 익명의 채팅 공간입니다.
					</div>
					<div>
					쾌적한 채팅 환경을 위해 다음과 같은 금지 사항을 준수해야 함을 알려드립니다.					
					</div>
					<ul class="rules-list">
						<li>&#8226; 타인의 저작권 및 상표권d을 침해하는 행위</li>
						<li>&#8226; 음란한 내용이나 불건전한 영상 공유</li>
						<li>&#8226; 타인을 사칭하는 행위</li>
						<li>&#8226; 바이러스, 악성 코드 또는 불법적이고 위험한 콘텐츠로 연결되는 링크 공유</li>
						<li>&#8226; 인종, 종교, 성별, 문화적 배경 또는 성적 취향을 기반으로 한 집단 또는 소수자에 대한
							차별적이거나 증오적인 메시지 게시</li>
						<li>&#8226; 반복적이고 불쾌하거나 혹은 양면성이 뚜렷한 메시지를 스팸처럼 반복하여 게시하는 행위</li>
					</ul>
					<div class="footer">위의 내용을 준수하지 않을 경우, 제재가 가해질 수 있습니다.</div>
				</div>

			</div>
		</div>
	</div>
</body>
</html>