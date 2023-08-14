<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/cafejoin.css" type="text/css" rel="stylesheet"> <!-- 실행(index.jsp) 기준 -->
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<script>
	$(function() {
		let checkid = false;
		let checkemail = false;
		$("input[name=id]").on('keyup', function() {
//			$('#message').empty();
			
			// \w: [A-z a-z 0-9]
			const pattern = /^\w{5,12}$/;
			const id = $("input:eq(0)").val();
			if (!pattern.test(id)) {
				$("#message").css('color', 'red').html("영문자 숫자 _로 5~12자 가능합니다.")
				checkid = false;
				return;
			}
			
			$.ajax({
				url: "idcheck.cnet",
				data: {"id" : id},
				success: function(resp) {
					if(resp == -1) {
						$("#message").css('color', 'green').html("사용 가능한 아이디 입니다.")
						checkid = true;
					} else {
						$("#message").css('color', 'blue').html("이미 사용중인 아이디 입니다.")
						checkid = false;
					}
				}
			})
		}) // id keyup event end
		
		
		$('form').submit(function() {
			
			if(!checkid) {
				alert('사용가능한 id로 입력하세요');
				$("input[name='id']").val('').focus(); // id값 초기화
				return false;
			}
			
		}) // form submit event end
	})
</script>
<body>
   <form name="joinform" action="joinProcess.cnet" method="post">
   <h1>회원가입</h1>
   <hr>
   <b>아이디</b>
   <input type="text" name="id" placeholder="Enter id" maxLength="12" required>
   <span id="message"></span>
   <b>비밀번호</b>
   <input type="password" name="pass" placeholder="Enter password" required>
   <b>이름</b>
   <input type="text" name="name" maxLength="5" placeholder="Enter name" required>
   <div class="clearfix">
   		<button type="submit" class="submitbtn">회원가입</button>
   		<button type="reset" class="cancelbtn">다시작성</button>
   </div>
   </form>
</body>
</html>
