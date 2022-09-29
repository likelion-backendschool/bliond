package com.likelion.bliond.domain.question.service;

import com.likelion.bliond.domain.event.entity.Event;
import com.likelion.bliond.domain.event.repository.EventRepository;
import com.likelion.bliond.domain.member.entity.Member;
import com.likelion.bliond.domain.member.exception.MemberNotFoundException;
import com.likelion.bliond.domain.member.repository.MemberRepository;
import com.likelion.bliond.domain.question.dto.QuestionDto;
import com.likelion.bliond.domain.question.entity.Question;
import com.likelion.bliond.domain.question.exception.EventNotFoundException;
import com.likelion.bliond.domain.question.repository.QuestionRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
