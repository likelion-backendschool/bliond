package com.likelion.bliond.domain.question.service;

import com.likelion.bliond.domain.event.entity.*;
import com.likelion.bliond.domain.event.repository.*;
import com.likelion.bliond.domain.member.entity.*;
import com.likelion.bliond.domain.member.exception.*;
import com.likelion.bliond.domain.member.repository.*;
import com.likelion.bliond.domain.question.dto.*;
import com.likelion.bliond.domain.question.entity.*;
import com.likelion.bliond.domain.question.exception.*;
import com.likelion.bliond.domain.question.repository.*;
import java.util.*;
import lombok.*;
import org.modelmapper.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final EventRepository eventRepository;
    private final MemberRepository memberRepository;
    private final ModelMapper mapper;

    @Transactional
    public void create(Long eventId, String content, Long memberId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(EventNotFoundException::new);

        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);

        event.addQuestion(content, member);
    }

    public List<QuestionDto> findAllByEventId(Long eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(EventNotFoundException::new);

        List<Question> questions = event.getQuestions();

        return questions.stream().map(question -> mapper.map(question, QuestionDto.class)).toList();
    }
}
