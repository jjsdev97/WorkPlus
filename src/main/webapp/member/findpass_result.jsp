<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기 결과</title>
<link href="css/find.css" rel="stylesheet">
<script src="js/jquery-3.7.0.js"></script>
<style>

</style>
</head>
<body>
<script>
$(function(){
	
	 let checkpass = false;
	 
	$("#new_passcheck").on('keyup',
	function(){
		const pass = $('#new_pass');
		const passcheck = $('#new_passcheck');
		
		const pass_value = $.trim(pass.val());
        const passcheck_value = $.trim(passcheck.val());

        if (pass_value != passcheck_value) {
           $("#pass_message").css('color', 'red')
              .css('font-size', '11px')
              .html('비밀번호가 일치하지 않습니다.');
           checkpass = false;
        } else {
           $("#pass_message").css('color', 'black')
           	  .css('font-size', '11px')
              .html('비밀번호가 일치합니다.');
           checkpass = true;
        }
     }
	);
	
	$('form').submit(function(){
		
		
		const pass = $('#new_pass');
		 if ($.trim(pass.val()) == "") {
	         alert("비밀번호를 입력 하세요");
	         pass.focus();
	         return false;
	      }

	      if (!checkpass) { //비밀번호 일치 확인
	         alert("비밀번호를 확인해주세요");
	         $("#new_passcheck").val('').focus();
	         return false;
	      }
	      
			if(checkpass){
				alert("새로운 비밀번호가 설정되었습니다.");
			}
		
	});
	
	
	
}); //function end
	

</script>
<div class="main">
  <div class="container">
  <form name="find_passform" class="find_passform" action="passreset.et" method="post">
  <h3>비밀번호 찾기 결과</h3>
  	<div class="element_pass">
  	<div class="newpass">
  		<h4>비밀번호를 변경해주세요.</h4><br>
  		새 비밀번호<br>
		<input type="password" name="pass" value="" id="new_pass" placeholder="새 비밀번호를 입력해주세요" required><br><br>
		새 비밀번호 확인<br>
		<input type="password" name="passcheck" value="" id="new_passcheck" placeholder="새 비밀번호를 입력해주세요" required>
		<div id="pass_message"></div>
	</div>
   </div>
	
 	<div class="clearfix_p">
	  	<button type="submit" class="submitbtn">확인</button>
	  	<button type="reset" class="cancelbtn">취소</button>
   </div>
  </form>
  </div>
</div> <%-- main end --%>
</body>
</html>