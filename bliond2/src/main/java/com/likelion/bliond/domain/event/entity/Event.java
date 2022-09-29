package com.likelion.bliond.domain.event.entity;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import com.likelion.bliond.base.BaseEntity;
import com.likelion.bliond.base.BooleanToYNConverter;
import com.likelion.bliond.domain.member.entity.Member;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.likelion.bliond.domain.poll.entity.Poll;
import com.likelion.bliond.domain.question.entity.Question;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Event extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private LocalDateTime endDateTime;

    private String title;
    private String description;

    @Convert(converter = BooleanToYNConverter.class)
    private Boolean isPrivate;

    @ManyToOne(fetch = LAZY)
    private Member member;

    @OneToMany(mappedBy = "event")
    private List<Question> questions;

    @Builder.Default
    @OneToMany(mappedBy = "event", cascade = PERSIST, orphanRemoval = true)
    private List<Poll> polls = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "event", cascade = PERSIST, orphanRemoval = true)
    private Set<EventMember> eventMembers = new HashSet<>();

    public void participate(Member member) {
        EventMember eventMember = EventMember.builder()
            .event(this)
            .member(member)
            .build();
        this.eventMembers.add(eventMember);
    }

    public void leave(Member member) {
        EventMember eventMember = EventMember.builder()
            .event(this)
            .member(member)
            .build();
        this.eventMembers.remove(eventMember);
    }

    public void addPoll(String name, String description, List<String> pollChoiceNames) {
        Poll poll = Poll.builder()
                .name(name)
                .description(description)
                .event(this)
                .isActive(true)
                .build();

        pollChoiceNames.forEach(poll::addPollChoice);

        this.polls.add(poll);
    }
}
