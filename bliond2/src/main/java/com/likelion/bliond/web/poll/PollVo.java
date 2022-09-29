package com.likelion.bliond.web.poll;

import com.likelion.bliond.domain.poll.entity.PollChoice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PollVo {
    private String createdDate;
    private Long id;
    private String name;
    private String description;
    private List<PollChoiceVo> pollChoices;
}
