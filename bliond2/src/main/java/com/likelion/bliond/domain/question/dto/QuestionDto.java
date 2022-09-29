package com.likelion.bliond.domain.question.dto;

import com.likelion.bliond.domain.event.entity.Event;
import com.likelion.bliond.domain.member.dto.MemberDto;
import com.likelion.bliond.domain.member.entity.Member;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto {
    private Long id;
    private String content;
    private MemberDto memberDto;
}
