package jpabasic.toyvaserver.config;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.concurrent.CopyOnWriteArrayList;

/*
 * WebSocket Handler 작성
 * 소켓 통신은 서버와 클라이언트가 1:n으로 관계를 맺는다. 따라서 한 서버에 여러 클라이언트 접속 가능
 * 서버에는 여러 클라이언트가 발송한 메세지를 받아 처리해줄 핸들러가 필요
 * TextWebSocketHandler를 상속받아 핸들러 작성
 * 클라이언트로 받은 메세지를 log로 출력하고 클라이언트로 환영 메세지를 보내줌
 * */
public class ChatWebSocketHandler extends TextWebSocketHandler {

    // CopyOnWriteArrayList : 동시성 제어 리스트 참고글 ->
    // https://curiousjinan.tistory.com/entry/java-copyonwritearraylist-concurrency#3.%20%EC%BD%94%EB%93%9C%20%EC%9E%91%EC%84%B1%3A%20ArrayList%EC%99%80%20CopyOnWriteArrayList%EB%A5%BC%20%EC%82%AC%EC%9A%A9%ED%95%98%EB%8A%94%20%ED%81%B4%EB%9E%98%EC%8A%A4%20(%EC%8A%A4%ED%94%84%EB%A7%81%20%EB%B9%88)-1
    // 현재 연결된 세션들을 담을거임
    private final CopyOnWriteArrayList<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    // 소켓연결 확인부 : 클라이언트가 웹소켓 연결 요청했을 때
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        System.out.println("새로운 클라이언트 연결: " + session.getId());
    }

    // 메세지 전송핸들부 : 클라이언트에게 메세지 전송받았을 때
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        System.out.println("수신한 메시지: " + payload);

        for (WebSocketSession s : sessions) {
            s.sendMessage(new TextMessage(payload));
        }
    }

    // 소켓 종료 확인부 : 클라이언트 연결 종료되면 실행
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
        System.out.println("클라이언트 연결 종료: " + session.getId());
    }
}
