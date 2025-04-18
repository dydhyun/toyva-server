package jpabasic.toyvaserver.service.user;

import jpabasic.toyvaserver.dto.RegisteredUserDto;
import jpabasic.toyvaserver.dto.UserDto;
import jpabasic.toyvaserver.entity.User;
import jpabasic.toyvaserver.exception.PasswordMismatchException;
import jpabasic.toyvaserver.exception.UserNotFoundException;
import jpabasic.toyvaserver.repository.user.UserRepository;
import jpabasic.toyvaserver.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public Boolean userIdCheck(String userId) {

        return userRepository.findByUserId(userId).isPresent();
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
        User user = userRepository.findByUserId(userDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException(MessageUtils.MSG_USERID_NOT_FOUND));

        if (!isPasswordValid(user, userDto.getUserPw())) {
            throw new PasswordMismatchException(MessageUtils.MSG_USERPW_NOT_MATCH);
        }

        return new RegisteredUserDto(
                user.getUserId(),
                user.getNickname(),
                user.getEmail(),
                user.getCreatedAt()
        );
    }

    // 비밀번호 검증 메서드 (유틸성 메서드로 분리)
    private boolean isPasswordValid(User user, String rawPassword) {
        return user.getUserPw().equals(rawPassword);
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
