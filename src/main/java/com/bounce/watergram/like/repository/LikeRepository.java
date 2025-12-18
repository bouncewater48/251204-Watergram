package com.bounce.watergram.like.repository;

import com.bounce.watergram.like.domain.Like;
import com.bounce.watergram.like.domain.LikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, LikeId> {

    // SELECT COUNT(*) FROM `like` WHERE `post_id` = 2;
    public int countByPostId(long postId);
    // WHERE `post_id` = #{} AND `user_id` = #{}
    // 존재한다/안한다 여부 확인이므로 boolean
    public boolean existsByPostIdAndUserId(long postId, long userId);

}
