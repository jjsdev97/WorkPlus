$(document).ready(function( ){
//-------------- 팝업 시작 ----------------------//
	$('.status_layer').click(function(){
        var $href = $(this).attr('href');
        console.log($href);
        layer_popup($href); //layer_popup(#layer1)
    });

	$('.font_setting_layer').click(function(){
        var $href = $(this).attr('href');
        console.log($href);
        layer_popup($href); //layer_popup(#layer2)
    });
    
 	$('.friendProfile_layer').click(function(){
		var $href = $(this).attr('href');
		let tr = $(this).closest('tr');//가장 가까운 부모 tr을 찾음
		
		let name = tr.find(".name").text();
		let d_name = tr.find(".dept").text();
		let status_img = tr.find(".status_img").attr("src");
		let m_job = tr.find(".f_job").text();
		console.log("m_job = " + m_job);
		let m_id = tr.find(".f_id").text();
		console.log("f_id =" + m_id)
		
		$("#popup_name").text(name);
		$("#popup_dname").text(d_name);
		$("#popup_img").attr("src", status_img);
		$("#popup_job").text(m_job);
		$("#popup_id").text(m_id + "@work.com");
		
		
		console.log($href);
		
		//클릭한 tr요소의 옆에 레이어 추가
		var newLayer3 = $('#layer3').clone().addClass('active');
		tr.after(newLayer3);
		
		layer_popup(newLayer3);
	})

    $('.solChatStart_layer').click(function(){
		var $href = $(this).attr('href');
		let tr = $(this).parent().parent().parent();
		let f_id = tr.find(".f_id").text();
		$("#f_id").text(f_id);
		console.log("f_id =" + f_id)
		
		let $hrefValue = $(this).siblings('#layer4').find(".moveHref").attr('href'); 
   		console.log("hrefValue = " + $hrefValue);
		console.log("f_id=" + f_id)
		
		$(this).siblings('#layer4').find(".moveHref").attr('href','Chatlist.chat?f_id='+f_id);
		
		
		console.log($href);
		layer_popup($href);
	})
    
    function layer_popup(el){

        var $el = $(el);    //레이어의 id를 $el 변수에 저장
        
        $el.show();

        $el.find('a.btn-layerClose').click(function(){
            $el.hide(); // 닫기 버튼을 클릭하면 레이어가 닫힌다.
            return false;
        });
       
    }


	

//------------- 즐겨찾기 -------------//
$('.star').click(function(e){
	
	var $src = $(this).find("img").attr('src');
	console.log($src);
	
	//a태그의 href 값 가져오기	
	var $href = $(this).attr('href');
	console.log($href);
	
	//?앞의 값까지만 분리
	$shref = $href.split("?")[0];
	console.log($shref);
	$f_id = $href.split("?")[1];
	console.log($f_id);
	
	///WorkPlus/img/star_f.png
	if($src.lastIndexOf("/star_f.png") > -1 ){
		$src = "star_e.png"
		$shref = "ChatFBookMarkRemove.chat"
	} else {
		$src = "star_f.png"
		$shref = "ChatFBookMarkAdd.chat"
	}
	
	$(this).find("img").attr('src', "img/"+$src)
	$(this).attr('href', $shref + "?" +$f_id )
	console.log("a");
	
}); //$('.star').click(function(){
	



});//$(document).ready(function( ){

