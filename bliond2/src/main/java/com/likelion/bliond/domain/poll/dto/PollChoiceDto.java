package com.likelion.bliond.domain.poll.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PollChoiceDto {
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private Long id;
    private String name;
}
