package com.bounce.watergram.like.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(LikeId.class)
@Table(name="`like`")
@Entity
public class Like {

    @Id
    private long userId;
    @Id
    private long postId;
    @CreationTimestamp
    @Column(updatable = false) // 좋아요 생성 시간 변경 여부 - false,  변경 불가
    private LocalDateTime createdAt;

}
