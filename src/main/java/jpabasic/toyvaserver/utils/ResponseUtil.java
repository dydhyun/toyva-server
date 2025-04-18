package jpabasic.toyvaserver.utils;

import jpabasic.toyvaserver.dto.ResponseDto;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {
    public static <T> ResponseEntity<ResponseDto<T>> ok(T data, int code, String message) {
        ResponseDto<T> dto = new ResponseDto<>();
        dto.setStatusCode(code);
        dto.setStatusMessage(message);
        dto.setItem(data);

        return ResponseEntity.ok(dto);
    }

    public static <T> ResponseEntity<ResponseDto<T>> error(T data, int code, String message) {
        ResponseDto<T> dto = new ResponseDto<>();
        dto.setStatusCode(code);
        dto.setStatusMessage(message);
        dto.setErrorMessage(message);
        dto.setItem(data);

        return ResponseEntity.status(code).body(dto);
    }
}

