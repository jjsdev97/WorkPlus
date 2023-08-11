$(document).ready(function() {

	function modalClose() {
		$('.modal-background').css('display', 'none');
		$('.modal-container').css('display', 'none');
	}

	$(".cancelbtn").click(modalClose);


	$(".submitbtn").click(function() {
		modalClose();
	});


})