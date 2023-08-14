$(document).ready(function() {

	getTemplate();

	function modalClose() {
		$('.modal-background').css('display', 'none');
		$('.modal-container').css('display', 'none');

		$('body').css('overflow', 'auto');
	}

	$(".modal-cancelbtn").click(modalClose);


	$(".modal-submitbtn").click(function() {

		$('.approval-line td').empty();
		$(".approval-reference-container #input-ref-item").remove();
		$(".approval-reciever-container #input-ref-item").remove();

		$('.modal-approval-line-item .approval-line-item-detail').each(function(index) {
			var item_deptname = $(this).find('.item-deptname').text();
			var item_pname = $(this).find('.item-pname').text();
			var item_mname = "<input type='text' name='input-approval-item' id='input-approval-item' value='"
				+ $(this).find('.item-mname').text() + "' readOnly>";

			$("#approval-line-first td").eq(index).append(item_deptname);
			$("#approval-line-second td").eq(index).append(item_mname);
			$("#approval-line-third td").eq(index).append(item_pname);

		})




		$('.modal-approval-reference > .approval-line-item-refrec').each(function() {
			var item = "<input type='text' name='input-ref-item' id='input-ref-item' value='"
				+ $(this).find('.item-mname').text() + "' readOnly>";

			$('.approval-reference-container').append(item);
		});

		$('.modal-approval-reciever > .approval-line-item-refrec').each(function() {
			var item = "<input type='text' name='input-rec-item' id='input-ref-item' value='"
				+ $(this).find('.item-mname').text() + "' readOnly>";

			$('.approval-reciever-container').append(item);
		});



		modalClose();

	});





	$('.main').on('click', '.modal-approval-type-btn', function() {

		var approvalType = $(this).attr("id");

		if (approvalType == 'type-1') {
			moveMemberToApproval($(this), ".modal-approval-line-type");
		} else if (approvalType == 'type-2') {
			moveMemberRefRec($(this), ".modal-approval-reference");
		} else if (approvalType == 'type-3') {
			moveMemberRefRec($(this), ".modal-approval-reciever");
		}
	})

	function moveMemberToApproval(button, targetSection) {
		var $parent = button.closest(".modal-member-item");
		var deptName = $parent.closest(".deptname").data("value");
		var pname = $parent.find(".pname").data("value");
		var mname = $parent.find(".mname").data("value");
		var targetSectionDiv = $(targetSection);

		var divDeptName = '<div class="item-deptname">' + deptName + '</div>'
		var divPname = '<div class="item-pname">' + pname + '</div>'
		var divMname = '<div class="item-mname">' + mname + '</div>'


		var memberInfo = $("<div class='approval-line-item-detail'>").append(divDeptName).append(divPname).append(divMname);

		// 차례대로 채워나가기 위한 로직 추가
		var approvalItems = targetSectionDiv.find(".modal-approval-line-item");

		for (var i = 0; i < approvalItems.length; i++) {
			var currentApprovalItem = $(approvalItems[i]);
			if (!currentApprovalItem.hasClass("filled")) {
				currentApprovalItem.html(memberInfo.clone()); // 해당 칸에 추가
				currentApprovalItem.addClass("filled");

				$parent.css("display", "none"); // 원래 위치에서 숨김
				addRestoreButton(currentApprovalItem, $parent); // 복원 버튼 추가
				break; // 채워진 칸이 있으면 반복문 종료
			}
		}
	}

	function moveMemberRefRec(button, targetSection) {
		var $parent = button.closest(".modal-member-item");
		var mname = $parent.find(".mname").data("value");

		var targetSectionDiv = $(targetSection);

		var divMname = '<div class="item-mname">' + mname + '</div>'


		var memberInfo = $("<div class='approval-line-item-refrec'>").append(divMname);

		targetSectionDiv.append(memberInfo); // 해당 칸에 추가

		$parent.css("display", "none"); // 원래 위치에서 숨김
		addRestoreButton_refrec(memberInfo, $parent); // 복원 버튼 추가
	}


	function addRestoreButton(targetItem, originalItem) {
		var restoreButton = $("<button>").text("X").addClass("restore-member-btn");
		targetItem.append(restoreButton);

		restoreButton.click(function() {
			targetItem.removeClass("filled");
			originalItem.css("display", ""); // 원래 위치의 아이템 다시 보이게 함
			targetItem.find(".restore-member-btn").remove(); // 복원 버튼 삭제
			targetItem.html(''); // 내용 비우기
		});
	}

	function addRestoreButton_refrec(targetItem, originalItem) {
		var restoreButton = $("<button>").text("X").addClass("restore-member-btn");
		targetItem.append(restoreButton);

		restoreButton.click(function() {
			targetItem.removeClass("filled");
			originalItem.css("display", ""); // 원래 위치의 아이템 다시 보이게 함
			targetItem.find(".restore-member-btn").remove(); // 복원 버튼 삭제
			targetItem.remove(); // 내용 비우기
		});
	}



	$('#select-template').change(function() {
		getTemplate();

	})


	function getTemplate() {

		var selected_option = $('#select-template').val();

		$.ajax({
			type: "post",
			url: "approvalGetTemplate.apv",
			dataType: "json",
			data: {
				num: selected_option
			},
			success: function(rdata) {
				$('.approval-content').empty();
				$('.approval-content').append(rdata.templateContent);
			}
		})
	}
	
	
	 $("#approvalForm").submit(function(event) {
            event.preventDefault(); 
            
            var divContent = $(".approval-content").html();

            $("#hiddenField").val(divContent);

            $(this).unbind("submit").submit(); 
        });

});