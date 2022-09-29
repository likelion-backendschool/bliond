package com.likelion.bliond.domain.question;

import com.likelion.bliond.base.TestService;
import com.likelion.bliond.domain.event.entity.Event;
import com.likelion.bliond.domain.event.repository.EventRepository;
import com.likelion.bliond.domain.member.entity.Member;
import com.likelion.bliond.domain.member.repository.MemberRepository;
import com.likelion.bliond.domain.question.entity.Question;
import com.likelion.bliond.domain.question.repository.QuestionRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import java.util.List;

import static com.likelion.bliond.domain.member.entity.AuthType.KAKAO;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.*;

@Transactional
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class QuestionTest {

    @Autowired
    TestService testService;

    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    public MemberRepository memberRepository;

    @Autowired
    private EventRepository eventRepository;

    Member member;
    @BeforeAll
    public void beforeAll() {
        member = memberRepository.findByUsername("KAKAO_12345").get();
    }

    @Test
    void create(){
        Member member1 = memberRepository.findByUsername("KAKAO_23456").get();
        Event event = testService.createEvent(member.getId(), 1).get(0);

        event.addQuestion("궁금합니다.", member1);

        assertThat(event.getQuestions().size()).isEqualTo(1);
    }

    @Test
    void update(){
        Member member1 = memberRepository.findByUsername("KAKAO_23456").get();
        Event event = testService.createEvent(member.getId(), 1).get(0);

        event.addQuestion("궁금합니다.", member1);

        Event findEvent = eventRepository.findById(event.getId()).get();

        Question question = findEvent.getQuestions().get(0);
        question.setContent("이게 맞나요?");

        List<Question> questions = questionRepository.findByEventIdAndMemberId(event.getId(), member1.getId());
        Question findQuestion = questions.get(0);

        assertThat(findQuestion.getContent()).isEqualTo(question.getContent());
    }

    @Test
    @Rollback(value = false)
    void delete(){
        Member member1 = memberRepository.findByUsername("KAKAO_23456").get();
        Event event = testService.createEvent(member.getId(), 1).get(0);

        event.addQuestion("궁금합니다.", member1);

        List<Question> questions = questionRepository.findByEventIdAndMemberId(event.getId(), member1.getId());
        Question findQuestion = questions.get(0);

        event.removeQuestion(findQuestion);

        assertThat(event.getQuestions().size()).isEqualTo(0);
    }
}
