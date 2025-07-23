package otter.sherry.ottershell.user;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/signUp")
    @Operation(summary = "회원가입",description = "회원가입하는 API")
    // swagger 관련 API : “이 API는 회원가입용이야. Swagger 문서에 그렇게 표시해줘!”
    public UserEntity signUp(@RequestBody UserEntity userEntity){
        return userService.signUp(userEntity);
    }

    @GetMapping("/myPage")
    @Operation(summary = "마이페이지", description = "마이페이지(정보 조회)하는 API")
    public UserEntity myPage(@AuthenticationPrincipal UserEntity userEntity){
        return userEntity;
    }

}
