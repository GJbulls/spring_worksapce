

$('#btn').click(function(){
	$.ajax({
		type:'GET',
		dataType:'json',
		//data:'data='+$('#data').val(),
		//url:'process.do' 이렇게 data를 넘겨줄수 있다.
		url:'process.do?data='+$('#data').val(),
		//url:'process.do controller에서 받는다.  ('#data')값도 controller에서 String으로 받는다.(이름값)
		success:viewMessage //정상적으로 수행했을경우 view메시지 수행
	});
});



function viewMessage(res){
//	console.log(res);

//remove = 현재 선택되어있는 자신을 제거 / empty = 현재 선택되어있는 자식을 제거
//자식요소 삭제
$('#wrap').empty();
//$('wrap').children().remove();


//console창에 출력된것이 이제 브라우져에 출력됨
$('#wrap').append('<table><tr><th>employee_id</th><th>first_name</th><th>salary</th></tr></table>');
$.each(res, function(index, element){
	//$('#wrap table').append('<tr><td>'+element.employee_id+'</td><td>'+element.first_name +'</td><td>'+element.salary +'</td></tr>');
	//더 간결하게 표현
	$('#wrap table').append(`<tr><td>${element.employee_id}</td><td>${element.first_name}</td><td>${element.salary}</td></tr>`);
});


//검색결과 추가후 이름검색창 초기화 후 포커싱 된다.
$('#data').val('');
$('#data').focus();


}//end viewMessage()


















































