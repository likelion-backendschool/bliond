package com.likelion.bliond.domain.question;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.*;

import com.likelion.bliond.base.*;
import com.likelion.bliond.domain.event.entity.*;
import com.likelion.bliond.domain.event.repository.*;
import com.likelion.bliond.domain.member.entity.*;
import com.likelion.bliond.domain.member.repository.*;
import com.likelion.bliond.domain.question.dto.*;
import com.likelion.bliond.domain.question.service.*;
import java.util.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

@SpringBootTest
@TestInstance(PER_CLASS)
public class QuestionServiceTest {
    @Autowired
    TestService testService;
    @Autowired
    EventRepository eventRepository;

    @Autowired
    public MemberRepository memberRepository;

    @Autowired
    private QuestionService questionService;

    Member member;

    @BeforeAll
    public void beforeAll() {
        member = memberRepository.findByUsername("KAKAO_12345").get();
    }

    @Test
    void create() {
        Member member1 = memberRepository.findByUsername("KAKAO_23456").get();
        Event event = testService.createEvent(member.getId(), 1).get(0);

        questionService.create(event.getId(), "test", member1.getId());
        List<QuestionDto> questionDtos = questionService.findAllByEventId(event.getId());
        assertThat(questionDtos.size()).isEqualTo(1);
    }

    @Test
    void read() {
        Member member1 = memberRepository.findByUsername("KAKAO_23456").get();
        Event event = testService.createEvent(member.getId(), 1).get(0);

        questionService.create(event.getId(), "궁금합니다", member1.getId());
        questionService.create(event.getId(), "궁금합니다", member1.getId());

        List<QuestionDto> questionDtos = questionService.findAllByEventId(event.getId());

        assertThat(questionDtos.size()).isEqualTo(2);
    }
}
