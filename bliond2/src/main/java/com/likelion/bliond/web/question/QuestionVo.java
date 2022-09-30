package com.likelion.bliond.web.question;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.likelion.bliond.domain.member.entity.Role;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionVo {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime modifiedDate;
    private Long id;
    private String content;
    private Long memberId;
    private String username;
    private String nickname;
    private Role role;
    private String eventNickname;
}
