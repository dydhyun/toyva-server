package jpabasic.toyvaserver.rest.user;

import jpabasic.toyvaserver.dto.RegisteredUserDto;
import jpabasic.toyvaserver.dto.ResponseDto;
import jpabasic.toyvaserver.dto.UserDto;
import jpabasic.toyvaserver.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserRestController {

    private static final Logger log = LoggerFactory.getLogger(UserRestController.class);
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserDto userDto){
        ResponseDto<RegisteredUserDto> responseDto = new ResponseDto<>();

        try {
            log.info("signup UserDto : {}", userDto.toString());

            RegisteredUserDto registeredUserDto = userService.register(userDto);

            responseDto.setItem(registeredUserDto);
            responseDto.setStatusCode(HttpStatus.OK.value());
            responseDto.setStatusMessage("created");

            return ResponseEntity.ok(responseDto);
        } catch (Exception e){
            log.error("signup err : {}", e.getMessage());

            responseDto.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseDto.setStatusMessage(e.getMessage());
            responseDto.setErrorMessage(e.getMessage());

            return ResponseEntity.internalServerError().body(responseDto);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto){
        ResponseDto<RegisteredUserDto> responseDto = new ResponseDto<>();

        try {
            log.info("login user");

            return ResponseEntity.ok(responseDto);
        } catch (Exception e){

            return ResponseEntity.internalServerError().body(responseDto);
        }
    }


}
