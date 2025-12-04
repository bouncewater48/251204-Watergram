package com.bounce.watergram;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/user")
@RestController
public class UserRestController {

    public Map<String, String> join(
        @RequestParam String loginId
        ,@RequestParam String password
        ,@RequestParam String name
         ,@RequestParam String email
    )

}
