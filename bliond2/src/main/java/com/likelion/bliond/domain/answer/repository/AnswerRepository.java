package com.likelion.bliond.domain.answer.repository;

import com.likelion.bliond.domain.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
