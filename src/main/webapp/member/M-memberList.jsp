<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 관리</title>
<link href="/WorkPlus/css/M-memberlist.css" rel="stylesheet">
<jsp:include page="/header.jsp" />

<script>
 $(document).ready(function(){
	  $('ul.tabs li').click(function(){
		 var tab_id = $(this).attr('data-tab');
		 
		 $('ul.tabs li').removeClass('current');
		 $('.tab-content').removeClass('current');
		 
		 $(this).addClass('current');
		 $('#'+tab_id).addClass('current');
	  });
	  
	//검색 클릭 후 응답화면에는 검색시 선택한 필드가 선택되도록 합니다.
		let selectedValue = '${search_field}'
		if(selectedValue != '-1')
			$("#viewcount").val(selectedValue);
		else
			selectedValue=0;   //선택된 필드가 없는 경우 기본적으로 아이디를 선택하도록 합니다.
		
		const $input = $("input[name=search_word]")
		//검색 후 selectedValue값에 따라 placeholder가 나타나도록 합니다.
		const message = ["아이디", "이름", "나이", "여 또는 남"]
		
		$input.attr("placeholder", message[selectedValue] + " 입력하세요");
		
			
		//검색 버튼 클릭한 경우
		$("button").click(function(){
			//검색어 공백 유효성 검사합니다.
			if($input.val() == ''){
				alert("검색어를 입력하세요");
				$input.focus();
				return false;
			}
			
			const word = $input.val();
			if(selectedValue === '2'){
				const pattern = /^[0-9]{2}$/;
				if(!pattern.test(word)){
					alert("나이는 형식에 맞게 입력하세요(두자리 숫자)");
					$input.val('').focus();
					return false;
				}
			} else if (selectedValue === '3'){
				if(word != "남" && word !="여"){
					alert("남 또는 여를 선택하세요");
					$input.val('').focus();
					return false;
				}
			}
		}); //button click end
		
		//검색어 입력창에 placeholder 나타나도록 합니다.
		$("#viewcount").change(function(){
			selectedValue = $(this).val();
			$input.val('').attr("placeholder", message[selectedValue] + " 입력하세요");
		})//$("#viewcount").change end
		
		
		//회원 목록의 삭제를 클릭한 경우
		$("tr > td:nth-child(3) > a").click(function(event){
			 const answer = confirm("정말로 삭제하시겠습니까?");
			 console.log(answer);	// 취소를 클릭한 경우 - false
			 if(!answer) {	//취소를 클릭한 경우
				 event.preventDefault();	//이동하지 않습니다.
			 }
		}) //삭제 클릭 end 
	  
 });
</script>

</head>
<body>
<div class="main">
 <h3>사용자 관리</h3><hr>
 <div class="container">
   <ul class="tabs">
   	 <li class="tab-link current" id="waiting" data-tab="tab-1">가입대기</li>
   	 <li class="tab-link" id="block" data-tab="tab-2">이용중지</li>
   	 <li class="tab-link" id="completion" data-tab="tab-3">승인완료</li>
   </ul>
   
   <form action="memberList.net" method="post">
			<div class="input-group">
				<select id="viewcount" name="search_field">
					<option value="0" selected>아이디</option>
					<option value="1">이름</option>
					<option value="2">나이</option>
					<option value="3">성별</option>
				</select> 
				   <input name="search_word" type="text" class="form-control"
					      placeholder="아이디 입력하세요"    value="${search_word}">
				  <button class="btn btn-primary" type="submit">검색</button>
			</div>
		</form>
   
   
   <div id="tab-1" class="tab-content current">가입대기 내용</div>
   <div id="tab-2" class="tab-content">이용중지 내용</div>
   <div id="tab-3" class="tab-content">승인완료 내용</div>

 </div><%-- container end --%>
</div><%-- main end --%>
</body>
</html>