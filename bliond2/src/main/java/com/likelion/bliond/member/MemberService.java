package com.likelion.bliond.member;

import com.likelion.bliond.web.ApiException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final ModelMapper mapper;

    private final MemberRepository memberRepository;

    public MemberDto findByUsername(String username) {
        Member member = memberRepository.findByUsername(username)
            .orElseThrow(() -> new ApiException("사용자를 찾을 수 없습니다."));
        return mapper.map(member, MemberDto.class);
    }

    public MemberDto findById(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new ApiException("사용자를 찾을 수 없습니다."));
        return mapper.map(member, MemberDto.class);
    }
}
