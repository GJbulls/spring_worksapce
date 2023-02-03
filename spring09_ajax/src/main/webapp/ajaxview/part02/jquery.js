
$('#btn').on('click',process);

//ajax로 보내는방법
/*
function process(){
	$.ajax({
		type:'POST',
		dataType:'text',
		data:{name:$('#name').val()},
		url:'name.do',
		success:viewMessage
	});
}
*/



//POST로 보내는방법
function process(){
	$.post('name.do',{name:$('#name').val()}, viewMessage);
}


function viewMessage(res){
	$('#wrap').html(res);
}


















