 $(document).ready(function(){
	 
	 
	    let tab_value = $("#tab").val();
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
	  });
	  
		//검색 클릭 후 응답화면에는 검색시 선택한 필드가 선택되도록
		let selectedValue = '${search_field}'
		if(selectedValue != '-1')
			$("#viewcount").val(selectedValue);
		else
			selectedValue=0;   //선택된 필드가 없는 경우 기본적으로 아이디를 선택하도록 합니다.
		
			
		//검색 버튼 클릭한 경우
		$("button").click(function(){
			//검색어 공백 유효성 검사합니다.
			if($input.val() == ''){
				alert("검색어를 입력하세요");
				$input.focus();
				return false;
			}
		}); //button click end
		
		
		//회원 목록의 승인을 클릭한 경우
		$(document).on("click", "#admit" ,function(event){
			 const answer = confirm("정말로 승인하시겠습니까?");
			 console.log(answer);	// 취소를 클릭한 경우 - false
			 if(!answer) {	//취소를 클릭한 경우
				 event.preventDefault();	//이동하지 않습니다.
			 }
		}); //삭제 클릭 end 
		
		//이용중지 해제를 클릭한 경우
		$(document).on("click", "#clear", function(event){
			 const answer = confirm("정말로 승인하시겠습니까?");
			 console.log(answer);	// 취소를 클릭한 경우 - false
			 if(!answer) {	//취소를 클릭한 경우
				 event.preventDefault();	//이동하지 않습니다.
			 }
		}); //삭제 클릭 end 
	  
	 
	//회원 목록의 거절을 클릭한 경우
		$(document).on("click", "#reject", function(event){
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
	
		$("#viewcount").on("keyup", function(){
			const value = $(this).val().toLowerCase();
			$("table tr").each(function(){
				console.log($(this).text().toLowerCase().indexOf(value) > -1 )
			
			$(this).toggle($(this).text().toLowerCase().indexOf(value)>-1)
			
			});
		});
	
		$(document).on("click", "#admit", function(){
			let m_id = $("#m_id").val(); 
			let dnum = $("#dept").val();
			let pnum = $("#pnum").val();
			
			location.href="memberConfirm.et?dnum=" + dnum + "&pnum=" + pnum + "&id=" + m_id + "&tab=1";
		});
	
	
	
	}); //main end
	