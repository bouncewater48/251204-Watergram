package com.bounce.watergram.user.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class User {

    private long id;
    private String login_id;
    private String password;
    private String name;
    private String email;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

}
