package com.bounce.watergram.post;

import com.bounce.watergram.post.dto.PostDetail;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/post")
@Controller
public class PostController {

    @GetMapping("/timeline")
    public String timeline() {

        List<PostDetail> postDetailList = new ArrayList<>();

        return "watergram/post/timeLine";
    }

}
