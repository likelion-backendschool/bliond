package com.likelion.bliond.web.poll;

import com.likelion.bliond.domain.poll.service.PollService;
import com.likelion.bliond.domain.question.service.QuestionService;
import com.likelion.bliond.web.question.QuestionVo;
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
@RequestMapping("/api/v1/event/{eventId}/polls")
public class ApiPollControllerV1 {
    private final ModelMapper mapper;
    private final PollService pollService;

    @GetMapping
    public ApiResponse<PollVo> getPolls(@PathVariable Long eventId) {
        List<PollVo> pollVos = pollService.getPolls(eventId).stream().map(pollDto -> mapper.map(pollDto, PollVo.class))
                .toList();

        return ApiResponse.ok(pollVos);
    }
}
