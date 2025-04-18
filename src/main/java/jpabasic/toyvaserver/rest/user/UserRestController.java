package jpabasic.toyvaserver.rest.user;

import jpabasic.toyvaserver.dto.RegisteredUserDto;
import jpabasic.toyvaserver.dto.ResponseDto;
import jpabasic.toyvaserver.dto.UserDto;
import jpabasic.toyvaserver.exception.PasswordMismatchException;
import jpabasic.toyvaserver.exception.UserNotFoundException;
import jpabasic.toyvaserver.service.user.UserService;
import jpabasic.toyvaserver.utils.MessageUtils;
import jpabasic.toyvaserver.utils.ResponseUtil;
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
            responseDto.setStatusMessage(MessageUtils.MSG_USERID_DUPLICATION);

            return ResponseEntity.internalServerError().body(responseDto);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserDto userDto){
        ResponseDto<RegisteredUserDto> responseDto = new ResponseDto<>();

        try {
            log.info("\nexec -- signup UserDto : {}", userDto);

            RegisteredUserDto registeredUserDto = userService.register(userDto);

            responseDto.setItem(registeredUserDto);
            responseDto.setStatusCode(HttpStatus.OK.value());
            responseDto.setStatusMessage("created");

            return ResponseEntity.ok(responseDto);
        } catch (Exception e){
            log.error("signup failed : ", e);

            responseDto.setErrorMessage(e.getMessage());
            responseDto.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseDto.setStatusMessage(e.getMessage());

            return ResponseEntity.internalServerError().body(responseDto);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto) {

        try {
            log.info("\nexec -- login user : {}", userDto);

            RegisteredUserDto dto = userService.login(userDto);

            return ResponseUtil.ok(dto, HttpStatus.OK.value(), MessageUtils.MSG_LOGIN_SUCCESS);
        } catch (UserNotFoundException e) {
            log.info("login failed : ", e);

            return ResponseUtil.error(null, HttpStatus.NOT_FOUND.value(), MessageUtils.MSG_USERID_NOT_FOUND);
        } catch (PasswordMismatchException e) {
            log.info("login failed : ", e);

            return ResponseUtil.error(null, HttpStatus.BAD_REQUEST.value(), MessageUtils.MSG_USERPW_NOT_MATCH);
        } catch (Exception e) {
            log.error("unexpected error in login: ", e);

            return ResponseUtil.error(null, HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }
    }




}
