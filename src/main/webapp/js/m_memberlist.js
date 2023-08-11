 $(document).ready(function(){
	 
	 
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
	  

			
		//검색 버튼 클릭한 경우

		
		
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
	
		/*$("#viewcount").on("keyup", function(){
			const value = $(this).val().toLowerCase();
			$("table tr").each(function(){
				console.log($(this).text().toLowerCase().indexOf(value) > -1 )
			
			$(this).toggle($(this).text().toLowerCase().indexOf(value)>-1)
			
			});
		});
	*/
		$(document).on("click", "#admit", function(){
			let m_id = $("#m_id").val(); 
			let dnum = $("#dept").val();
			let pnum = $("#pnum").val();
			
			location.href="memberConfirm.et?dnum=" + dnum + "&pnum=" + pnum + "&id=" + m_id + "&tab=1";
		});
		
	
	
	$('.update').click(function() {
		$('.modal-background').css('display', 'block');
		$('.modal-container').css('display', 'block');
		
		$('body').css('overflow', 'hidden');
		
		
		let tr= $(this).parent().parent();
		$('#name').val(tr.find(".name").text());
		$('#email').val(tr.find(".email").text());
		$('#dname').val(tr.find(".dname").text());
		$('#mjob').val(tr.find(".mjob").text());
		$('#empnum').val(tr.find(".empnum").text());
	})
	
	
	}); //main end
	