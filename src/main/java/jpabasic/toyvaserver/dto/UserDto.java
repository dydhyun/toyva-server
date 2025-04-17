package jpabasic.toyvaserver.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {
    // 회원가입 시 클라이언트에서 서버로 보내는 객체를 담는 Dto
    private Long id;
    private String userId;
    private String userPw;
    private String nickname;
    private String email;
    private String status;
    private LocalDateTime createdAt;

}
