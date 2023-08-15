<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로필 수정</title>
<link href="/WorkPlus/css/profile.css" rel="stylesheet">
<jsp:include page="../header.jsp" />
<style>

</style>
</head>
<body>
<script>
$(function(){
	$('input[type=file]').change(function(event){
		const inputfile = $(this).val().split('\\');
		const filename=inputfile[inputfile.length - 1]; //inputfile.length - 1 = 2

		const pattern = /(gif|jpg|jpeg|png)$/i; //i(ignore case)는 대소문자 무시를 의미
		if(pattern.test(filename)){
			$('#upfile').text(filename);
			
			const reader = new FileReader();	//파일을 읽기 위한 객체 생성
			
		  reader.readAsDataURL(event.target.files[0]);
			 
		  reader.onload = function(){	//읽기에 성공했을 때 실행되는 이벤트 핸들러
			$('#showImage + img').attr('src', this.result);  
		  };
		} else {
			alert('이미지 파일(gif,jpg,jpeg,png)이 아닌 경우는 무시됩니다.');
			$(this).val('')
		}
	})//change()
	
	$('.cancelbtn').click(function(){
		location.href = "main.com";
	});
	
	$('.submitbtn').click(function(){
		const answer = confirm('수정된 내용을 저장하시겠습니까?');
		if(!answer) {	//취소를 클릭한 경우
			 event.preventDefault();	//이동하지 않습니다.
		 }
	});
	
	
});

</script>
<div class="main">
 <h3>프로필 설정</h3><hr> 
 <div class="container">
 <%-- file 업로드 action 속성은 파일 업로드 처리할 jsp 파일로 설정 --%>
 <form name="profileform" action="profileUpdateProcess.et" class="profileform" method="post" enctype="multipart/form-data">
  
  <div class="profilesetting">
   <div class="profile_top">
		<div class="profilephoto">
	     	<%-- 프로필 사진 첨부 --%>
	    	<label>
	     	<span id="showImage">
	     	   <c:if test='${empty m.m_PROFILEFILE}'>
	     	   	  <c:set var='src' value='img/profile.png' />
	     	   </c:if>
	     	   <c:if test = '${!empty m.m_PROFILEFILE}'>
	     	   	  <c:set var='src' value='${"profileupload/"}${m.m_PROFILEFILE}' />
	     	   	  <input type="hidden" name="check" value="${m.m_PROFILEFILE}">
	     	   </c:if>
	     	</span>
	     	<img src="${src}" width="20px" alt="profile">
	     	<input type="file" id="upfile" name="profilefile" accept="image/*">
	   		</label>
	    	<span id="filevalue"></span>
	   	</div>
	</div>
	
   <div class="profile_position">	
	<div class="profile_left">
		<b>아이디</b><br>
		<input type="text" name="id" value="${m.m_ID}" readonly><br>
	
		<b>이름</b><br>
		<input type="text" name="name" value="${m.m_NAME}" readonly><br>
		
		<b>사원번호</b><br>
		<input type="text" name="empnum" value="${m.e_NUM}" readonly><br>
	</div>
	
	<div class="profile_right">
		<b>새 비밀번호</b><br>
		<input type="password" name="pass" ><br>
		
		<b>새 비밀번호 확인</b><br>
		<input type="password" name="passcheck" ><br>
    </div>
  </div>  
  </div>
  
  <div class="clearfix">
  	<button type="submit" class="submitbtn">저장</button>
  	<button type="button" class="cancelbtn">취소</button>
  </div>
  
 </form>
</div>
</div>
</body>
</html>