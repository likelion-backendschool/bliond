package com.likelion.bliond.domain.question;

import com.likelion.bliond.domain.event.Event;
import com.likelion.bliond.domain.participant.Participant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Participant participant;

    @ManyToOne
    private Event event; //이벤트의 id

    @Column(columnDefinition = "TEXT")
    private String content;

    private Integer likeCount;

    private Boolean isDeleted;

}
