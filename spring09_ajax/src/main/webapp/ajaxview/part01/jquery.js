
//window.onload=function(){}


//<script defer넣으면 이렇게 안써도 됌
//${document}.ready(function(){
//	$('#btn').on('click', process);
//});


//<script defer넣으면 이렇게 간단하게 가능
$('#btn').on('click', process);
console.log('jquery');


function process(){
 $.ajax({
 	type:'GET',
 	dataType:'text',
 	url:'ajaxview/part01/sample.txt',
 	success:viewMessage
 });
}




function viewMessage(res){
	$('#wrap').html(res);
}