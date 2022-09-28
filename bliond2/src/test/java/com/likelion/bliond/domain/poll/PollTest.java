package com.likelion.bliond.domain.poll;

import com.likelion.bliond.base.TestService;
import com.likelion.bliond.domain.event.entity.Event;
import com.likelion.bliond.domain.member.entity.Member;
import com.likelion.bliond.domain.member.repository.MemberRepository;
import com.likelion.bliond.domain.poll.entity.Poll;
import com.likelion.bliond.domain.poll.entity.PollChoice;
import com.likelion.bliond.domain.poll.repository.PollRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

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

    Member member;
    @BeforeAll
    public void beforeAll() {
        member = memberRepository.findByUsername("KAKAO_12345").get();
    }

    @Test
    void create(){
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

        Poll findPoll = pollRepository.findById(savedPoll.getId()).get();

        Assertions.assertThat(findPoll.getName()).isEqualTo(poll.getName());
        Assertions.assertThat(findPoll.getDescription()).isEqualTo(poll.getDescription());
        Assertions.assertThat(findPoll.getPollChoices().size()).isEqualTo(3);
    }

    /** 22시까지
     * update
     * read(list)
     * delete(poll 삭제)
     * pollChoice 개별 삭제에 대한 건 나중에
     * */
}
