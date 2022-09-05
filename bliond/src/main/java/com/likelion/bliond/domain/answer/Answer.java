package com.likelion.bliond.domain.answer;

import com.likelion.bliond.domain.participant.Participant;
import com.likelion.bliond.domain.question.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.websocket.MessageHandler;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Participant participant;

    @ManyToOne
    private Question question;

    @Column(columnDefinition = "TEXT")
    private String content;

    private Boolean isAccepted;

}
