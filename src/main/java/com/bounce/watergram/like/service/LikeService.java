package com.bounce.watergram.like.service;

import com.bounce.watergram.like.domain.Like;
import com.bounce.watergram.like.repository.LikeRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    private final LikeRepository likeRepository;

    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

//    좋아요 눌렀는지 확인
    public boolean createLike(long postId, long userId) {

        Like like = Like.builder()
                .postId(postId)
                .userId(userId)
                .build();

        try {
            likeRepository.save(like);
        } catch (DataAccessException e) {
            return false;
        }

        return true;

    }

}
