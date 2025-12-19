package com.bounce.watergram.comment.repository;

import com.bounce.watergram.comment.domains.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    // WHERE `post_id` = #{}
    public List<Comment> findByPostId(long postId);

}
