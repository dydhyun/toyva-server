package jpabasic.toyvaserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration // 스프링 설정 클래스임을 명시
@EnableWebSocket // WebSocket 기능을 활성화
public class WebSocketConfig implements WebSocketConfigurer {

    private final WebSocketHandler webSocketHandler;

    public WebSocketConfig() {
        webSocketHandler = new ChatWebSocketHandler();
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // WebSocket 엔드포인트를 "/ws/chat"으로 설정
        // (ws://localhost:8080/ws/chat 로 요청 들어오면, WebSocket 통신 시작)
        registry.addHandler(webSocketHandler, "/ws/chat")
                .setAllowedOrigins("http://localhost:3000");

        registry.addHandler(webSocketHandler, "/ws/TestWebsocket")
                .setAllowedOrigins("http://localhost:3000"); // ws에 대한 CORS 허용

    }
}
