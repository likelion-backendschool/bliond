package com.likelion.bliond.domain.member;

import static org.assertj.core.api.Assertions.assertThat;

import com.likelion.bliond.domain.member.dto.MemberDto;
import com.likelion.bliond.domain.member.entity.AuthType;
import com.likelion.bliond.domain.member.entity.Member;
import com.likelion.bliond.domain.member.entity.Role;
import com.likelion.bliond.domain.member.repository.MemberRepository;
import com.likelion.bliond.domain.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    void findByUsername() {
        Member member = Member.builder()
            .authType(AuthType.KAKAO)
            .authKey("1234")
            .username(AuthType.KAKAO + "1234")
            .nickname("user1")
            .role(Role.ROLE_USER)
            .build();
        memberRepository.save(member);

        MemberDto memberDto = memberService.findByUsername(member.getUsername());
        assertThat(memberDto).isNotNull();
    }
}
