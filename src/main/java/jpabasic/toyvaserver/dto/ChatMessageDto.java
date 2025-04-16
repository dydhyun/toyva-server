package jpabasic.toyvaserver.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChatMessageDto {

    private String sender;
//    private RegisteredUserDto senderInfo;
    private String content;
    private String time;

}
