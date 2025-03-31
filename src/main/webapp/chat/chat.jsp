<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>WebSocket Chat</title>
  <style>
    body {
      font-family: Arial, sans-serif;
    }
    #chat {
      border: 1px solid #ccc;
      height: 300px;
      overflow-y: auto;
      margin-bottom: 10px;
      padding: 10px;
    }
    #message {
      width: 80%;
      padding: 10px;
    }
    button {
      padding: 10px;
    }
  </style>
</head>
<body>
<h2>WebSocket 채팅</h2>
<div id="chat"></div>
<input id="message" type="text" placeholder="메시지를 입력하세요" />
<button onclick="sendMessage()">전송</button>

<script>
  // WebSocket 서버에 연결
  const socket = new WebSocket("ws://" + window.location.host + "/ws/chat");

  // WebSocket이 열리면 실행되는 이벤트 핸들러
  socket.onopen = function() {
    console.log("WebSocket 연결됨");
  };

  // 서버로부터 메시지를 받으면 실행되는 이벤트 핸들러
  socket.onmessage = function(event) {
    const chat = document.getElementById("chat");
    const msg = document.createElement("p");
    msg.innerText = event.data; // 서버에서 받은 메시지
    chat.appendChild(msg); // 채팅 영역에 메시지 추가
    chat.scrollTop = chat.scrollHeight; // 스크롤을 최신 메시지로 자동 이동
  };

  // 메시지를 서버로 전송하는 함수
  function sendMessage() {
    const messageInput = document.getElementById("message");
    const message = messageInput.value; // 입력한 메시지 가져오기
    if (message) {
      socket.send(message); // 메시지를 서버로 전송
      messageInput.value = ""; // 입력창 비우기
    }
  }
</script>
</body>
</html>
