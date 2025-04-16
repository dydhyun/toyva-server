package jpabasic.toyvaserver.service.user;

import jpabasic.toyvaserver.dto.RegisteredUserDto;
import jpabasic.toyvaserver.dto.UserDto;

import java.util.Map;

public interface UserService {
    Map<String, String> memberIdCheck(String memberId);

    Map<String, String> nicknameCheck(String nickname);

    RegisteredUserDto register(UserDto userDto);

    UserDto login(UserDto userDto);

    String findByEmail(String email);

    String modifyPasswd(String newPasswd);
}
