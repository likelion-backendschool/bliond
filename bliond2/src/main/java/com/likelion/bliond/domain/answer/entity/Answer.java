package com.likelion.bliond.domain.answer.entity;

import com.likelion.bliond.base.BaseEntity;
import com.likelion.bliond.domain.event.entity.Event;
import com.likelion.bliond.domain.question.entity.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class Answer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private LocalDateTime createDate;

    @ManyToOne(fetch = LAZY)
    private Question question;

    private String content;

    private Boolean isAcceted;
}
