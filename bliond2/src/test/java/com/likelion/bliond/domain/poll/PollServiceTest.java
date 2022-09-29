package com.likelion.bliond.domain.poll;

import com.likelion.bliond.base.TestService;
import com.likelion.bliond.domain.event.entity.Event;
import com.likelion.bliond.domain.event.repository.EventRepository;
import com.likelion.bliond.domain.member.entity.Member;
import com.likelion.bliond.domain.member.repository.MemberRepository;
import com.likelion.bliond.domain.poll.dto.PollDto;
import com.likelion.bliond.domain.poll.repository.PollRepository;
import com.likelion.bliond.domain.poll.service.PollService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PollServiceTest {
    @Autowired
    TestService testService;
    @Autowired
    public MemberRepository memberRepository;

    @Autowired
    public PollRepository pollRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private PollService pollService;

    Member member;
    @BeforeAll
    public void beforeAll() {
        member = memberRepository.findByUsername("KAKAO_12345").get();
    }

    @Test
    @Transactional
    void save(){
        Event event = testService.createEvent(member.getId(),1).get(0);

        pollService.save(
                event.getId(), "만족도 조사","만족도 조사 설명", Arrays.asList("만족", "불만족", "덜만족"));

       Event foundEvent = eventRepository.findById(event.getId()).get();

        Assertions.assertThat(foundEvent.getPolls().size()).isEqualTo(1);
    }

    @Test
    @Rollback(value = false)
    void read(){
        Event event = testService.createEvent(member.getId(),1).get(0);

        pollService.save(
                event.getId(), "만족도 조사1","만족도 조사 설명1", Arrays.asList("만족", "불만족", "덜만족"));
        pollService.save(
                event.getId(), "만족도 조사2","만족도 조사 설명2", Arrays.asList("만족", "불만족", "덜만족"));

        List<PollDto> pollDtos = pollService.getPolls(event.getId());

        Assertions.assertThat(pollDtos.size()).isEqualTo(2);
    }
}
