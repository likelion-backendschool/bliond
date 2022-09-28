package com.likelion.bliond.domain.poll.entity;

import com.likelion.bliond.base.BaseEntity;
import com.likelion.bliond.domain.event.entity.Event;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@SuperBuilder
@NoArgsConstructor
@Getter
public class Poll extends BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Event event;
    private String name;
    private Boolean isActive;
    private String description;

    @Builder.Default
    @OneToMany(mappedBy = "poll", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<PollChoice> pollChoices = new ArrayList<>();

    public void addPollChoice(String name){
        PollChoice pollChoice = PollChoice.builder()
                .name(name)
                .poll(this)
                .build();
        this.pollChoices.add(pollChoice);
    }
}
