package jpabasic.toyvaserver.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegisteredUserDto {
    // 서버에서 클라이언트로 보내는 용도의 Dto
    private String userId;
    private String nickname;
    private String email;
    private LocalDateTime createdAt;

}
