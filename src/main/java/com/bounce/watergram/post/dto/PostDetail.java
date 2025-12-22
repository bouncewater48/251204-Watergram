package com.bounce.watergram.post.dto;

import com.bounce.watergram.comment.dto.CommentDetail;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class PostDetail {

    private long id;

    private String contents;
    private String imagePath;

    private long userId;
    private String loginId;

    private int likeCount;
    private boolean isLike;
    
    // 댓글 목록
    // 댓글 작성된 리스트 필요

    List<CommentDetail> commentList;

}
