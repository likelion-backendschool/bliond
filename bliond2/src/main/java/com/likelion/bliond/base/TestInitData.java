package com.likelion.bliond.base;

import static com.likelion.bliond.member.AuthType.KAKAO;
import static com.likelion.bliond.member.Role.ROLE_ADMIN;
import static com.likelion.bliond.member.Role.ROLE_USER;

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
            String adminAuthKey = "54321";
            String adminUsername = KAKAO + "_" + adminAuthKey;
            String adminNickname = "admin1";

            testService.createUser(userUsername, userNickname, ROLE_USER, userAuthKey);
            testService.createUser(adminUsername, adminNickname, ROLE_ADMIN, adminAuthKey);
        };
    }

}
