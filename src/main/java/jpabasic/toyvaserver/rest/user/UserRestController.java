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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserRestController {

    private static final Logger log = LoggerFactory.getLogger(UserRestController.class);
    private final UserService userService;

    @GetMapping("/userId-check")
    public ResponseEntity<?> userIdCheck(@RequestParam String userId){
        ResponseDto<Boolean> responseDto = new ResponseDto<>();

        try {
            log.info("\nexec -- userIdCheck : {}", userId);
            boolean isDuplicated = userService.userIdCheck(userId);

            responseDto.setItem(isDuplicated);
            responseDto.setStatusCode(HttpStatus.OK.value());
            responseDto.setStatusMessage("userIdCheck ok");

            return ResponseEntity.ok(responseDto);
        }catch (Exception e){
            log.error(e.getMessage());

            responseDto.setErrorMessage(e.getMessage());
            responseDto.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseDto.setStatusMessage(e.getMessage());

            return ResponseEntity.internalServerError().body(responseDto);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserDto userDto){
        ResponseDto<RegisteredUserDto> responseDto = new ResponseDto<>();

        try {
            log.info("\nexec -- signup UserDto : {}", userDto.toString());

            RegisteredUserDto registeredUserDto = userService.register(userDto);

            responseDto.setItem(registeredUserDto);
            responseDto.setStatusCode(HttpStatus.OK.value());
            responseDto.setStatusMessage("created");

            return ResponseEntity.ok(responseDto);
        } catch (Exception e){
            log.error("signup err : {}", e.getMessage());

            responseDto.setErrorMessage(e.getMessage());
            responseDto.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseDto.setStatusMessage(e.getMessage());

            return ResponseEntity.internalServerError().body(responseDto);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto){
        ResponseDto<RegisteredUserDto> responseDto = new ResponseDto<>();

        try {
            log.info("\nexec -- login user : {}", userDto);

            boolean isExistUser = userService.userIdCheck(userDto.getUserId());

            if (isExistUser){
                log.info("존재하는유저임");
            } else {
                log.info("존재 안 하는유저임");
            }
            RegisteredUserDto registeredUserDto = userService.login(userDto);

            return ResponseEntity.ok(responseDto);
        } catch (Exception e){

            return ResponseEntity.internalServerError().body(responseDto);
        }
    }


}
