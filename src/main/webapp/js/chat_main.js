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
	
//------------- status 내 프로필 상태 변경 -------------//

$('.statusOption').click(function(e){
	
	var $change_src = e.target.id;
	console.log("chang_src="+$change_src)
	
	document.getElementById('myStatus').setAttribute('src',"img/"+$change_src)
	
	
})


});//$(document).ready(function( ){

