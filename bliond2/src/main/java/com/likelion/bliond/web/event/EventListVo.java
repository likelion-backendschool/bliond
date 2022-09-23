package com.likelion.bliond.web.event;

import com.likelion.bliond.member.MemberDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EventListVo {
    private Long id;
    private String title;
    private String description;
    private Boolean isPrivate;
    private String endDateTime;
    private String createdDate;
    private String modifiedDate;
    private MemberDto member;
}
