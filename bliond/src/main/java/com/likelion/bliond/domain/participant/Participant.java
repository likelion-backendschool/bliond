package com.likelion.bliond.domain.participant;

import com.likelion.bliond.domain.event.Event;
import com.likelion.bliond.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // 얘가 주인..
    private Event event;

    @ManyToOne
    private Member member;


}
