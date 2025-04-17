package jpabasic.toyvaserver.service.user;

import jpabasic.toyvaserver.dto.RegisteredUserDto;
import jpabasic.toyvaserver.dto.UserDto;
import jpabasic.toyvaserver.entity.User;
import jpabasic.toyvaserver.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public Boolean userIdCheck(String userId) {

        if (userRepository.findByUserId(userId).isPresent()) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean nicknameCheck(String nickname) {
        return false;
    }

    @Override
    public RegisteredUserDto register(UserDto userDto) {

        User user = User.builder()
                .userId(userDto.getUserId())
                .userPw(userDto.getUserPw())
                .nickname(userDto.getNickname())
                .email(userDto.getEmail())
                .build();
        userRepository.save(user);

        return new RegisteredUserDto(userDto.getUserId(), userDto.getNickname(), userDto.getEmail(), user.getCreatedAt());
    }

    @Override
    public RegisteredUserDto login(UserDto userDto) {

//        RegisteredUserDto registeredUserDto = userRepository.
//        String userId = userRepository.
        return null;
    }

    @Override
    public String findByEmail(String email) {
        return "";
    }

    @Override
    public String modifyPasswd(String newPasswd) {
        return "";
    }
}
