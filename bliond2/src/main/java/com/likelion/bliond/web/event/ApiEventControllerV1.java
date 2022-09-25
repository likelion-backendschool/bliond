package com.likelion.bliond.web.event;

import com.likelion.bliond.event.EventDto;
import com.likelion.bliond.event.EventService;
import com.likelion.bliond.security.MemberContext;
import com.likelion.bliond.web.ApiResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
@Slf4j
public class ApiEventControllerV1 {

    private final ModelMapper mapper;
    private final EventService eventService;

    // TODO member controller, service, repository 가 완성이 안되어서 멈추어진 상태
    @PostMapping
    public ApiResponse<EventCreateVo> save(@RequestBody EventForm eventForm,
        @AuthenticationPrincipal MemberContext memberContext) {
        EventDto eventDto = mapper.map(eventForm, EventDto.class);
        eventDto.setMemberId(memberContext.getId());

        Long eventId = eventService.save(eventDto);

        EventDto findEventDto = eventService.findById(eventId);

        EventCreateVo result = mapper.map(findEventDto, EventCreateVo.class);
        return ApiResponse.created(result);
    }

    @GetMapping
    public ApiResponse<EventVo> getEvents() {
        List<EventVo> eventVos = eventService.getEvents().stream().map(event -> mapper.map(event, EventVo.class))
            .toList();

        return ApiResponse.ok(eventVos);
    }

    @GetMapping("/{eventId}")
    public ApiResponse<EventVo> detail(@PathVariable Long eventId) {
        EventDto eventDto = eventService.findById(eventId);
        EventVo result = mapper.map(eventDto, EventVo.class);

        return ApiResponse.ok(result);
    }

    @PostMapping("/{eventId}")
    public ApiResponse<EventVo> update(@PathVariable Long eventId, @RequestBody EventForm eventForm,
        @AuthenticationPrincipal MemberContext memberContext) {
        EventDto eventDto = mapper.map(eventForm, EventDto.class);
        eventDto.setMemberId(memberContext.getId());
        eventDto.setId(eventId);
        eventService.update(eventDto);

        EventDto findEventDto = eventService.findById(eventId);

        EventVo result = mapper.map(findEventDto, EventVo.class);
        return ApiResponse.ok(result);
    }

    @DeleteMapping("/{eventId}")
    public ApiResponse<EventVo> delete(@PathVariable Long eventId) {
        eventService.deleteById(eventId);
        return ApiResponse.noContent();
    }
}
