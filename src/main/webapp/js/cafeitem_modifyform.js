$(document).ready(function() {
	let check = 0;

	$("form[name=modifyform]").submit(function() {

		if ($.trim($("#ITEM_NAME").val()) == "") {
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

		if ($.trim($("#ITEM_DETAIL").val()) == "") {
			alert('내용을 입력하세요.');
			$("#ITEM_DETAIL").focus();
			return false;
		}

		// 파일첨부를 변경하지 않으면 $('#filevalue').text()의 파일명을
		// 파라미터 'check'라는 이름으로 form에 추가하여 전송
		if (check == 0) {
			const value = $('#filevalue').text();
			const html = "<input type='hidden' value='" + value + "' name='check'>";
			console.log(html);
			$(this).append(html);
		}
	}); // submit end

	function show() {
		// 업로드 된 파일이 존재하는 경우: remove 이미지 보이기
		// 업로드 된 파일이 존재하지 않는 경우: remove 이미지 보이기 않기
		if ($('#filevalue').text() == '') {
			$(".remove").css('display', 'none');
		} else {
			$(".remove").css({
				'display': 'inline-block',
				'position': 'relative',
				'top': '-5px'
			});
		}
	}
	show();

	$("#upfile").change(function() { // 파일 첨부 
		check++;
		const inputfile = $(this).val().split('\\');
		// 배열의 마지막 원소(파일명): 사진 파일명을 id가 filevalue인 곳에 넣기
		$('#filevalue').text(inputfile[inputfile.length - 1]);
		show();
		console.log(check);
	});

	$(".remove").click(function() { // remove 이미지 클릭시
		// 파일 삭제 버튼이 클릭되었을 때, 파일명 표시를 제거하고 파일 input의 값을 초기화합니다.
		$("#filevalue").text(''); // 파일명 표시 초기화
		//    		$("#upfile").val(""); // 파일 input 초기화
		$(this).css('display', 'none');
	})
}); // ready() end
