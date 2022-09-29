package com.likelion.bliond.domain.poll.service;

import com.likelion.bliond.domain.event.dto.EventDto;
import com.likelion.bliond.domain.event.entity.Event;
import com.likelion.bliond.domain.event.repository.EventRepository;
import com.likelion.bliond.domain.member.entity.Member;
import com.likelion.bliond.domain.member.repository.MemberRepository;
import com.likelion.bliond.domain.poll.dto.PollDto;
import com.likelion.bliond.domain.poll.entity.Poll;
import com.likelion.bliond.domain.poll.repository.PollRepository;
import com.likelion.bliond.web.response.ApiException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PollService {
    private final ModelMapper mapper;
    private final EventRepository eventRepository;
    private final PollRepository pollRepository;


    @Transactional
    public void save(Long eventId,String name, String description, List<String>pollChoices) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ApiException("Event not found"));
        event.addPoll(name, description,pollChoices);
    }

    public PollDto findById(Long pollId) {
        Poll poll = pollRepository.findById(pollId).orElseThrow(() -> new ApiException("존재하지 않는 투표입니다."));
        return mapper.map(poll, PollDto.class);
    }

    public List<PollDto> getPolls(Long eventId){
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ApiException("Event not found"));
        return event.getPolls().stream().map(poll -> mapper.map(poll, PollDto.class)).toList();
    }
}
