package com.likelion.bliond.web.question;

import com.likelion.bliond.domain.member.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionVo {
    private Long id;
    private String content;
    private Long memberId;
    private String username;
    private String nickname;
    private Role role;
    private String eventNickname;
}
