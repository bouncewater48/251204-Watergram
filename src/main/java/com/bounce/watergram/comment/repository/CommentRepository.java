package com.bounce.watergram.comment.repository;

import com.bounce.watergram.comment.domains.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
