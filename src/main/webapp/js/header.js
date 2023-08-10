$(document).ready(function() {

	$('#btn_admin').click(function() {
		$('.sidebar-admin').css("visibility", "visible");
		$('.sidebar-user').css("visibility", "hidden");
	})

	$('#btn_user').click(function() {
		$('.sidebar-user').css("visibility", "visible");
		$('.sidebar-admin').css("visibility", "hidden");
	}) // 관리자, 일반페이지 전환



	$('.sidebar-item').on('click', '.sidebar-anchor', function() {
		$('.sidebar-anchor+.second-menu').css('transition','all ease 0.5s');
		$('.sidebar-anchor-collapse').removeClass('sidebar-anchor-collapse');
		$('.second-menu').css('height', '0');
		$(this).addClass('sidebar-anchor-collapse');

		$('.sidebar-anchor-collapse + .second-menu').css('height', function() {
			var length = $(this).children().length;

			return 27.6 * length + "px";
		})
	}) // 화살표 접힌 상태에서 클릭





	$('.sidebar-item').on('click', '.sidebar-anchor-collapse', function() {
		$(this).removeClass('sidebar-anchor-collapse');

		$(this).next().css('height', 0);

	}) // 화살표 펴진 상태에서 클릭








	/* ----------------------모달 시작---------------------- */

	$('.modal-btn').click(function() {
		$('.modal-background').css('display', 'block');
		$('.modal-container').css('display', 'block');

		$('body').css('overflow', 'hidden');
	})



	/* ----------------------모달 종료---------------------- */


	$('.modal-background').click(function() {
		$('.modal-background').css('display', 'none');
		$('.modal-container').css('display', 'none');

		$('body').css('overflow', 'auto');
	})





});