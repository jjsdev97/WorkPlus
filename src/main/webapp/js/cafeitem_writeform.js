$(document).ready(function() {
	
	$("#upfile").change(function() {
		console.log($(this).val())
		const inputfile = $(this).val().split('\\');
		// 배열의 마지막 원소: 사진 파일명을 id가 filevalue인 곳에 넣기
		$('#filevalue').text(inputfile[inputfile.length - 1]); 
	});
	
	$("form[name=boardform]").submit(function() {
		if($.trim($("#ITEM_NAME").val()) == "") {
			alert('상품 이름을 입력하세요.');
			$("#ITEM_NAME").focus();
			return false;
		}
		
		if ($.trim($("#ITEM_PRICE").val()) == "") {
			alert('상품 가격을 입력하세요.');
			$("#ITEM_PRICE").focus();
			return false;
		} else if (isNaN($.trim($("#ITEM_PRICE").val()))) {
			alert('상품 가격은 숫자만 입력 가능합니다.');
			$("#ITEM_PRICE").focus();
			return false;
		}
		
		if ($.trim($("#ITEM_MENU").val()) == "") {
			alert('상품 메뉴를 입력하세요.');
			$("#ITEM_MENU").focus();
			return false;
		} else {
			// 허용된 값들의 배열
			const allowedValues = ['coffee', 'tea', 'ade', 'side'];
			// 현재 입력된 값
			const currentValue = $.trim($("#ITEM_MENU").val());
			// 입력된 값이 허용된 값에 포함되지 않는 경우
			if (allowedValues.indexOf(currentValue) === -1) {
				alert('유효한 상품 메뉴를 입력하세요. (coffee, tea, ade, side 중 하나)');
				$("#ITEM_MENU").focus();
				return false;
			}
		}
		
		if($.trim($("#ITEM_DETAIL").val()) == "") {
			alert('내용을 입력하세요.');
			$("#ITEM_DETAIL").focus();
			return false;
		}
	}); // submit end
})
