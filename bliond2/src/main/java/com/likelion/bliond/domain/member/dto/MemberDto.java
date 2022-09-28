package com.likelion.bliond.domain.member.dto;

import com.likelion.bliond.domain.member.entity.AuthType;
import com.likelion.bliond.domain.member.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {
    private Long id;
    private AuthType authType; // KAKAO
    private String authKey;
    private String username;
    private String nickname;
    private Role role;
    private String accessToken;
    private String eventNickname;
}
