package com.likelion.bliond.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private Long id;
    private AuthType authType;
    private String authKey;
    private String username;
    private Role role;
}
