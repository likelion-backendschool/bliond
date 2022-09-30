package com.likelion.bliond.base;

import static com.likelion.bliond.domain.member.entity.AuthType.KAKAO;

import com.likelion.bliond.domain.event.entity.Event;
import com.likelion.bliond.domain.event.repository.EventRepository;
import com.likelion.bliond.domain.member.entity.Member;
import com.likelion.bliond.domain.member.entity.Role;
import com.likelion.bliond.domain.member.repository.MemberRepository;
import com.likelion.bliond.domain.poll.entity.Poll;
import com.likelion.bliond.domain.question.entity.Question;
import com.likelion.bliond.domain.question.repository.QuestionRepository;
import com.likelion.bliond.util.JwtDto;
import com.likelion.bliond.util.TokenService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TestService {

    private final QuestionRepository questionRepository;
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

            Event event = Event.builder()
                .endDateTime(LocalDateTime.now())
                .title("! Bliond !")
                .description("bliond의 첫번째 이벤트 생성입니다.")
                .isPrivate(false)
                .member(member)
                .build();
            events.add(eventRepository.save(event));

        Event event2 = Event.builder()
                .endDateTime(LocalDateTime.now())
                .title("[React] 리액트 첫걸음")
                .description("""
                        React로 구현하는 웹사이트.
                        기초부터 튼튼하게
                        """)
                .isPrivate(false)
                .member(member)
                .build();
        events.add(eventRepository.save(event2));
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

    public List<Question> createQuestion(Event event, int count){
        Event event1 = eventRepository.findById(event.getId()).get();
        Member member1 = memberRepository.findByUsername("KAKAO_23456").get();
        event1.addQuestion("""
                       ## Welcome to Bliond
                       
                       Bliond는 여러분의 **무한한 궁금증 해결**을 위한 공간입니다.
                       질문은 쉽게, 그리고 답변과 피드백은 진중하게. 여러분의 귀중한 학습경험을 위해 도와드리겠습니다.
                       
                               ```
                               
                               console.log('Welcome to Bliond!');
                               
                                ```  
                    """, member1);
            event1.addQuestion(
                       """
                       
                       ## Bliond
                       
                               ```
                               
                               import React, {useState} from 'react';
                                    ...
                                   
                               const Bliond = () => {
                                    const [smile, setSmile] = useState(false);
                                   	const comeIn = () -> {
                                   		setSmile(true);
                                   		console.log('smile이 on 되었습니다.');
                                   	}
                                   	return(
                                   		<button onClick={comeIn} > 참여하시겠습니까? </button>
                                   	);
                                }
                                
                                ```
                                
                    """
                    , member1);


        return event.getQuestions();
    }

    public List<Poll> createPoll(Event event, int count){
        Event event1 = eventRepository.findById(event.getId()).get();

        IntStream.rangeClosed(1, count).forEach(i -> {
            event1.addPoll("만족도 조사(%d)".formatted(i), "만족도 조사 투표입니다.(%d)".formatted(i), Arrays.asList("만족", "불만족", "덜만족"));
        });

        return event1.getPolls();
    }

}
