package com.likelion.bliond.web.event;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.likelion.bliond.domain.event.dto.EventDto;
import com.likelion.bliond.domain.event.service.EventService;
import com.likelion.bliond.domain.member.entity.Member;
import com.likelion.bliond.domain.member.repository.MemberRepository;
import com.likelion.bliond.util.TokenService;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
@Transactional
public class ApiEventControllerV1Test {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private EventService eventService;
    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    TokenService tokenService;

    Member member;
    Member member2;

    @BeforeAll
    void beforeEach() {
        member = memberRepository.findByUsername("KAKAO_12345").get();
        member2 = memberRepository.findByUsername("KAKAO_54321").get();
    }

    @Test
    void create() throws Exception {
        // given
        EventForm eventForm = new EventForm("test", LocalDateTime.now().plusDays(1),
            "testDescription", true);

        // when
        ResultActions resultActions = mvc.perform(
                post("/api/v1/events")
                    .contentType(APPLICATION_JSON)
                    .header(AUTHORIZATION, "Bearer " + member.getAccessToken())
                    .content(objectMapper.writeValueAsString(eventForm))
                    .accept(APPLICATION_JSON))
            .andDo(print());

        // then
        resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status", is(notNullValue())))
            .andExpect(jsonPath("$.data[0].id", is(notNullValue())))
            .andExpect(jsonPath("$.data[0].title", is(notNullValue())))
            .andExpect(jsonPath("$.data[0].endDateTime", is(notNullValue())))
            .andExpect(jsonPath("$.data[0].description", is(notNullValue())))
            .andExpect(jsonPath("$.data[0].memberId", is(notNullValue())))
            .andExpect(jsonPath("$.data[0].username", is(notNullValue())))
            .andExpect(jsonPath("$.data[0].nickname", is(notNullValue())));
    }

    @Test
    void read() throws Exception {
        EventDto eventDto1 = new EventDto();
        eventDto1.setTitle("test1");
        eventDto1.setDescription("testDescription1");
        eventDto1.setEndDateTime(LocalDateTime.now().plusDays(1));
        eventDto1.setIsPrivate(true);
        eventDto1.setMemberId(member.getId());

        EventDto eventDto2 = new EventDto();
        eventDto2.setTitle("test2");
        eventDto2.setDescription("testDescription2");
        eventDto2.setEndDateTime(LocalDateTime.now().plusDays(2));
        eventDto2.setIsPrivate(false);
        eventDto2.setMemberId(member.getId());

        eventService.save(eventDto1);
        eventService.save(eventDto2);

        ResultActions resultActions = mvc.perform(
                get("/api/v1/events")
                    .contentType(APPLICATION_JSON)
                    .header(AUTHORIZATION, "Bearer " + member.getAccessToken())
                    .accept(APPLICATION_JSON))
            .andDo(print());

        resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status", is(notNullValue())))
            .andExpect(jsonPath("$.data[0].id", is(notNullValue())))
            .andExpect(jsonPath("$.data[0].title", is(notNullValue())))
            .andExpect(jsonPath("$.data[0].endDateTime", is(notNullValue())))
            .andExpect(jsonPath("$.data[0].description", is(notNullValue())))
            .andExpect(jsonPath("$.data[0].memberId", is(notNullValue())))
            .andExpect(jsonPath("$.data[0].username", is(notNullValue())))
            .andExpect(jsonPath("$.data[0].nickname", is(notNullValue())))
            .andExpect(jsonPath("$.data[1].id", is(notNullValue())))
            .andExpect(jsonPath("$.data[1].title", is(notNullValue())))
            .andExpect(jsonPath("$.data[1].endDateTime", is(notNullValue())))
            .andExpect(jsonPath("$.data[1].description", is(notNullValue())))
            .andExpect(jsonPath("$.data[1].memberId", is(notNullValue())))
            .andExpect(jsonPath("$.data[1].username", is(notNullValue())))
            .andExpect(jsonPath("$.data[1].nickname", is(notNullValue())));
    }

    @Test
    void detail() throws Exception {
        EventDto eventDto1 = new EventDto();
        eventDto1.setTitle("test1");
        eventDto1.setDescription("testDescription1");
        eventDto1.setEndDateTime(LocalDateTime.now().plusDays(1));
        eventDto1.setIsPrivate(true);
        eventDto1.setMemberId(member.getId());

        Long eventId = eventService.save(eventDto1);

        ResultActions resultActions = mvc.perform(
                get("/api/v1/events/%d".formatted(eventId))
                    .contentType(APPLICATION_JSON)
                    .header(AUTHORIZATION, "Bearer " + member.getAccessToken())
                    .accept(APPLICATION_JSON))
            .andDo(print());

        resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status", is(notNullValue())))
            .andExpect(jsonPath("$.data[0].id", is(notNullValue())))
            .andExpect(jsonPath("$.data[0].title", is(notNullValue())))
            .andExpect(jsonPath("$.data[0].endDateTime", is(notNullValue())))
            .andExpect(jsonPath("$.data[0].description", is(notNullValue())))
            .andExpect(jsonPath("$.data[0].memberId", is(notNullValue())))
            .andExpect(jsonPath("$.data[0].username", is(notNullValue())))
            .andExpect(jsonPath("$.data[0].nickname", is(notNullValue())));
    }

    @Test
    void update() throws Exception {
        EventDto eventDto1 = new EventDto();
        eventDto1.setTitle("test1");
        eventDto1.setDescription("testDescription1");
        eventDto1.setEndDateTime(LocalDateTime.now().plusDays(1));
        eventDto1.setIsPrivate(true);
        eventDto1.setMemberId(member.getId());

        Long eventId = eventService.save(eventDto1);
        EventForm eventForm = new EventForm();
        eventForm.setTitle("test2modify");

        ResultActions resultActions = mvc.perform(
                post("/api/v1/events/%d".formatted(eventId))
                    .contentType(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(eventForm))
                    .header(AUTHORIZATION, "Bearer " + member.getAccessToken())
                    .accept(APPLICATION_JSON))
            .andDo(print());

        resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status", is(notNullValue())))
            .andExpect(jsonPath("$.data[0].id", is(notNullValue())))
            .andExpect(jsonPath("$.data[0].title", is(notNullValue())))
            .andExpect(jsonPath("$.data[0].endDateTime", is(notNullValue())))
            .andExpect(jsonPath("$.data[0].description", is(notNullValue())))
            .andExpect(jsonPath("$.data[0].memberId", is(notNullValue())))
            .andExpect(jsonPath("$.data[0].username", is(notNullValue())))
            .andExpect(jsonPath("$.data[0].nickname", is(notNullValue())));
    }

    @Test
    void remove() throws Exception {
        EventDto eventDto1 = new EventDto();
        eventDto1.setTitle("test1");
        eventDto1.setDescription("testDescription1");
        eventDto1.setEndDateTime(LocalDateTime.now().plusDays(1));
        eventDto1.setIsPrivate(true);
        eventDto1.setMemberId(member.getId());

        Long eventId = eventService.save(eventDto1);

        ResultActions resultActions = mvc.perform(
                 delete("/api/v1/events/%d".formatted(eventId))
                    .contentType(APPLICATION_JSON)
                    .header(AUTHORIZATION, "Bearer " + member.getAccessToken())
                    .accept(APPLICATION_JSON))
            .andDo(print());

        resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status", is(notNullValue())));
    }
}
