package com.likelion.bliond.web.event;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventCreateForm {

    private String title;
    private LocalDateTime endDateTime;
    private String description;
    private Boolean isPrivate;
}
