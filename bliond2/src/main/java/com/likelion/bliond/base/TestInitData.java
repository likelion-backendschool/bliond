package com.likelion.bliond.base;

import static com.likelion.bliond.domain.member.entity.AuthType.KAKAO;
import static com.likelion.bliond.domain.member.entity.Role.ROLE_ADMIN;
import static com.likelion.bliond.domain.member.entity.Role.ROLE_USER;

import com.likelion.bliond.domain.event.entity.Event;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestInitData {
    @Bean
    public ApplicationRunner testInit(TestService testService) {
        return args -> {
            String userAuthKey = "12345";
            String userUsername = KAKAO + "_" +userAuthKey;
            String userNickname = "user1";

            String user2AuthKey = "23456";
            String user2Username = KAKAO + "_" +user2AuthKey;
            String user2Nickname = "user2";

            String user3AuthKey = "34567";
            String user3Username = KAKAO + "_" +user3AuthKey;
            String user3Nickname = "user3";

            String adminAuthKey = "54321";
            String adminUsername = KAKAO + "_" + adminAuthKey;
            String adminNickname = "admin1";



            testService.createUser(userUsername, userNickname, ROLE_USER, userAuthKey);
            testService.createUser(user2Username, user2Nickname, ROLE_USER, user2AuthKey);
            testService.createUser(user3Username, user3Nickname, ROLE_USER, user3AuthKey);
            testService.createUser(adminUsername, adminNickname, ROLE_ADMIN, adminAuthKey);
            Event event = testService.createEventMember();
            testService.createQuestion(event, 10);
            testService.createPoll(event, 3);
        };
    }
}
