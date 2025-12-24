package com.bounce.watergram.like.domain;


import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LikeId {

//    도메인의 postId와 userId를 하나로 묶어서 관리
    private long postId;
    private long userId;

}
