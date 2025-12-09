package com.bounce.watergram;

import com.bounce.watergram.domain.User;
import com.bounce.watergram.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/user")
@RestController
public class UserRestController {


    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/join-process")
    public Map<String, String> join(
        @RequestParam String loginId
        ,@RequestParam String password
        ,@RequestParam String name
         ,@RequestParam String email) {

        Map<String, String> resultMap = new HashMap<>();

        if(userService.createUser(loginId, password, name, email)) {
            resultMap.put("result", "success");
        } else {
            resultMap.put("result", "fail");
        }

        return resultMap;

    }

    @GetMapping("/duplicate-id")
    public Map<String, Boolean> isDuplicateId(@RequestParam String loginId) {

        Map<String, Boolean> resultMap = new HashMap<>();

        if(userService.isDuplicateId(loginId)) {
            resultMap.put("isDuplicate", true);
        } else {
            resultMap.put("isDuplicate", false);
        }

        return resultMap;

    }

    @PostMapping("login-process")
    public Map<String, String> login(
            @RequestParam String loginId
            , @RequestParam String password
            , HttpServletRequest request) {

        User user = userService.getUser(loginId, password);

        Map<String, String> resultMap = new HashMap<>();

        if (user != null) {
            resultMap.put("result", "success");
            HttpSession session = request.getSession();

            session.setAttribute("userId", user.getId());
            session.setAttribute("userLoginId", user.getLogin_id());

        } else {
            resultMap.put("result", "fail");
        }
        return resultMap;
    }

}
