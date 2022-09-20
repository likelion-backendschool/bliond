package com.likelion.bliond.member;

import com.likelion.bliond.security.MemberContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MemberController {

    @GetMapping("/home")
    public String home(@AuthenticationPrincipal MemberContext memberContext) {
        log.info("id: {}", memberContext.getId());
        log.info("username: {}", memberContext.getUsername());
        return "home";
    }
}
