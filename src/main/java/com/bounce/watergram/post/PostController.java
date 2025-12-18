package com.bounce.watergram.post;

import com.bounce.watergram.post.dto.PostDetail;
import com.bounce.watergram.post.service.PostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/post")
@Controller
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/timeline")
    public String timeline(
            Model model
            , HttpSession session) {

//       로그인할때 지정한 PK값(userId)
        Long userId = (Long)session.getAttribute("userId");
        List<PostDetail> postList = postService.getPostList(userId);

        if (userId == null) {
            return "redirect:/user/login";
        }

        model.addAttribute("postList", postList);

        return "post/timeline";
    }

}
