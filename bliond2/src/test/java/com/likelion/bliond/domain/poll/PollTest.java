package com.likelion.bliond.domain.poll;

import com.likelion.bliond.base.TestService;
import com.likelion.bliond.domain.event.entity.Event;
import com.likelion.bliond.domain.event.repository.EventRepository;
import com.likelion.bliond.domain.member.entity.Member;
import com.likelion.bliond.domain.member.repository.MemberRepository;
import com.likelion.bliond.domain.poll.entity.Poll;
import com.likelion.bliond.domain.poll.entity.PollChoice;
import com.likelion.bliond.domain.poll.repository.PollRepository;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PollTest {
    @Autowired
    TestService testService;
    @Autowired
    public MemberRepository memberRepository;

    @Autowired
    public PollRepository pollRepository;

    @Autowired
    private EventRepository eventRepository;

    Member member;
    @BeforeAll
    public void beforeAll() {
        member = memberRepository.findByUsername("KAKAO_12345").get();
    }

    @Test
    @Rollback(false)
    void create(){
        Event event = testService.createEvent(member.getId(),1).get(0);

        List<String> pollChoiceNames = new ArrayList<>();
        pollChoiceNames.add("만족");
        pollChoiceNames.add("보통");
        pollChoiceNames.add("불만족");

        event.addPoll("만족도 조사", "만족도 조사 투표입니다.", pollChoiceNames);

        Event findEvent = eventRepository.findById(event.getId()).get();

        Assertions.assertThat(findEvent.getPolls().size()).isEqualTo(1);

    }

    /** 22시까지
     * update
     * read(list)
     * delete(poll 삭제)
     * pollChoice 개별 삭제에 대한 건 나중에
     * */

    @Test
    void update(){
        Event event = testService.createEvent(member.getId(),1).get(0);
        Poll poll = Poll.builder()
                .name("만족도 조사")
                .description("...에 대한 만족도 조사")
                .event(event)
                .isActive(true)
                .build();

        poll.addPollChoice("매우 만족");
        poll.addPollChoice("만족");
        poll.addPollChoice("불만족");

        Poll savedPoll = pollRepository.save(poll);

        savedPoll.setName("만족도 조사 : 하나 고르시오");
        savedPoll.setIsActive(false);

        Poll foundPoll = pollRepository.findById(savedPoll.getId()).get();

        Assertions.assertThat(foundPoll.getName()).isEqualTo(poll.getName());
        Assertions.assertThat(foundPoll.getIsActive()).isEqualTo(false);
    }

    @Test
    void delete(){
        Event event = testService.createEvent(member.getId(),1).get(0);
        Poll poll = Poll.builder()
                .name("만족도 조사")
                .description("...에 대한 만족도 조사")
                .event(event)
                .isActive(true)
                .build();

        poll.addPollChoice("매우 만족");
        poll.addPollChoice("만족");
        poll.addPollChoice("불만족");

        pollRepository.save(poll);

        pollRepository.deleteById(poll.getId());

        Poll deletedPoll = pollRepository.findById(poll.getId()).orElse(null);
        assertThat(deletedPoll).isNull();
    }

    @Test
    void read(){
        Event event = testService.createEvent(member.getId(),1).get(0);
        Poll poll1 = Poll.builder()
                .name("질문1")
                .description("답을 맞추시오1")
                .event(event)
                .isActive(true)
                .build();
        Poll poll2 = Poll.builder()
                .name("질문2")
                .description("답을 맞추시오2")
                .event(event)
                .isActive(true)
                .build();

        pollRepository.save(poll1);
        pollRepository.save(poll2);

        List<Poll> polls = pollRepository.findAll();
        assertThat(polls.size()).isEqualTo(2);

    }
}
