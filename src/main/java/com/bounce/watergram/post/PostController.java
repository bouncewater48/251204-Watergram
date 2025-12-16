package com.bounce.watergram.post;

import com.bounce.watergram.post.dto.PostDetail;
import com.bounce.watergram.post.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/post")
@Controller
public class PostController {

    private PostService postService;

    @GetMapping("/timeline")
    public String timeline() {

        List<PostDetail> postDetailList = postService.getPostList();

        return "post/timeLine";
    }

}
