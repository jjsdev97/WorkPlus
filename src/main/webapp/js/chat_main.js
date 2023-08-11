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
		let tr = $(this).parent().parent().parent();
		let name = tr.find(".name").text();
		let d_name = tr.find(".dept").text();
		let status_img = tr.find(".img").text();
		
		$("#popup_name").text(name);
		$("#popup_dname").text(d_name);
		$("#popup_dname").text(d_name);
		
		
		console.log($href);
		layer_popup($href);
	})

    $('.solChatStart_layer').click(function(){
		var $href = $(this).attr('href');
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
$('.star').click(function(){
	var $src = $(this).find("img").attr('src');
	console.log($src);
	///WorkPlus/img/star_f.png
	if($src.lastIndexOf("/star_f.png") > -1 ){
		$src = "star_e.png"
	} else {
		$src = "star_f.png"
	}
	
	$(this).find("img").attr('src', "img/"+$src)

}); //$('.star').click(function(){
	



});//$(document).ready(function( ){

