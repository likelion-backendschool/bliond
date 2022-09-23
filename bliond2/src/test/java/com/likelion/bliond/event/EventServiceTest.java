package com.likelion.bliond.event;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import com.likelion.bliond.member.Member;
import com.likelion.bliond.member.MemberRepository;
import com.likelion.bliond.web.ApiException;
import java.time.LocalDateTime;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public class EventServiceTest {

    @Autowired
    private EventService eventService;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void create() {
        // given
        Member member = Member.builder()
            .username("test").build();
        Member member1 = memberRepository.save(member);

        EventDto eventDto = new EventDto();
        eventDto.setTitle("test");
        eventDto.setDescription("testDescription");
        eventDto.setEndDateTime(LocalDateTime.now().plusDays(1));
        eventDto.setIsPrivate(true);
        eventDto.setMemberId(member1.getId());
        // when
        Long eventId = eventService.save(eventDto);

        // then
        EventDto foundEventDto = eventService.findById(eventId);
        assertThat(foundEventDto.getTitle()).isEqualTo(eventDto.getTitle());
    }

    @Test
    public void read() {
        // given
        EventDto eventDto1 = new EventDto();
        eventDto1.setTitle("test1");
        eventDto1.setDescription("testDescription1");
        eventDto1.setEndDateTime(LocalDateTime.now().plusDays(1));
        eventDto1.setIsPrivate(true);

        EventDto eventDto2 = new EventDto();
        eventDto2.setTitle("test2");
        eventDto2.setDescription("testDescription2");
        eventDto2.setEndDateTime(LocalDateTime.now().plusDays(2));
        eventDto2.setIsPrivate(false);

        eventService.save(eventDto1);
        eventService.save(eventDto2);

        // when
        List<EventDto> eventDtos = eventService.getEvents();
        assertThat(eventDtos.size()).isEqualTo(2);
    }

    @Test
    public void update() {
        // given
        EventDto eventDto = new EventDto();
        eventDto.setTitle("test");
        eventDto.setDescription("testDescription");
        eventDto.setEndDateTime(LocalDateTime.now().plusDays(1));
        eventDto.setIsPrivate(true);

        Long eventId = eventService.save(eventDto);

        // when
        EventDto findEventDto = eventService.findById(eventId);
        findEventDto.setTitle("updateTitle");
        eventService.update(findEventDto);

        // then
        EventDto updatedEventDto = eventService.findById(eventId);
        assertThat(updatedEventDto.getTitle()).isEqualTo(findEventDto.getTitle());
    }

    @Test
    public void delete() {
        // given
        EventDto eventDto = new EventDto();
        eventDto.setTitle("test");
        eventDto.setDescription("testDescription");
        eventDto.setEndDateTime(LocalDateTime.now().plusDays(1));
        eventDto.setIsPrivate(true);


        Long eventId = eventService.save(eventDto);

        // when
        eventService.deleteById(eventId);

        // then
        assertThatExceptionOfType(ApiException.class)
            .isThrownBy(() -> eventService.findById(eventId))
            .withMessageMatching("존재하지 않는 이벤트입니다.");
    }
}
