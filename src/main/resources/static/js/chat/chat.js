document.addEventListener("DOMContentLoaded", function() {
    // WebSocket 서버에 연결
    const socket = new WebSocket("ws://" + window.location.host + "/ws/chat");
    // 호출하면 WebSocket이 서버에 연결을 시도합니다.
    // 이 과정은 자동으로 진행되며, 연결이 성공하면 WebSocket이 열리게 됩니다.


    // 임시로 사용할 닉네임 (빈값 X, 12자 이하)
    let nickname;
    do {
        nickname = prompt("닉네임을 입력하세요 (최대 12자) :")?.trim();
    } while (!nickname || nickname.length > 12);

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
        const sendingTime = new Date().toLocaleTimeString();

        if (message) {
            socket.send("[" + sendingTime + "] " + nickname + " : " + message); // 메시지를 서버로 전송
            messageInput.value = ""; // 입력창 비우기
        }
    }

    // 엔터로 메세지 전송 허용
    document.getElementById("message").addEventListener("keydown", function(event) {
        if (event.key === "Enter") {
            event.preventDefault(); // 기본 Enter 동작 방지
            sendMessage(); // 메시지 전송
        }
    });
});
