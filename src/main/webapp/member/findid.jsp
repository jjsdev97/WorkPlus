<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<link href="../css/find.css" rel="stylesheet">
<style>

</style>
</head>
<body>
<div class="main">
  <div class="container">
  <form name="find_idform" class="find_idform">
  <h3>아이디 찾기</h3>
  	<div class="element">
  	<div class="input_id">
		<b>이름</b><br>
		<input type="text" name="name" value="" id="input_name" placeholder="이름을 입력하세요" required><br>
	</div>
	
	<div class="input_empnum">
		<b>사원번호</b><br>
		<input type="text" name="empnum" value="" id="input_empnum" placeholder="사원번호를 입력하세요" required><br>
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