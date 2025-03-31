package jpabasic.toyvaserver.testServer;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket")
public class SocketServer{

    @OnOpen
    public void handelOpen() {
        System.out.println("클라이언트가 접속했습니다.");
    }

    @OnClose
    public void handleClose() {
        System.out.println("클라이언트가 종료했습니다.");
    }

    @OnMessage
    public String handleMassage(String msg) {
        System.out.println("클라이언트가 보낸 메시지: " + msg);

        return "(응답)" + msg;
    }

    @OnError
    public void handleError(Throwable e) {
        System.out.println("에러 발생 " + e.getMessage());
    }

}
