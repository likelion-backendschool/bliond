package com.likelion.bliond.socket;

import com.likelion.bliond.domain.question.service.*;
import lombok.*;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SocketController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final QuestionService questionService;

    @MessageMapping("/questions/{eventId}")
    public void sendQuestion(@DestinationVariable Long eventId, @Payload MessageQuestion messageQuestion) {
        questionService.create(eventId,messageQuestion.getContent(),messageQuestion.getMemberId());
        simpMessagingTemplate.convertAndSend("/queue/" + eventId, "question");
    }
}
