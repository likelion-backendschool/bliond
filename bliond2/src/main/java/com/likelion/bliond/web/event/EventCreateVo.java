package com.likelion.bliond.web.event;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventCreateVo {
    private Long id;
    private String title;
    private String description;
    private Boolean isPrivate;
    private LocalDateTime endDateTime;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private Long memberId;
    private String username;
    private String nickname;
}