package jpabasic.toyvaserver.exception;

// 비밀번호 불일치 시 발생하는 예외
public class PasswordMismatchException extends RuntimeException {
    public PasswordMismatchException(String message) {
        super(message);  // 예외 메시지를 부모 클래스에 전달
    }
}
