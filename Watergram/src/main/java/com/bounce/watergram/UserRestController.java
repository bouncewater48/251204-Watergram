package com.bounce.watergram;

import com.bounce.watergram.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/user")
@RestController
public class UserRestController {

    @Autowired
    private UserService userService;

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

}
