package com.likelion.bliond.domain.poll.dto;

import com.likelion.bliond.domain.poll.entity.PollChoice;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PollDto {
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private Long id;
    private String name;
    private Boolean isActive;
    private String description;
    private List<PollChoice> pollChoiceDtos;
}
