

function getDept() {
	$.ajax({
		type: "post",
		url: "deptGetList.dt",
		dataType: "json",
		success: function(rdata) {

			let output = "";
			console.log(rdata.deptList.length);
			if (rdata.deptList.length > 0) {

				$(rdata.deptList).each(function() {
					output += '<li><div>' + this.d_name + '<button>수정</button><button>삭제</button>' + '</div></li>';
					
				})
			}

			$('.deptList').html(output);


		}
	});
}




$(document).ready(function() {
	getDept();
	
	$('.cancelbtn').click(function() {
	$('.modal-background').css('display', 'none');
	$('.modal-container').css('display', 'none');
	 }) /* 취소 버튼 */



$('.submitbtn').click(function() {
	console.log("모달클릭");
	const dname = $('#dname').val();
	const dlevel = $('#dlevel').val();
	const dupperlevel = $('#dupperlevel').val();
	const dcolor = $('#dcolor').val();
	
	console.log(dname);
	if (!dname && !dlevel && !dupperlevel && !dcolor) {//내용없이 등록 클릭한 경우
		alert("댓글을 입력하세요");
		return;
	}

	$.ajax({
		url: 'deptAdd.dt',  //원문 등록
		data: {
			dname: dname,
			dlevel: dlevel,
			dupperlevel: dupperlevel,
			dcolor: dcolor
		},
		type: 'post',
		success: function(rdata) {
			if (rdata == 1) {
				$('.modal-background').css('display', 'none');
				$('.modal-container').css('display', 'none');
				getDept();
				
			}
		}
	})//ajax

	$('.comment-write-area-text').val('');//textarea 초기화
	$('.comment-write-area-count').text('0/200'); //입력한 글 카운트 초기화

})// 확인 버튼

})

