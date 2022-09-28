package com.likelion.bliond.domain.poll.entity;


import com.likelion.bliond.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.util.Lazy;

import javax.persistence.*;

@SuperBuilder
@Entity
@NoArgsConstructor
@Getter
@Setter
public class PollChoice extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Poll poll;

    private String name;
}
