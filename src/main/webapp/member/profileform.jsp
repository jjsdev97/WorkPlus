<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로필 수정</title>
<link href="/WorkPlus/css/profile.css" rel="stylesheet">
<jsp:include page="profileheader.jsp" />
<style>

</style>
</head>
<body>
<div class="main">
 <h3>프로필 설정</h3><hr>
 <div class="container">
 <form name="profileform" class="profileform">
 
  <div class="profilesetting">
   <div class="profile_top">
		<div class="profilephoto">
	    	<label>
	     	<%-- 프로필 사진 첨부 --%>
	     	<img src="../img/profile.png" alt="파일첨부" id="profile">
	     	<input type="file" id="upfile" name="profile_file">
	   		</label>
	    	<span id="filevalue"></span>
	   	</div>
	</div>
	
   <div class="profile_position">	
	<div class="profile_left">
		<b>아이디</b><br>
		<input type="text" name="id" value="" readonly><br>
	
		<b>이름</b><br>
		<input type="text" name="name" value="" readonly><br>
		
		<b>사원번호</b><br>
		<input type="text" name="empnum" value="" readonly><br>
	</div>
	
	<div class="profile_right">
		<b>새 비밀번호</b><br>
		<input type="password" name="pass" value=""><br>
		
		<b>새 비밀번호 확인</b><br>
		<input type="password" name="passcheck" value="" ><br>
    </div>
  </div>  
  </div>
  
  <div class="clearfix">
  <ul>
  	<button type="submit" class="submitbtn">저장</button>
  	<button type="button" class="cancelbtn">취소</button>
  </ul>
  </div>
  
 </form>
</div>
</div>
</body>
</html>