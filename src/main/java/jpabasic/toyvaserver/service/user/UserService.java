package jpabasic.toyvaserver.service.user;

import jpabasic.toyvaserver.dto.RegisteredUserDto;
import jpabasic.toyvaserver.dto.UserDto;

import java.util.Map;

public interface UserService {
    Boolean userIdCheck(String userId);

    Boolean nicknameCheck(String nickname);

    RegisteredUserDto register(UserDto userDto);

    RegisteredUserDto login(UserDto userDto);

    String findByEmail(String email);

    String modifyPasswd(String newPasswd);
}
