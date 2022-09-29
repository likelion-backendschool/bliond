package com.likelion.bliond.web.question;

import com.likelion.bliond.domain.question.service.QuestionService;
import com.likelion.bliond.web.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/event/{eventId}/questions")
public class ApiQuestionControllerV1 {

    private final ModelMapper mapper;
    private final QuestionService questionService;

    @GetMapping
    public ApiResponse<QuestionVo> getQuestions(@PathVariable Long eventId) {
        List<QuestionVo> questionVos = questionService.findAllByEventId(eventId).stream().map(questionDto -> mapper.map(questionDto, QuestionVo.class))
                .toList();

        return ApiResponse.ok(questionVos);
    }
}
