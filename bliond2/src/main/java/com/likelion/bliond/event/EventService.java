package com.likelion.bliond.event;

import com.likelion.bliond.member.Member;
import com.likelion.bliond.member.MemberRepository;
import com.likelion.bliond.web.ApiException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EventService {
    private final ModelMapper mapper;
    private final EventRepository eventRepository;
    private final MemberRepository memberRepository;
    @Transactional
    public Long save(EventDto eventDto) {
        Event event = mapper.map(eventDto, Event.class);
        Member member = memberRepository.findById(eventDto.getMemberId())
            .orElseThrow(() -> new ApiException("Member not found"));
        event.setMember(member);
        eventRepository.save(event);

        return event.getId();
    }

    public EventDto findById(Long eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new ApiException("존재하지 않는 이벤트입니다."));
        return mapper.map(event, EventDto.class);
    }

    public List<EventDto> getEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map(event -> mapper.map(event, EventDto.class)).toList();
    }

    @Transactional
    public void update(EventDto eventDto) {
        Event event = eventRepository.findById(eventDto.getId()).orElseThrow(() -> new ApiException("존재하지 않는 이벤트입니다."));

        eventRepository.findByIdAndMemberId(event.getId(), eventDto.getMemberId())
            .orElseThrow(() -> new ApiException("이벤트에 대한 수정 권한이 없습니다."));

        mapper.map(eventDto, event);
    }

    @Transactional
    public void deleteById(Long eventId) {
        findById(eventId);
        eventRepository.deleteById(eventId);
    }
}
