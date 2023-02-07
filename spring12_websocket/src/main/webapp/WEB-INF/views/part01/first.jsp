<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script type="text/javascript">

//defer는 별도의 파일명으로 설정되어있지 않는이상 써줄 필요없다. 왜냐하면 jsp페이지에서 js를 작성 했기 때문에 $(document).ready(function(){};를 사용한다.
	let websocket;
	
	$(document).ready(function(){
	//입장 버튼을 눌렀을 때 이벤트 처리
	$('#enterBtn').on('click',function(){
		//웹 소켓 연결 연결할때는 프로토콜이 ws이다
		websocket = new WebSocket("ws://localhost:8090/myapp/chatws.do");
		
		
		
		//웹 소켓 이벤트 처리
		websocket.onopen = onOpen; //소켓서버에 연결이 되었을때
		websocket.onmessage = onMessage ; //소켓서버에서 클라이언트에 메시지를 보냈을때
		websocket.onclose = onClose; //소켓서버에 연결이 종료 되었을 때
		
		});
	//전송 버튼을 눌렀을때 이벤트 처리
	$('#sendBtn').on('click',function(){
		messageProcess();
	});
	
		
	//message 창에서 enter를 눌렀을때 메시지를 전송
	//키보드를 눌렀을때 이벤트 처리
	$('#message').keypress(function(event){//on('keypress',function() 처럼 해도 됨
		if(event.keyCode==13){
		messageProcess();
		}
	});
	
	//퇴장 버튼을 눌렀을때 이벤트 처리
	$('#exitBtn').on('click',function(){
		websocket.close();
	});
	
});
	/////////////////////////////////////////////////////////////////////////
	
	function messageProcess(){
		//nickname과 message에 입력된 내용을 서버에 전송
		let nick = $('#nickname').val();
		let msg = $('#message').val();
		
		//메세지전송
		websocket.send(nick + ":" + msg);
		//메세지 입력창 초기화
		$('#message').val('');
	} ////end messageProcess()////////////////////////////////////////////////
	//WebSocket이 연결된 경우 호출되는 함수
	function onOpen(evt){
		console.log("웹 소켓에 연결 성공");
	}//end onOpen((//////////////////////////////////))
	
	
	//서버에서 메시지가 왔을 때 호출 되는 함수
	function onMessage(evt){
		
		console.log("evt:", evt);
		//서버가 전송한 메시지 가져오기
		let data = evt.data;
		
		$('#chatMessageArea').append('<p>'+data+'</p>');
	} //end onMessage((//////////////////////////////////))
	
	//WebSocket이 연결 해제 된 경우 호출되는 함수
	function onClose(evt){
		console.log("웹 소켓에 연결 해제");
	}
	
</script>

</head>
<body>
	<span>이름:</span>
	<input type="text" id="nickname" />
	<input type="button" id="enterBtn" value="입장" />
	<input type="button" id="exitBtn" value="나가기" />

	<h1>채팅 영역</h1>
	<div id="charArea">
		<div id="chatMessageArea"></div>
	</div>

	<span>전송할 메시지:</span>
	<input type="text" id="message" />
	<input type="button" id="sendBtn" value="전송" />
</body>
</html>