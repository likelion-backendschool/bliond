package com.likelion.bliond.web.event;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.likelion.bliond.event.EventDto;
import com.likelion.bliond.event.EventService;
import com.likelion.bliond.member.AuthType;
import com.likelion.bliond.member.Member;
import com.likelion.bliond.member.MemberRepository;
import com.likelion.bliond.security.JwtDto;
import com.likelion.bliond.security.TokenService;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc
public class ApiEventControllerV1Test {

    JwtDto jwtDto;
    

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

    @BeforeEach
    void beforeEach() {
        jwtDto = tokenService.generateTokenTest(AuthType.KAKAO+ "_1234", "1", "ROLE_USER", "user1");
    }

    @Test
    @WithMockUser
    void create() throws Exception {
        // given
        EventCreateForm eventCreateForm = new EventCreateForm("test", LocalDateTime.now().plusDays(1),
            "testDescription", true);

        // when
        ResultActions resultActions = mvc.perform(
                post("/api/v1/events")
                    .contentType(APPLICATION_JSON)
                    .header(AUTHORIZATION, "Bearer " + jwtDto.getAccessToken())
                    .content(objectMapper.writeValueAsString(eventCreateForm))
                    .accept(APPLICATION_JSON))
            .andDo(print());

        // then
        resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status", is(notNullValue())))
            .andExpect(jsonPath("$.data[0].id", is(notNullValue())))
            .andExpect(jsonPath("$.data[0].title", is(notNullValue())))
            .andExpect(jsonPath("$.data[0].endDateTime", is(notNullValue())))
            .andExpect(jsonPath("$.data[0].description", is(notNullValue())));
    }

    @Test
    @WithMockUser
    void read() throws Exception {
        Member member = memberRepository.save(
            Member.builder()
                .username("test")
                .build()
        );

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
                    .header(AUTHORIZATION, "Bearer " + jwtDto.getAccessToken())
                    .accept(APPLICATION_JSON))
            .andDo(print());

        resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status", is(notNullValue())))
            .andExpect(jsonPath("$.data[0].id", is(notNullValue())))
            .andExpect(jsonPath("$.data[0].title", is(notNullValue())))
            .andExpect(jsonPath("$.data[0].endDateTime", is(notNullValue())))
            .andExpect(jsonPath("$.data[0].description", is(notNullValue())))
            .andExpect(jsonPath("$.data[1].id", is(notNullValue())))
            .andExpect(jsonPath("$.data[1].title", is(notNullValue())))
            .andExpect(jsonPath("$.data[1].endDateTime", is(notNullValue())))
            .andExpect(jsonPath("$.data[1].description", is(notNullValue())));
    }
}
