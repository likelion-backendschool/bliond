package com.likelion.bliond.domain.question.dto;

import com.likelion.bliond.domain.member.dto.MemberDto;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto {
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private Long id;
    private String content;
    private MemberDto memberDto;
}
