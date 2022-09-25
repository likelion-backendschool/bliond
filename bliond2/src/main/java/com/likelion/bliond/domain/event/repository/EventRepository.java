package com.likelion.bliond.domain.event.repository;

import com.likelion.bliond.domain.event.entity.Event;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

    Optional<Event> findByIdAndMemberId(Long id, Long memberId);
}
