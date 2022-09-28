package com.likelion.bliond.web.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EventMemberVo {

    private Long id;
    private String username;
    private String nickname;
    private String eventNickname;
}
