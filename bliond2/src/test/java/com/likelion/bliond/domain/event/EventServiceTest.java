package com.likelion.bliond.domain.event;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import com.likelion.bliond.base.TestService;
import com.likelion.bliond.domain.event.dto.EventDto;
import com.likelion.bliond.domain.event.entity.Event;
import com.likelion.bliond.domain.event.service.EventService;
import com.likelion.bliond.domain.member.entity.Member;
import com.likelion.bliond.domain.member.repository.MemberRepository;
import com.likelion.bliond.web.response.ApiException;
import java.time.LocalDateTime;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
@TestInstance(Lifecycle.PER_CLASS)
public class EventServiceTest {

    @Autowired
    private EventService eventService;

    @Autowired
    TestService testService;
    @Autowired
    private MemberRepository memberRepository;

    Member member;

    @BeforeAll
    public void beforeAll() {
        member = memberRepository.findByUsername("KAKAO_12345").get();
    }

    @Test
    public void create() {
        // given

        EventDto eventDto = new EventDto();
        eventDto.setTitle("test");
        eventDto.setDescription("testDescription");
        eventDto.setEndDateTime(LocalDateTime.now().plusDays(1));
        eventDto.setIsPrivate(true);
        eventDto.setMemberId(member.getId());

        // when
        Long eventId = eventService.save(eventDto);

        // then
        EventDto foundEventDto = eventService.findById(eventId);
        assertThat(foundEventDto.getTitle()).isEqualTo(eventDto.getTitle());
    }

    @Test
    public void read() {
        // given
        testService.createEvent(member.getId(), 2);

        // when
        List<EventDto> eventDtos = eventService.getEvents();
        assertThat(eventDtos.size()).isEqualTo(2);
    }

    @Test
    public void update() {
        // given
        Event event = testService.createEvent(member.getId(), 1).get(0);

        // when
        EventDto findEventDto = eventService.findById(event.getId());
        findEventDto.setTitle("updateTitle");
        eventService.update(findEventDto);

        // then
        EventDto updatedEventDto = eventService.findById(event.getId());
        assertThat(updatedEventDto.getTitle()).isEqualTo(findEventDto.getTitle());
    }

    @Test
    public void delete() {
        // given
        Event event = testService.createEvent(member.getId(), 1).get(0);

        // when
        eventService.deleteById(event.getId());

        // then
        assertThatExceptionOfType(ApiException.class)
            .isThrownBy(() -> eventService.findById(event.getId()))
            .withMessageMatching("존재하지 않는 이벤트입니다.");
    }
}
