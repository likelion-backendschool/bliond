package com.likelion.bliond.domain.event;

import static org.assertj.core.api.Assertions.assertThat;

import com.likelion.bliond.domain.event.entity.Event;
import com.likelion.bliond.domain.event.repository.EventRepository;
import com.likelion.bliond.domain.member.entity.Member;
import com.likelion.bliond.domain.member.repository.MemberRepository;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Transactional
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class EventTest {

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
        Event event = Event.builder()
            .endDateTime(LocalDateTime.now().plus(3, ChronoUnit.HOURS))
            .title("test")
            .description("test")
            .isPrivate(true)
            .member(member)
            .build();
        eventRepository.save(event);

        Event foundEvent = eventRepository.findById(event.getId()).orElse(null);
        assertThat(foundEvent.getCreatedDate()).isNotNull();
        assertThat(foundEvent.getEndDateTime()).isNotNull();
    }

    @Test
    void read() {
        Event event1 = Event.builder()
            .endDateTime(LocalDateTime.now().plus(3, ChronoUnit.HOURS))
            .title("test")
            .description("test")
            .isPrivate(false)
            .member(member)
            .build();
        Event event2 = Event.builder()
            .endDateTime(LocalDateTime.now().plus(3, ChronoUnit.HOURS))
            .title("test2")
            .description("test2")
            .isPrivate(true)
            .member(member)
            .build();

        eventRepository.save(event1);
        eventRepository.save(event2);

        List<Event> events = eventRepository.findAll();
        assertThat(events.size()).isEqualTo(2);
    }

    @Test
    void update() {
        Event event = Event.builder()
            .endDateTime(LocalDateTime.now().plus(3, ChronoUnit.HOURS))
            .title("test")
            .description("test")
            .isPrivate(false)
            .member(member)
            .build();
        Event savedEvent = eventRepository.save(event);

        savedEvent.setIsPrivate(true);

        Event findEvent = eventRepository.findById(event.getId()).get();
        assertThat(findEvent.getIsPrivate()).isEqualTo(savedEvent.getIsPrivate());
        assertThat(findEvent.getModifiedDate()).isEqualToIgnoringNanos(event.getModifiedDate());
    }

    @Test
    void delete() {
        Event event = Event.builder()
            .endDateTime(LocalDateTime.now().plus(3, ChronoUnit.HOURS))
            .title("test")
            .description("test")
            .isPrivate(false)
            .member(member)
            .build();
        eventRepository.save(event);

        eventRepository.deleteById(event.getId());

        Event deletedEvent = eventRepository.findById(event.getId()).orElse(null);
        assertThat(deletedEvent).isNull();
    }
}
