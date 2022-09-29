package com.likelion.bliond.web.poll;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
