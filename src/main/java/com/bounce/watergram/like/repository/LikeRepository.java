package com.bounce.watergram.like.repository;

import com.bounce.watergram.like.domain.Like;
import com.bounce.watergram.like.domain.LikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, LikeId> {
}
