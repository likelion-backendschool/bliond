package com.likelion.bliond.domain.question.repository;

import com.likelion.bliond.domain.question.entity.Question;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {


    List<Question> findByEventIdAndMemberId(Long eventId, Long memberId);
}
