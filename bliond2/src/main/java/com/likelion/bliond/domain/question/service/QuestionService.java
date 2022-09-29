package com.likelion.bliond.domain.question.service;

import com.likelion.bliond.domain.event.entity.Event;
import com.likelion.bliond.domain.event.repository.EventRepository;
import com.likelion.bliond.domain.member.entity.Member;
import com.likelion.bliond.domain.member.exception.MemberNotFoundException;
import com.likelion.bliond.domain.member.repository.MemberRepository;
import com.likelion.bliond.domain.question.entity.Question;
import com.likelion.bliond.domain.question.exception.EventNotFoundException;
import com.likelion.bliond.domain.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final EventRepository eventRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long create(Long eventId, String content, Long memberId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(EventNotFoundException::new);

        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);

        Question question = Question.builder()
                .content(content)
                .event(event)
                .member(member)
                .build();
        Question savedQuestion = questionRepository.save(question);

        return savedQuestion.getId();
    }
}
