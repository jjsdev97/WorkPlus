<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <jsp:include page="cafefooter.jsp"/>
    <link rel="stylesheet" href="css/cafeitemview.css" type="text/css">
    <meta charset="UTF-8">
 <!-- <script src="js/view.js"></script> -->      
    <title>item - view 페이지</title>
</head>
<body>
    <input type="hidden" id="loginid" value="${id}" name="loginid">
    <div class="container">
        <table class="table">
            <tr>
                <th colspan="2">item - view 페이지</th>
            </tr>

            <tr>
                <td><div>상품 이름</div></td>
                <td><c:out value="${boarddata.ITEM_NAME}"/></td>        
            </tr>
            <tr>
                <td><div>상품 가격</div></td>
                <td><c:out value="${boarddata.ITEM_PRICE}"/></td>        
            </tr>
            <tr>
                <td><div>상품 메뉴</div></td>
                <td><c:out value="${boarddata.ITEM_MENU}"/></td>        
            </tr>
            <tr>
                <td><div>내용</div></td>
                <td style="padding-right: 0px">
                    <textarea class="form-control" rows="5" readonly>${boarddata.ITEM_DETAIL}</textarea>
                </td>
            </tr>    
                <tr>
                    <td><div>첨부파일</div></td>
                    <c:if test="${!empty boarddata.ITEM_IMG_PATH}">
                        <td>
                            <img src="image/down.png" width="10px">
                            <a href="ItemFileDown.ca?filename=${boarddata.ITEM_IMG_PATH}">
                                파일 다운로드
                            </a>
                        </td>
                    </c:if>
                </tr>
            <c:if test="${empty boarddata.ITEM_IMG_PATH}">
                <td></td>
            </c:if>
            <tr>
                <td colspan="2" class="center">
                    <c:if test="${id eq 'cafe1' }">
                        <a href="ItemModifyView.ca?num=${boarddata.ITEM_UID}">
                            <button class="btn btn-info">수정</button>
                        </a>
                        <%-- href 주소를 #으로 설정 --%>
                        <a href="#">
                            <button class="btn btn-danger" data-toggle="modal"
                                    data-target="#myModal">삭제</button>
                        </a>
                    </c:if>
                    <a href="ItemList.ca">
                        <button class="btn btn-warning">목록</button>
                    </a>
                </td>
            </tr>
        </table>
    </div>
    
    <div class="modal" id="myModal">
    	<div class="modal-dialog">
    		<div class="modal-content">
    			<%-- Modal body --%>
    			<div class="modal-body">
    				<form name="deleteForm" action="ItemDelete.ca" method="post">
    				<%-- http://localhost:8088/Board_Ajax/BoardDetailAction.bo?num=12
    				주소에서 num 파라미터를 넘기는 것을 ${param.num} or ${boarddata.board_num}
    				를 사용해서 catch --%>
    				<input type="hidden" name="num" value="${param.num}"
    					   id="comment_board_num">
 <!--    				<div class="form-group">
    					<label for="pwd">비밀번호</label>
    					<input type="password" class="form-control" 
    						   placeholder="Enter password" name="board_pass"
    						   id="board_pass">
    				</div>
-->     			
					<div class="modal-body">
    				<p>상품 삭제</p>
    				</div>
    				<button type="submit" class="btn btn-primary">삭제</button>
    				<button type="button" class="btn btn-danger" data-dismiss="modal">취소</button>
    				</form>
    			</div>
    		</div>
    	</div>
    </div>
</body>
</html>
