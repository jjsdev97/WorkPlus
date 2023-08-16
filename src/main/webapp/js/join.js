//workplus
$(function() {

   let checkid = false;
   let checkpass = false;
   let checkempnum = false;
   let idcheck_value='';  //id 중복검사시 값
   let verifyemailcheck = false;

   //id중복검사 부분
   $("#idcheck").click(function() {
      const id = $('#id');
      const id_value = id.val();
      const pattern = /^\w{5,12}$/;


      if ($.trim(id_value) == "") {  //id값을 입력하지 않았을 때 
         alert("ID를 입력하세요");
         id.focus();
         
      } else { //id값을 입력했을 때

         //id값 유효성 검사
         if (!pattern.test(id_value)) {  //입력한 값이 조건에 맞지 않으면
            alert("영문자 숫자 _로 5~12자 가능합니다.");
            id.focus();
            checkid = false;
            return;
         }
         //입력한 값이 조건에 맞으면
         //ajax를 실행해서 db에 해당 id가 있는지 확인!
         $.ajax({
            url: "idcheck.et",
            data: { "id": id_value },
            success: function(resp) {
               if (resp == -1) { //db에 해당 id가 없는 경우
                  alert("사용 가능한 아이디 입니다.");
                  checkid = true;
                  idcheck_value = id_value;
               } else {   //db에 해당 id가 있는 경우(0)
                  alert("사용중인 아이디 입니다.");
                  checkid = false;
               }
            }  //success end 
         });   //ajax end
		
      }
   }); //#idcheck function end


   //도메인 선택 부분
   $("#email_sel").change(function() {
      const domain = $("#domain");
      if ($(this).val() == "") {   //직접 입력 선택한 경우
         domain.val("")
            .focus()
            .prop("readOnly", false);
      } else {   //도메인 선택한 경우
         domain.val($(this).val())
            .prop("readOnly", true);
      }

   });//email sel end


   //사원번호 유효성 검사
   $("#empnum").on('keyup', function() {
         const empnum = $("#empnum").val();
         const pattern = /^[0-9]{6}$/;
         
         if (!pattern.test(empnum)){
				checkempnum = false;
						} 
         
        $.ajax({
			url : "empnumcheck.et",
			data : {"empnum" : empnum},
			success : function(resp){
				if(resp == -1){ //db에 해당 사원번호가 없는 경우
				$("#empnum_message").css('font-size', '11px')
									.css('color', 'black')
               						.html("사용가능한 사원번호 입니다.");
               		checkempnum = true;				
				} else {
					
						$("#empnum_message").css('font-size', '11px')
										.css('color', 'red')
	               						.html("사용중인 사원번호 입니다.");
	               		checkempnum = false;				
						
						
					}
				}
		});
		
      }); //empnum end



   //비밀번호 일치 확인
   $("#passcheck").on('keyup',
      function() {
         const pass = $('#pass');
         const passcheck = $('#passcheck');

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
   ); //pass end


	$('.verify_emailbtn').click(function(){
		const email = $("input[name=email]").val();
		const domain = $("input[name=domain]").val();
		const emailaddr = email + "@" + domain;
			console.log("emailaddr:", emailaddr);
		alert('인증번호가 발송되었습니다.');
			
		$.ajax({
			url : "joinverifyemail.et",
			data : {"email" : email,
					"domain" : domain },
			success : function(resp){
				$("#authRadnum").val(resp); 
			 }
		}); //ajax end	
		
		
		
		 $('.verify').click(function(){
		 var inputAuthnum = $("input[name=verifynum]").val();
		 
		 if(inputAuthnum === ''){
			 alert('인증번호를 입력하세요');
			 verifyemailcheck = false;
			 return;
		 } else{
			 if(inputAuthnum == $("#authRadnum").val()){
				 alert("인증에 성공하였습니다.");
				 verifyemailcheck = true;
				 
			 } else {
				 alert("인증에 실패하였습니다.");
				 verifyemailcheck = false;
			 }
		 }
	 }); //verify click end
		
	});




   //회원가입 버튼 클릭할 때 이벤트 처리 부분
   $('form').submit(function() {

      const id = $('#id');


      //submit할 때 id값과 id중복검사 때 사용한 id가 다른 경우 메시지 출력
      let submit_id_value = $.trim(id.val());
      if (submit_id_value != idcheck_value) {
         alert("ID 중복검사를 하세요.");
         return false;
      }


      if (!checkempnum) { //사원번호 확인
         alert("사원번호를 확인해주세요");
         $("#empnum").val('').focus();
         return false;
      }

      const email = $("#email"); //이메일 입력 확인
      if ($.trim(email.val()) == '') {
         alert("E-mail 아이디를 입력하세요");
         email.focus();
         return false;
      }

      const domain = $("#domain"); //도메인 입력 확인
      if ($.trim(domain.val()) == '') {
         alert("E-mail 도메인을 입력하세요");
         domain.focus();
         return false;
      }

      const pass = $('#pass'); //비밀번호 입력 확인
      if ($.trim(pass.val()) == "") {
         alert("비밀번호를 입력 하세요");
         pass.focus();
         return false;
      }

      if (!checkpass) { //비밀번호 일치 확인
         alert("비밀번호를 확인해주세요");
         $("#passcheck").val('').focus();
         return false;
      }

	 if (verifyemailcheck) {
		        // verifyemailcheck가 true일 경우 폼을 제출합니다.
		        return true;
		    } else {
		        // verifyemailcheck가 false일 경우 폼 제출을 막습니다.
		        alert("이메일 인증을 완료해주세요.");
		        return false;
		    }

   });//form submit end


}); //main end
