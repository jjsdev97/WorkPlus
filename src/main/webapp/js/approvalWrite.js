$(document).ready(function() {

	function modalClose() {
		$('.modal-background').css('display', 'none');
		$('.modal-container').css('display', 'none');
	}

	$(".modal-cancelbtn").click(modalClose);


	$(".modal-submitbtn").click(function() {
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


		var memberInfo = $("<div>").append(deptName).append(pname).append(mname);

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


		var memberInfo = $("<div>").append(mname);

		targetSectionDiv.append(memberInfo); // 해당 칸에 추가

		$parent.css("display", "none"); // 원래 위치에서 숨김
		addRestoreButton(memberInfo, $parent); // 복원 버튼 추가
	}


	function addRestoreButton(targetItem, originalItem) {
		var restoreButton = $("<button>").text("X").addClass("restore-member-btn");
		targetItem.append(restoreButton);

		restoreButton.click(function() {
			targetItem.removeClass("filled");
			originalItem.css("display", ""); // 원래 위치의 아이템 다시 보이게 함
			targetItem.find(".restore-member-btn").remove(); // 복원 버튼 삭제
			targetItem.html(""); // 내용 비우기
		});
	}



})