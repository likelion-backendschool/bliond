package com.likelion.bliond.domain.event;

import com.likelion.bliond.domain.participant.Participant;
import com.likelion.bliond.domain.question.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Event {

    @Id //이벤트의 id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy ="event")
    private List<Participant> participantList = new ArrayList<>();

    /* 카데고리 하실 건가요? */

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @Column(length = 200)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Boolean isPrivate; //공개 비공개 (권한 유무)

    @Column(length = 100)
    private String contact;

}
