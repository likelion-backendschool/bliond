package com.likelion.bliond.domain.question.repository;

import com.likelion.bliond.domain.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {


    List<Question> findByEventIdAndMemberId(Long eventId, Long memberId);
}