package com.likelion.bliond.web.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EventVo {
    private Long id;
    private String title;
    private String description;
    private Boolean isPrivate;
    private String endDateTime;
    private String createdDate;
    private String modifiedDate;
    private Long memberId;
    private String username;
    private String nickname;
}
