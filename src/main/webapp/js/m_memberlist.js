 $(document).ready(function(){
	 
	 
	 function setPaging(href, digit){
		 let active="";
		 let gray ="";
		 if(href ==""){ //href가 빈문자열인 경우
		 	if(isNaN(digit)){
				 gray="gray";
			 } else {
				 active="active";
			 }
		 }
		 let output = `<li class="page-item ${active}">`;
		 let anchor = `<a class="page-link ${gray}" ${href} > ${digit}</a></li>`;
		 output += anchor;
		 return output;
		 
	 }//setpaging end
	 
	 function ajax(sdata){
		 console.log(sdata);
		 
		 $,ajax({
			 type : "post",
			 data : sdata,
			 url : "memberList.et",
			 dataType : "json",
			 cache : false,
			 success : function(data){
				 $("#viewcount").val(data.limit);
				 $("thead").find("span").text(data.listcount);
				 
				 let digit = '이전&nbsp;'
				 let href="";
				 if(data.page > 1){
					 href = 'href=javascript:go(' + (data.page - 1) + ')';
				 } 
				 output += setPaging(href, digit);
				 
				 for(let i = data.startpage; i <= data.endpage; i++){
					 digit = i;
					 href = "";
					 if(i != data.page){
						 href = 'href=javascript:go(' + i + ')';
					 } 
					 output += setPaging(href, digit);
				 }
				 
				 digit = '&nbsp;다음&nbsp;';
				 href="";
				 if(data.page < data.maxpage){
					 href = 'href=javascript:go(' + (data.page + 1) + ')';
				 }
				 output += setPaging(href,digit);
				 
				 $('.pagination').append(output)
			 },
			 error : function(){
				 console.log('에러')
			 }
			 
		 })
	 }
	 
	 $('#viewpoint').change(function(){
		 go(1);
	 })
	 
	 
	    let tab_value = $("#tab").val();//1, 2, 3
	    if(tab_value){
		    $('ul.tabs li').removeClass('current');
			 $('.tab-content').removeClass('current');
		      $("li[data-tab=tab-" + tab_value).addClass('current');
			 $('#tab-'+tab_value).addClass('current');
	     }
	     
	 
	  $('ul.tabs li').click(function(){
		 var tab_id = $(this).attr('data-tab');
		 
		 $('ul.tabs li').removeClass('current');
		 $('.tab-content').removeClass('current');
		 
		 $(this).addClass('current');
		 $('#'+tab_id).addClass('current');
		 
		 console.log(tab_id.substr(4));
		 $("#tab").val(tab_id.substr(4));
	  });
	  
		
		//이용중지 해제를 클릭한 경우
		$(document).on("click", ".clear", function(event){
			 const answer = confirm("중지해제 하시겠습니까?");
			 console.log(answer);	// 취소를 클릭한 경우 - false
			 if(!answer) {	//취소를 클릭한 경우
				 event.preventDefault();	//이동하지 않습니다.
			 }
		}); //삭제 클릭 end 
	  
	 
	//회원 목록의 거절을 클릭한 경우
		$(document).on("click", ".reject", function(event){
			 const answer = confirm("정말로 거절하시겠습니까?");
			 console.log(answer);	// 취소를 클릭한 경우 - false
			 if(!answer) {	//취소를 클릭한 경우
				 event.preventDefault();	//이동하지 않습니다.
			 }
		}) //삭제 클릭 end 
		
	//회원 목록의 거절을 클릭한 경우
		$(document).on("click", ".delete",function(event){
			 const answer = confirm("정말로 삭제하시겠습니까?");
			 console.log(answer);	// 취소를 클릭한 경우 - false
			 if(!answer) {	//취소를 클릭한 경우
				 event.preventDefault();	//이동하지 않습니다.
			 }
		}) //삭제 클릭 end 
		
		//모달 수정하기 저장 확인
		$(document).on("click", ".submitbtn",function(event){
			 const answer = confirm("수정 하시겠습니까?");
			 console.log(answer);	// 취소를 클릭한 경우 - false
			 if(!answer) {	//취소를 클릭한 경우
				 event.preventDefault();	//이동하지 않습니다.
			 }
		}) //수정 확인 end
	
		//승인완료 탭에서 이용중지 클릭한 경우
		$(document).on("click", ".block", function(event){
			 const answer = confirm("이용중지 하시겠습니까?");
			 console.log(answer);	// 취소를 클릭한 경우 - false
			 if(!answer) {	//취소를 클릭한 경우
				 event.preventDefault();	//이동하지 않습니다.
			 }
		}) //수정 확인 end
	
	
	
	
	
	 //수정 확인 end
	
		/*$("#viewcount").on("keyup", function(){
			const value = $(this).val().toLowerCase();
			$("table tr").each(function(){
				console.log($(this).text().toLowerCase().indexOf(value) > -1 )
			
			$(this).toggle($(this).text().toLowerCase().indexOf(value)>-1)
			
			});
		});
	*/
		$(document).on("click", ".admit", function(){
		
				 const answer = confirm("정말로 승인하시겠습니까?");
			 console.log(answer);	// 취소를 클릭한 경우 - false
			 if(!answer) {	//취소를 클릭한 경우
				 event.preventDefault();	//이동하지 않습니다.
			 } else{
		
		 	let tr = $(this).parent().parent();
			let m_id = tr.prev().val(); 
			let dnum = tr.find("#dept").val();
			let pnum = tr.find("#pnum").val();
			
			location.href="memberConfirm.et?dnum=" + dnum + "&pnum=" + pnum + "&id=" + m_id + "&tab=1";
			 }
		
		});
		
	
	
	$('.update').click(function() {
		$('.modal-background').css('display', 'block');
		$('.modal-container').css('display', 'block');
		
		$('body').css('overflow', 'hidden');
		
		
		let tr= $(this).parent().parent();
		$('#name').val(tr.find(".name").text());
		$('#email').val(tr.find(".email").text());
		//$('#dname').val(tr.find(".dname").text());
		//$('#mjob').val(tr.find(".mjob").text());
		$('#empnum').val(tr.find(".empnum").text());
		
		let hiddnum = tr.find(".hiddnum").val();
		let hidpnum = tr.find(".hidpnum").val();
		
		//$('#dname option[value="' + hiddnum + '"]').prop('selected', true);
		//$('#mjob option[value="' + hidpnum + '"]').prop('selected', true);
		
		$('#dname option[value="' + hiddnum + '"]').prop('selected', true);
		$('#mjob option[value="' + hidpnum + '"]').prop('selected', true);
	
	
	})
	
	
	}); //main end
	