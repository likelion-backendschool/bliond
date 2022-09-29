package com.likelion.bliond.domain.question;

import com.likelion.bliond.base.TestService;
import com.likelion.bliond.domain.event.repository.EventRepository;
import com.likelion.bliond.domain.member.entity.Member;
import com.likelion.bliond.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class QuestionServiceTest {
    @Autowired
    TestService testService;
    @Autowired
    EventRepository eventRepository;

    @Autowired
    public MemberRepository memberRepository;

    Member member;
    @BeforeAll
    public void beforeAll() {
        member = memberRepository.findByUsername("KAKAO_12345").get();
    }

    @Test
    void create() {

    }
}
