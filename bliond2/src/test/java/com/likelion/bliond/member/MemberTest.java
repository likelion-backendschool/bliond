package com.likelion.bliond.member;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class MemberTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void create() {
        Member member = Member.builder()
            .authType(AuthType.KAKAO)
            .authKey("1234")
            .username(AuthType.KAKAO + "1234")
            .nickname("user1")
            .role(Role.ROLE_USER)
            .build();
        memberRepository.save(member);
        Member memberFindById = memberRepository.findById(member.getId()).orElse(null);

        assertThat(memberFindById).isNotNull();
    }

    @Test
    void read() {
        Member member1 = Member.builder()
            .authType(AuthType.KAKAO)
            .authKey("1234")
            .username(AuthType.KAKAO + "1234")
            .nickname("user1")
            .role(Role.ROLE_USER)
            .build();
        Member member2 = Member.builder()
            .authType(AuthType.KAKAO)
            .authKey("4321")
            .username(AuthType.KAKAO + "4321")
            .nickname("user2")
            .role(Role.ROLE_ADMIN)
            .build();
        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Member> members = memberRepository.findAll();
        assertThat(members.size()).isGreaterThan(2);
    }

    @Test
    void update() {
        Member member = Member.builder()
            .authType(AuthType.KAKAO)
            .authKey("1234")
            .username(AuthType.KAKAO + "1234")
            .nickname("user1")
            .role(Role.ROLE_USER)
            .build();
        memberRepository.save(member);
        Member memberFindById = memberRepository.findById(member.getId()).orElse(null);
        memberFindById.setNickname("test2");
        assertThat(memberFindById.getNickname()).isEqualTo("test2");
    }

    @Test
    void delete() {
        Member member = Member.builder()
            .authType(AuthType.KAKAO)
            .authKey("1234")
            .username(AuthType.KAKAO + "1234")
            .nickname("user1")
            .role(Role.ROLE_USER)
            .build();
        memberRepository.save(member);
        Member memberFindById = memberRepository.findById(member.getId()).orElse(null);
        memberRepository.delete(memberFindById);
        Member memberFindById2 = memberRepository.findById(member.getId()).orElse(null);
        assertThat(memberFindById2).isNull();
    }
}
