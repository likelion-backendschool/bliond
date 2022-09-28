package com.likelion.bliond.domain.poll.repository;

import com.likelion.bliond.domain.poll.entity.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollRepository extends JpaRepository <Poll, Long> {
}
