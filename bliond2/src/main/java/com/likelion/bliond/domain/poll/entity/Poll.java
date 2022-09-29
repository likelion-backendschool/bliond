package com.likelion.bliond.domain.poll.entity;

import static javax.persistence.GenerationType.IDENTITY;

import com.likelion.bliond.base.BaseEntity;
import com.likelion.bliond.domain.event.entity.Event;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
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
