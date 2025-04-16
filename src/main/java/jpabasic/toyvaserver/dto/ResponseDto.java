package jpabasic.toyvaserver.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
public class ResponseDto<T> {

    private T item; // 단일 객체 응답
    private List<T> items; // 객체 리스트 응답
//    private Page<T> pageItems; // 페이지네이션 객체 응답
    private int statusCode;
    private String statusMessage;
    private String errorMessage;

}
