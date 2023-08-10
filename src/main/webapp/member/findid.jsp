<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<link href="css/find.css" rel="stylesheet">
<script src="/WorkPlus/js/jquery-3.7.0.js"></script>
</head>
<body>
<script>
 $(function(){
	 
	 $('.cancelbtn').click(function(){
		 location.href='login.et';
	 });
	 
	 
	let checkname = false;
	let checkempnum = false;
	 
	 $('form').submit(function(){
		 
		 const name = $('#name');
		 const empnum = $('#empnum');
		 const pattern = /^[0-9]{6}$/;
		 
		 //사원번호 유효성 검사
		if(!pattern.test(empnum.val())){
			alert("올바른 사원번호를 입력해주세요.(6자리 숫자)");
			empnum.focus();
			checkempnum = false;
			return false;
		}
		
	 }); //submit end
	 
 }); //function end

</script>
<div class="main">
  <div class="container">
  <form name="find_idform" class="find_idform" action="findidprocess.et" method="post">
  <h3>아이디 찾기</h3>
  	<div class="element">
  	<div class="input_name">
		<b>이름</b><br>
		<input type="text" name="name" id="name" class="input_name" placeholder="이름을 입력하세요" required><br>
	</div>
	
	<div class="input_empnum">
		<div><b>사원번호</b></div>
		<input type="text" name="empnum" id="empnum" class="input_empnum" placeholder="사원번호를 입력하세요" required><br>
	</div>
   </div>
	
 	<div class="clearfix">
	  	<button type="submit" class="submitbtn">다음</button>
	  	<button type="button" class="cancelbtn">취소</button>
   </div>
  </form>
  </div>
</div> <%-- main end --%>
</body>
</html>