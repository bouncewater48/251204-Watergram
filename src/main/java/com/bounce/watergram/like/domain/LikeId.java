package com.bounce.watergram.like.domain;


import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class LikeId implements Serializable {

//    도메인의 postId와 userId를 하나로 묶어서 관리
    private long postId;
    private long userId;

}
