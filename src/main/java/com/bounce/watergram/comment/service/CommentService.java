package com.bounce.watergram.comment.service;

import com.bounce.watergram.comment.domains.Comment;
import com.bounce.watergram.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

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
}
