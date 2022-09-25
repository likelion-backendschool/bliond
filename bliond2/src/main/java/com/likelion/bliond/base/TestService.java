package com.likelion.bliond.base;

import static com.likelion.bliond.domain.member.entity.AuthType.KAKAO;

import com.likelion.bliond.domain.event.entity.Event;
import com.likelion.bliond.domain.event.repository.EventRepository;
import com.likelion.bliond.domain.member.entity.Member;
import com.likelion.bliond.domain.member.entity.Role;
import com.likelion.bliond.domain.member.repository.MemberRepository;
import com.likelion.bliond.util.JwtDto;
import com.likelion.bliond.util.TokenService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TestService {

    private final MemberRepository memberRepository;
    private final EventRepository eventRepository;
    private final TokenService tokenService;

    public Long createUser(String userUsername, String userNickname, Role role, String userAuthKey) {
        Member user = Member.builder()
            .authType(KAKAO)
            .authKey(userAuthKey)
            .username(userUsername)
            .role(role)
            .nickname(userNickname)
            .build();
        Member member = memberRepository.save(user);
        JwtDto jwtDto = tokenService.generateTokenTest(userUsername, member.getId().toString(),
            String.valueOf(role), userNickname);
        member.setAccessToken(jwtDto.getAccessToken());

        return member.getId();
    }

    public List<Event> createEvent(Long memberId, int count) {
        Member member = memberRepository.findById(memberId).get();
        List<Event> events = new ArrayList<>();
        IntStream.rangeClosed(1, count).forEach(i -> {
            Event event = Event.builder()
                .endDateTime(LocalDateTime.now())
                .title("title" + i)
                .description("content" + i)
                .isPrivate(false)
                .member(member)
                .build();
            events.add(eventRepository.save(event));
        });

        return events;
    }

    public Event createEventMember() {
        Member member1 = memberRepository.findByUsername("KAKAO_12345").get();
        Member member2 = memberRepository.findByUsername("KAKAO_23456").get();
        Member member3 = memberRepository.findByUsername("KAKAO_34567").get();
        Event event = createEvent(member1.getId(), 1).get(0);

        event.participate(member2);
        event.participate(member3);

        return event;
    }
}
