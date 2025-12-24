package com.bounce.watergram.comment.service;

import com.bounce.watergram.comment.domains.Comment;
import com.bounce.watergram.comment.dto.CommentDetail;
import com.bounce.watergram.comment.repository.CommentRepository;
import com.bounce.watergram.user.domain.User;
import com.bounce.watergram.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;

    public boolean createComment(long postId, long userId, String contents) {

        Comment comment = Comment.builder()
                .postId(postId)
                .userId(userId)
                .contents(contents)
                .build();

        try{
            commentRepository.save(comment);
        } catch(DataAccessException e) {
            return false;
        }

        return true;

    }

    public List<CommentDetail> getCommentList(long postId) {

        List<Comment> commentList = commentRepository.findByPostId(postId);

        List<CommentDetail> commentDetailList = new ArrayList<>();
        for(Comment comment:commentList) {

            User user = userService.getUserById(comment.getUserId());

            CommentDetail commentDetail = CommentDetail.builder()
                    .id(comment.getId())
                    .userId(comment.getUserId())
                    .contents(comment.getContents())
                    .loginId(user.getLoginId())
                    .build();

            commentDetailList.add(commentDetail);

        }

        return commentDetailList;
    }

    @Transactional
    public void deleteCommentByPostId(long postId) {
        commentRepository.deleteByPostId(postId);
    }
}
