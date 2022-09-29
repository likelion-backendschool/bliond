package com.likelion.bliond.domain.question;

import com.likelion.bliond.base.TestService;
import com.likelion.bliond.domain.event.entity.Event;
import com.likelion.bliond.domain.event.repository.EventRepository;
import com.likelion.bliond.domain.member.entity.Member;
import com.likelion.bliond.domain.member.repository.MemberRepository;
import com.likelion.bliond.domain.question.dto.QuestionDto;
import com.likelion.bliond.domain.question.service.QuestionService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

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
