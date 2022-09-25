package com.likelion.bliond.web.member;

import com.likelion.bliond.domain.member.dto.MemberDto;
import com.likelion.bliond.domain.member.service.MemberService;
import com.likelion.bliond.security.context.MemberContext;
import com.likelion.bliond.web.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class ApiMemberControllerV1 {

    private final MemberService memberService;

    private final ModelMapper mapper;

    @GetMapping
    public ApiResponse<MemberDetailVo> getMember(@AuthenticationPrincipal MemberContext memberContext) {
        MemberDto memberDto = memberService.findById(memberContext.getId());
        MemberDetailVo result = mapper.map(memberDto, MemberDetailVo.class);
        return ApiResponse.ok(result);
    }
}
