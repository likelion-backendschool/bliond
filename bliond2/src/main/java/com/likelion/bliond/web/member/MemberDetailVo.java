package com.likelion.bliond.web.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDetailVo {
    private Long id;
    private String username;
    private String nickname;
}
