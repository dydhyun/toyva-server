package jpabasic.toyvaserver.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegisteredUserDto {
    // 로그인한 유저에 대해 서버에서 클라이언트로 보내는 용도의 Dto
    private String userId;
    private String nickname;
    private String email;
    private LocalDateTime createdAt;

}
