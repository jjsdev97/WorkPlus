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
<script src="${pageContext.request.contextPath}/js/chat_main.js"></script>
	<div class="main">
		<div class="left">
			<div class="search">
				<form action="Chatmain.chat" method="post">
					<input name = "search_word" type="text" placeholder="search..." value="${search_word}">
					<button type="submit">
					<img id="Qbtn" src="${pageContext.request.contextPath}/img/search.png" 
						width="25px" height="25px" style="vertical-align:middle">
					</button>
				</form>
				
				<img id="addbtn" src="${pageContext.request.contextPath}/img/add.png"
					width="25px" height="25px" style="vertical-align:middle">
			</div>
			<!-- 친구 목록 -->
			<table class="chatMemberList">
			   <c:forEach var="m" items="${memberlist}">
				<tr>
					<td><div>
							<a href="#layer3" class="friendProfile_layer">
							<img src="${pageContext.request.contextPath}/img/profile.png"
								width="40px" height="40px">
							<!-- m.profilefile 이미지 경로 갖고 오기,,, -->
							</a>
						</div></td>
					<td><div class="name">${m.m_NAME}</div></td>
					<td><div>
							<c:choose>
								<c:when test="${m.CHAT_STATUS == 'online' }">
								<img class="status_img" src="${pageContext.request.contextPath}/img/online.png" width="40px" height="40px">
								</c:when>
								<c:when test="${m.CHAT_STATUS == 'offline' }">
								<img class="status_img" src="${pageContext.request.contextPath}/img/offline.png" width="40px" height="40px">
								</c:when>
								<c:when test="${m.CHAT_STATUS == 'brb' }">
								<img class="status_img" src="${pageContext.request.contextPath}/img/brb.png" width="40px" height="40px">
								</c:when>
							</c:choose>
						</div></td>
					<td><div class="f_job" style="display:none">${m.m_JOB}</div> </td>
					<td><div class="f_id" style="display:none">${m.m_ID}</div> </td>
					<td><div class="dept">${m.d_NAME }</div> </td>
					<td><div>
							<a href="#layer4" class="solChatStart_layer">
							<img src="${pageContext.request.contextPath}/img/chat.png" width="20px" height="20px">
							</a>
							<!-- solChatStart_layer -->
						<div id="layer4" class="pop-layer" style="margin-top:10px">
							<div class="pop-container">
								<div class="pop-conts">
								<div id="f_id">${m.m_ID}</div>
								<div class="btn-r">
           					     	<a class="moveHref" href="Chatlist.chat?f_id=${f_id}"  style="background-color: #A0C1B9;">이동</a>
           						</div>
								<div class="btn-r">
									<a href="#" class="btn-layerClose">닫기</a>
								</div>
								</div>
							</div>
						</div>
					</div></td>
					<td><div>
						<a class="star" href="ChatFBookMark.chat?f_id=${m.m_ID}">
							<img  src="${pageContext.request.contextPath}/img/star_f.png"
								 width="20px" height="20px">
						</a>
						</div>
					</td>
				</tr>
			   </c:forEach>
			</table>
			<!-- 상대 프로필 확인 layer -->
			<div id="layer3" class="pop-layer" style="margin-top:10px">
					<div class="pop-container">
						<div class="pop-conts">
						<div>
						<div>
							<img src="${pageContext.request.contextPath}/img/profile.png" width="120px" width="120px">
						</div>
						<div>
						<img id="popup_img" src="${pageContext.request.contextPath}/img/online.png" 
							width="35px" height="35px" style="vertical-align: middle;"> 
							<span id="popup_name"></span>
						</div>
						<div class="frinedProfileDesc">
						 	<div class="dept" id="popup_dname" ></div>
           				  	<div class="dept"   id="popup_job"></div>
            				<div class="dept" style="width:150px;" id="popup_id" ></div>
						</div>
						</div>
						<div class="btn-r">
							<a href="#" class="btn-layerClose">닫기</a>
						</div>
						</div>
					</div>
			</div>
			
			
		
		</div>
		
		<div class="right">
		<!-- 내 프로필 -->
		  <div class="myProfile">
            <div>
            <img src="${pageContext.request.contextPath}/img/profile.png" 
            		width="200px" height="200px">
            </div>
            
<!--// status -->
            <div class="layerP">
              <a href="#layer1" class="status_layer" >
            	<c:choose>
					<c:when test="${m.CHAT_STATUS == 'online' }">
					<img src="${pageContext.request.contextPath}/img/online.png" width="40px" height="40px">
					</c:when>
					<c:when test="${m.CHAT_STATUS == 'offline' }">
					<img src="${pageContext.request.contextPath}/img/offline.png" width="40px" height="40px">
					</c:when>
					<c:when test="${m.CHAT_STATUS == 'brb' }">
					<img src="${pageContext.request.contextPath}/img/brb.png" width="40px" height="40px">
					</c:when>
				</c:choose>
              </a>
<div id="layer1" class="pop-layer" style="margin-top: 10px;">
    <div class="pop-container">
        <div class="pop-conts">
            <!--content //-->
            <table class="status-table">
            	<tr>
            		<td>
            		<img src="${pageContext.request.contextPath}/img/online.png"
            	width="35px" height="35px"></td>
            		<td><a href="ChatStatusUpdate.chat?status=online" id="online.png"  class="statusOption">온라인</a><td>
            	</tr>
            	<tr>
            		<td><img src="${pageContext.request.contextPath}/img/offline.png"
            	width="35px" height="35px"></td>
            		<td><a href="ChatStatusUpdate.chat?status=offline" id="offline.png" class="statusOption">오프라인</a><td>
            	</tr>
            	<tr>
            		<td><img src="${pageContext.request.contextPath}/img/brb.png"
            	width="35px" height="35px"></td>
            		<td><a href="ChatStatusUpdate.chat?status=brb" id="brb.png" class="statusOption">자리비움</a><td>
            	</tr>
            </table>
            <div class="btn-r">
                <a href="#" class="btn-layerClose" style="margin-left: 30px;">취소</a>
            </div>
            <!--// content-->
        </div>
    </div>
</div>
            <!--// status -->
           </div>
           
            <div>
               ${m.m_NAME}
             </div>
          </div>
          
          <div class="myProfile_desc">
             <div class="dept">${m.d_NAME}</div>
             <div class="dept">${m.m_JOB }</div>
             <div class="dept" style="width:150px;">${m.m_ID}@work.com</div>
          </div>
          
          <div>
          <a href='#layer2' class="font_setting_layer">
          	<img src="${pageContext.request.contextPath}/img/settings.png"
          			width="30px" height="30px">
          			
          </a>
          
<div id="layer2" class="pop-layer" style="margin-top: 10px;">
    <div class="pop-container">
        <div class="pop-conts">
            <!--content //-->
            폰트
            <select name="font" size="1">
            <option value="돋움">돋움</option>
            <option value="굴림">굴림</option>
            <option value="고딕">고딕</option>
            </select>
			
            <br>크기
            <select name="font-size" size="1">
            <option value="8px">6pt</option>
            <option value="10px">8pt</option>
            <option value="13px">10pt</option>
            <option value="16px" selected>12pt</option>
            <option value="19px">14pt</option>
            <option value="22px">16pt</option>
            <option value="24px">18pt</option>
            <option value="26px">20pt</option>
            <option value="29px">22pt</option>
            </select>
            <br>
         	
         	<div class="btn-r">
         		<input id="chatsubmitbtn" type="submit" value="저장">
         	</div>
            <div class="btn-r">
                <a href="#" class="btn-layerClose" >취소</a>
            </div>
            <!--// content-->
        </div>
    </div>
</div>
          </div>

	</div>
</div>
</body>
</html>