package jpabasic.toyvaserver.rest.user;

import jpabasic.toyvaserver.dto.UserDto;
import jpabasic.toyvaserver.entity.User;
import jpabasic.toyvaserver.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserRestController {

    private final UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody UserDto userDto){

        User user = User.builder()
                .userId(userDto.getUserId())
                .userPw(userDto.getUserPw())
                .nickname(userDto.getNickname())
                .email(userDto.getEmail())
                .build();

        userRepository.save(user);
        return ResponseEntity.ok("회원가입 성공!");
    }
}
