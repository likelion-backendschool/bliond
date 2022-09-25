package com.likelion.bliond.event;

import com.likelion.bliond.member.MemberDto;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {
    private Long id;
    private LocalDateTime endDateTime;
    private String title;
    private String description;
    private Boolean isPrivate;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private MemberDto memberDto;
    private Long memberId;
}
