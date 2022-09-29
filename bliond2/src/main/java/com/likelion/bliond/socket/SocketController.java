package com.likelion.bliond.socket;

import com.likelion.bliond.domain.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

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
