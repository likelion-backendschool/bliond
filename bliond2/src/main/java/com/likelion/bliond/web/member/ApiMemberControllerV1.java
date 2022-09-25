package com.likelion.bliond.web.member;

import com.likelion.bliond.member.MemberDto;
import com.likelion.bliond.member.MemberService;
import com.likelion.bliond.web.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class ApiMemberControllerV1 {

    private final MemberService memberService;

    private final ModelMapper mapper;

    @GetMapping("/{id}")
    public ApiResponse<MemberDetailVo> getMember(@PathVariable Long id) {
        MemberDto memberDto = memberService.findById(id);
        MemberDetailVo result = mapper.map(memberDto, MemberDetailVo.class);
        return ApiResponse.ok(result);
    }
}
