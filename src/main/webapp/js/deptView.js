$(document).ready(function() {

	function modalOpen() {
		$('.modal-background').css('display', 'block');
		$('.modal-container').css('display', 'block');
		
		$('body').css('overflow', 'hidden');
	}
	function modalClose() {
		$('.modal-background').css('display', 'none');
		$('.modal-container').css('display', 'none');

		$('body').css('overflow', 'auto');
	}

	$('#dlevel').on("change", function() {
		$('#dupperlevel').empty();
		var selected_dlevel = $(this).val();

		//value 값으로 선택
		$.ajax({
			type: "post",
			url: "deptGetList.dt",
			dataType: "json",
			success: function(rdata) {

				$(rdata.deptList).each(function() {
					if (selected_dlevel == (this.d_level + 1)) {
						$('#dupperlevel').append('<option value="' + this.d_num + '">' + this.d_name + '</option>');
					}
				});
			}
		})
	})




	var update_num;

	function getDept() {
		$.ajax({
			type: "post",
			url: "deptGetList.dt",
			dataType: "json",
			success: function(rdata) {

				if (rdata.deptList.length > 0) {

					$('.deptList').empty();
					$('.deptList').prepend('<li><hr class="deptList-bar" id="dept-bar-lev1"></hr></li><li><hr class="deptList-bar" id="dept-bar-lev2"></hr></li><li><hr class="deptList-bar" id="dept-bar-lev3"></hr></li>');
					$(rdata.deptList).each(function() {
						if (this.d_level == 0) {
							$('.deptList').prepend('<li><div class="dept-item">' + this.d_name + '<input type="hidden" value="' + this.d_num + '" ></div></li>');
						} else if (this.d_level == 1) {
							$('#dept-bar-lev1').css('display', 'block');
							$('#dept-bar-lev1').after('<li><div class="dept-item">' + this.d_name + '<button class="deletebtn">삭제</button><button class="update-menu-btn">수정</button><input type="hidden" value="' + this.d_num + '" ></div></li>');
						} else if (this.d_level == 2) {
							$('#dept-bar-lev2').css('display', 'block');
							$('#dept-bar-lev2').after('<li><div class="dept-item">' + this.d_name + '<button class="deletebtn">삭제</button><button class="update-menu-btn">수정</button><input type="hidden" value="' + this.d_num + '" ></div></li>');
						} else if (this.d_level == 3) {
							$('#dept-bar-lev3').css('display', 'block');
							$('#dept-bar-lev3').after('<li><div class="dept-item">' + this.d_name + '<button class="deletebtn">삭제</button><button class="update-menu-btn">수정</button><input type="hidden" value="' + this.d_num + '" ></div></li>');
						}


					})
				}

				// 윗 부분 끝, 조직도 시작


				var $organisation = $('#organisation');

				$organisation.empty();

				$(rdata.deptList).each(function() {
					var $node;

					if (this.d_level === 0) {
						$node = $organisation;
					} else {
						var $parentNode = $organisation.find('#lev-id-' + this.d_upperlevel);
						$node = $parentNode.find('ul');

						if ($node.length === 0) {
							$parentNode.append('<ul></ul>');
							$node = $parentNode.find('ul');
						}
					}

					var li = '<li id="lev-id-' + this.d_num + '">' + this.d_name + '</li>';
					$node.append(li);
				});


				$jqchart("#organisation").orgChart({ container: $jqchart("#chart"), stack: true, depth: 4 });

			}
		});
	}


	getDept();


	$('.modal-btn').click(function() {
		$('.submitbtn').css("display", "inline-block");
		$('.updatebtn').css("display", "none");

		$('#dupperlevel').empty().append('<option value="0">대표이사</option>');

		$('#dlevel option:eq(0)').prop('selected', true);
		$('.modal-container input').val('');
	}) /* 모달창 시작 */



	$('.cancelbtn').click(function() {

		modalClose();
	}) /* 취소 버튼 */




	$('.submitbtn').click(function() {
		const dname = $('#dname').val();
		const dlevel = $('#dlevel').val();
		const dupperlevel = $('#dupperlevel').val();
		const dcolor = $('#dcolor').val();

		if (!dname) {//내용없이 등록 클릭한 경우
			alert("이름을 입력하세요");
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
					modalClose();
					getDept();

				}
			}
		})//ajax


	})// 확인 버튼


	$('.main').on('click', '.update-menu-btn', function() {
		$('#dupperlevel').empty();
		update_num = $(this).next().val();
		var update_dlevel;

		$('.submitbtn').css("display", "none");
		$('.updatebtn').css("display", "inline-block");

		modalOpen();
		
		$.ajax({
			url: 'deptGetData.dt',
			data: {
				num: update_num
			},
			type: 'post',
			success: function(rdata) {
				$('#dname').val(rdata.dname);
				$('#dlevel').val(rdata.dlevel);
				$('#dupperlevel').val(rdata.dupperlevel);
				$('#dcolor').val(rdata.dcolor);

				update_dlevel = rdata.dlevel;
			}


		})

		$.ajax({
			type: "post",
			url: "deptGetList.dt",
			dataType: "json",
			success: function(rdata) {

				$(rdata.deptList).each(function() {
					if (update_dlevel == (this.d_level + 1)) {
						$('#dupperlevel').append('<option value="' + this.d_num + '">' + this.d_name + '</option>');
					}
				});
			}
		})

	}) // 업데이트 버튼

	$('.updatebtn').click(function() {

		const dname = $('#dname').val();
		const dlevel = $('#dlevel').val();
		const dupperlevel = $('#dupperlevel').val();
		const dcolor = $('#dcolor').val();

		if (!dname) {//내용없이 등록 클릭한 경우
			alert("이름을 입력하세요");
			return;
		}

		$.ajax({
			url: 'deptUpdate.dt',
			data: {
				dnum: update_num,
				dname: dname,
				dlevel: dlevel,
				dupperlevel: dupperlevel,
				dcolor: dcolor
			},
			type: 'post',
			success: function() {
				modalClose();
				getDept();
			}
		})//ajax



	})


	$('.main').on('click', '.deletebtn', function() {

		const conf = confirm("정말 삭제하시겠습니까?");

		if (conf) {
			const num = $(this).next().next().val();

			$.ajax({
				url: 'deptDelete.dt',
				data: {
					num: num
				},
				type: 'post',
				success: function() {
					getDept();
				}
			})

		}

	}) // 삭제 버튼








}) //document()

