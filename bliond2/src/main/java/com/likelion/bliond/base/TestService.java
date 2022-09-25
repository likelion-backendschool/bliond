package com.likelion.bliond.base;

import static com.likelion.bliond.member.AuthType.KAKAO;

import com.likelion.bliond.member.Member;
import com.likelion.bliond.member.MemberRepository;
import com.likelion.bliond.member.Role;
import com.likelion.bliond.security.JwtDto;
import com.likelion.bliond.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TestService {
    private final MemberRepository memberRepository;
    private final TokenService tokenService;

    public void createUser(String userUsername, String userNickname, Role role, String userAuthKey) {
        Member user = Member.builder()
            .authType(KAKAO)
            .authKey(userAuthKey)
            .username(userUsername)
            .role(role)
            .nickname(userNickname)
            .build();
        Member member = memberRepository.save(user);
        JwtDto jwtDto = tokenService.generateTokenTest(userUsername, member.getId().toString(),
            String.valueOf(role), userNickname);
        member.setAccessToken(jwtDto.getAccessToken());
    }
}
