document.addEventListener("DOMContentLoaded", function() {
    let ws;

    document.getElementById("btnConnect").addEventListener("click", function() {
        ws = new WebSocket("ws://" + location.host + "/test/websocket");
// 호출하면 WebSocket이 서버에 연결을 시도합니다.
// 이 과정은 자동으로 진행되며, 연결이 성공하면 WebSocket이 열리게 됩니다.
        ws.onopen = function() {
            log("서버와 연결하였습니다.");
        };

        ws.onclose = function() {
            log("서버와 연결이 종료되었습니다.");
        };

        ws.onmessage = function(event) {
            log(event.data);
        };

        ws.onerror = function(event) {
            log("에러가 발생했습니다." + event);
        };
    });

    document.getElementById("btnDisConnect").addEventListener("click", function() {
        if (ws) {
            ws.close();
            log("서버와 연결 종료를 시도합니다.");
        }
    });

    document.getElementById("btnMsg").addEventListener("click", function() {
        const msgInput = document.getElementById("msg");
        if (ws && msgInput.value.trim() !== "") {
            ws.send(msgInput.value);
            log("메시지를 전송했습니다.");
            msgInput.value = "";
        }
    });

    function log(msg) {
        const messageContainer = document.querySelector(".message");
        const newMessage = document.createElement("div");
        newMessage.textContent = "[" + new Date().toLocaleTimeString() + "] " + msg;
        messageContainer.prepend(newMessage);
    }
});
