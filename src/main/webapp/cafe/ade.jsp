<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ade</title>
<link href="css/cafestyle.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<script>
		$(document).on('click', '.item-link', function(e) {
	    	e.preventDefault(); // #으로 이동시 페이지 최상단 가는 것 방지
	    	var itemUId = $(this).data('id');
            $.ajax({
            	url: 'ade.cafe',
                method: 'POST',
//              contentType : "application/json; charset:UTF-8",
                contentType: 'application/x-www-form-urlencoded; charset=euc-kr',
                data: { selectedItemUId: itemUId }, // name: value
                dataType: 'json', 
                success: function(cartItems) {
                	$('.cart-table tbody').empty(); // 기존 장바구니 내용 삭제
                    let total = 0;
                    cartItems.forEach(function(item) {
                        total += item.ITEM_PRICE;
                        $('.cart-table tbody').append('<tr><td>' + item.ITEM_NAME + '</td><td>' + item.ITEM_PRICE + '</td><td><a href="#" class="delete-item" data-id="' + item.ITEM_UID + '">삭제</a></td></tr>');
                    });
                    $('p#totalPrice').text('총 가격: ' + total); // 총 가격 업데이트
                 	// 반짝이는 효과 추가
                    $('p#totalPrice').addClass('flash');
                    setTimeout(function() {
                        $('p#totalPrice').removeClass('flash');
                    }, 1000); // 0.5초 후에 애니메이션 종료
                }
            });
        });
		$(document).on('click', '.delete-item', function(e) {
		    e.preventDefault();
		    var itemUId = $(this).data('id');  // 여기를 수정

		    $.ajax({
		        url: 'ade.cafe',
		        method: 'POST',
		        contentType: 'application/x-www-form-urlencoded; charset=euc-kr',
		        data: { deleteItemUId: itemUId },
		        dataType: 'json',  // 서버에서 새로운 장바구니 목록을 반환하도록 변경
		        success: function(cartItems) {
		            $('.cart-table tbody').empty();
		            let total = 0;
		            cartItems.forEach(function(item) {
		                total += item.ITEM_PRICE;
		                $('.cart-table tbody').append('<tr><td>' + item.ITEM_NAME + '</td><td>' + item.ITEM_PRICE + '</td><td><a href="#" class="delete-item" data-id="' + item.ITEM_UID + '">삭제</a></td></tr>');
		            });
		            $('p#totalPrice').text('총 가격: ' + total); // 여기를 수정
		        },
		        error: function() {
		            alert('아이템 삭제 실패');
		        }
		    });
		});

    </script>
    <jsp:include page="../header.jsp" />

    <div class="cafe-main-content">
    
    <div class="btn_item_menu">
    <a href="coffee.cafe" class="btn_cafe_menu" id="btn_coffee">Coffee</a>
	<a href="tea.cafe" class="btn_cafe_menu" id="btn_tea">Tea</a>
	<a href="ade.cafe" class="btn_cafe_menu" id="btn_ade">Ade</a>
	<a href="side.cafe" class="btn_cafe_menu" id="btn_side">Side</a>
	</div>
	
	<h3>Ade</h3>
    <table>
        <tr>
            <th>메뉴</th>
            <th>가격</th>
        </tr>
        <c:forEach items="${adeItemList}" var="item">
            <tr class="item" data-id="${item.ITEM_UID}"> <!-- data-id 속성과 .item 클래스를 추가 -->
            	<td><a href="#" class="item-link" data-id="${item.ITEM_UID}">${item.ITEM_NAME}</a></td>
            	<td><a href="#" class="item-link" data-id="${item.ITEM_UID}">${item.ITEM_PRICE}</a></td>
            </tr>
        </c:forEach>
    </table>

    <h3>장바구니</h3>
    <table class="cart-table">
    <thead> <!-- thead로 감싸기 for protecting -->
        <tr>
            <th>상품명</th>
            <th>가격</th>
            <th>삭제</th>
        </tr>
    </thead>
    <tbody>
    <c:forEach items="${cartItemList}" var="cartItem">
        <tr>
            <td>${cartItem.ITEM_NAME}</td>
            <td>${cartItem.ITEM_PRICE}</td>
            <td><a href="#" class="delete-item" data-id="${cartItem.ITEM_UID}">삭제</a></td>
        </tr>
    </c:forEach>
    </tbody>
	</table>
    <p id="totalPrice">총 가격: ${totalPrice}</p>
	</div>
	<a href="payment.cafe" class="btn_cafe_menu" id="btn_payment">결제하기</a>
	<script>
	$('#btn_payment').click(function(e) {
	    e.preventDefault();
	    // 팝업 창 열기
	    window.open('payment.cafe', 'popup', 'width=600,height=600,left=580,top=200');
	    window.location.href = 'main.cafe';
	});
	</script>
</body>
</html>