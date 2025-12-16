package com.bounce.watergram.watergram;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WatergramController {

    @GetMapping("/main")
    public String mainContents() {
        return "watergram/main";
    }

    @PostMapping("/write")
    public String post() {
        return "post/write";
    }

}
