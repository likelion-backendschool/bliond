package com.likelion.bliond.web.member;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.likelion.bliond.domain.event.service.EventService;
import com.likelion.bliond.domain.member.entity.Member;
import com.likelion.bliond.domain.member.repository.MemberRepository;
import com.likelion.bliond.util.TokenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc
class ApiMemberControllerV1Test {

    @Autowired
    EventService eventService;

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    TokenService tokenService;

    @Test
    void read() throws Exception {
        Member member = memberRepository.findByUsername("KAKAO_54321").get();
        Long id = member.getId();
        String accessToken = member.getAccessToken();
        ResultActions resultActions = mvc.perform(
                get("/api/v1/members/%d".formatted(id))
                    .contentType(APPLICATION_JSON)
                    .header(AUTHORIZATION, "Bearer " + accessToken)
                    .accept(APPLICATION_JSON))
            .andDo(print());

        resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status", is(notNullValue())))
            .andExpect(jsonPath("$.data[0].id", is(notNullValue())))
            .andExpect(jsonPath("$.data[0].username", is(notNullValue())))
            .andExpect(jsonPath("$.data[0].nickname", is(notNullValue())));
    }
}
