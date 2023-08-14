function go(page) {
	const limit = $("#viewcount").val();
	
//  const data = 'limit=${limit}&state=ajax&page=${page}';
	const data = {limit: limit, state: "ajax", page: page};
	// 이제 data 변수에 선택된 페이지 번호와 한 페이지에 보여줄 글의 개수가 들어 있습니다.
	// 이 데이터를 사용하여 서버로 요청을 보낼 수 있습니다.
	ajax(data);
}

function setPaging(href, digit) {
	let active="";
	let gray="";
	if(href=="") { // href가 빈 문자열인 경우
		if(isNaN(digit)) {
			gray="gray";
		} else {
			active = "active";
		}
	}
	// let: 이 안에서만 사용할 변수
	let output = `<li class="page-item ${active}">`;
//	let anchor = "<a class="page-link " + gray + "'" + href + ">" + digit + "</a></li>";
	let anchor = `<a class="page-link ${gray}" ${href}>${digit}</a></li>`;
	output += anchor;
	return output;
}

function ajax(sdata) {
	console.log(sdata)
	
	$.ajax({
		type: "POST",
		data: sdata,
		url: "ItemList.ca",
		dataType: "json",
		cache: false, 
		success: function(data) {
			$("#viewcount").val(data.limit);
			$("thead").find("span").text("글 개수: " + data.listcount);
			
			if(data.listcount > 0) {
				$("tbody").remove();
				let num = data.listcount - (data.page - 1) * data.limit;
				console.log(num)
				let output = "<tbody>";
				$(data.itemlist).each(
					function(index, item) {
						output += '<tr><td>' + (num--) + '</td>'
						const blank_count = item.board_re_lev * 2 + 1;
						let blank = '&nbsp';
						for (let i = 0; i < blank_count; i++) {
							blank += '&nbsp;&nbsp;';
						}
						
						let img="";
						if(item.board_re_lev > 0) {
							img = "<img src='image/line.gif'>";
						}
						
						let subject = item.ITEM_NAME;
						if(subject.length >= 20) {
							subject = subject.substr(0,20) + "...";
						}
						
						output += "<td><div>" + blank + img
						output += '<a href="ItemDetail.ca?num=' + item.ITEM_UID + '">'
						// g: global
						output +=  subject.replace(/</g, '&lg;').replace(/>/g, '&gt;')
							   + '</a></div></td>'
							   + "<td><div>" + item.ITEM_PRICE + "</td></div>"
							   + "<td><div>" + item.ITEM_MENU + "</td></div>"
					})
				output += "</tbody>"
				$('table').append(output) // 테이블 완성	
				
				$(".pagination").empty(); // 페이지 처리 영역 내용 제거
				output = "";
				
				let digit = '이전&nbsp';
				let href = "";
				if(data.page > 1) {
					href = 'href=javascript:go(' + (data.page - 1) + ')';
				}
				output += setPaging(href, digit);
				
				for(let i = data.startpage; i <= data.endpage; i++) {
					digit = i;
					href = "";
					if (i != data.page) {
						href = "href=javascript:go(" + i + ")";
					}
					output += setPaging( href, digit);
				}
				digit = '다음&nbsp';
				href = "";
				if(data.page < data.maxpage) {
					href = 'href=javascript:go(' + (data.page + 1) + ')';
				}
				output += setPaging(href, digit);
				$('.pagination').append(output);
			}
		},
		error: function() {
			console.log('에러')
		}
	}) // ajax end
} // function of ajax end
$(function () {
	$("button").click(function() {
		location.href = "ItemWrite.ca";
	});
	
	$("#viewcount").change(function() {
		go(1); // 보여줄 페이지 1
	});
})