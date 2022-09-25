package com.likelion.bliond.event;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import com.likelion.bliond.base.BaseEntity;
import com.likelion.bliond.base.BooleanToYNConverter;
import com.likelion.bliond.member.Member;
import java.time.LocalDateTime;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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


//    @OneToMany
//    private List<EventMember> eventMemberList;
}
