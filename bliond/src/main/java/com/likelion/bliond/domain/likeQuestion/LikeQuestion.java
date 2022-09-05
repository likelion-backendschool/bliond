package com.likelion.bliond.domain.likeQuestion;

import com.likelion.bliond.domain.participant.Participant;
import com.likelion.bliond.domain.question.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LikeQuestion {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Participant participant;

    @ManyToOne
    private Question question;

}
