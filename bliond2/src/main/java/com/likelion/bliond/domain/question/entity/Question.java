package com.likelion.bliond.domain.question.entity;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import com.likelion.bliond.base.BaseEntity;
import com.likelion.bliond.domain.event.entity.Event;
import com.likelion.bliond.domain.member.entity.Member;

import javax.persistence.*;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class Question extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Lob
    private String content;

    /**
     * NOTICE
     * eventList는 채팅방 리스트
     * event는 채팅방
     * question은 채팅
     */
    @ManyToOne(fetch = LAZY)
    private Event event;

    @ManyToOne(fetch = LAZY)
    private Member member;
    @Builder.Default
    @ManyToMany
    Set<Member> likeCount = new HashSet<>();

}
