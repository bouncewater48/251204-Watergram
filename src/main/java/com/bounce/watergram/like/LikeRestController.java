package com.bounce.watergram.like;

import com.bounce.watergram.like.domain.Like;
import com.bounce.watergram.like.service.LikeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LikeRestController {

    private final LikeService likeService;

    public LikeRestController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/post/like")
    public Map<String, String> like(
            @RequestParam long postId
            , HttpSession session) {

        long userId = (Long)session.getAttribute("userId");

        Map<String, String> resultMap = new HashMap<>();
        if(likeService.createLike(postId, userId)) {
            resultMap.put("result", "success");
        } else {
            resultMap.put("result", "fail");
        }

        return resultMap;

    }
}
