package com.likelion.bliond.socket;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageQuestion {

    private Long memberId;
    private String content;
}
