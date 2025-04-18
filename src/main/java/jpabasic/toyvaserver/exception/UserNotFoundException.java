package jpabasic.toyvaserver.exception;

// 사용자 ID가 존재하지 않을 때 발생하는 예외
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);  // 예외 메시지를 부모 클래스에 전달
    }
}
