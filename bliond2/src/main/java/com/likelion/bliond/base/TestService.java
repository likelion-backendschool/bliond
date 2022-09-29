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

    public List<Question> createQuestion(Event event, int count){
        Event event1 = eventRepository.findById(event.getId()).get();
        Member member1 = memberRepository.findByUsername("KAKAO_23456").get();

        IntStream.rangeClosed(1, count).forEach(i -> {
            event1.addQuestion("""
                    # 제목
                    ```java 
                    public class Main {
                            
                        static List<List<Integer>> graph;
                        static boolean[] visited;
                        static long[] fee;
                        static int n;
                            
                        static long sum;
                            
                        public static void main(String[] args) throws IOException {
                            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                            StringTokenizer st = new StringTokenizer(br.readLine());
                            n = Integer.parseInt(st.nextToken());
                            int m = Integer.parseInt(st.nextToken());
                            long k = Long.parseLong(st.nextToken());
                            fee = new long[n + 1];
                            st = new StringTokenizer(br.readLine());
                            for (int i = 1; i <= n; i++) {
                                fee[i] = Long.parseLong(st.nextToken());
                            }
                            graph = new ArrayList<>();
                            graph.add(null);
                            for (int i = 0; i < n; i++) {
                                graph.add(new LinkedList<>());
                            }
                            for (int i = 0; i < m; i++) {
                                st = new StringTokenizer(br.readLine());
                                int from = Integer.parseInt(st.nextToken());
                                int to = Integer.parseInt(st.nextToken());
                                graph.get(from).add(to);
                                graph.get(to).add(from);
                            }
                            visited = new boolean[n + 1];
                            long answer = 0;
                            sum = Integer.MAX_VALUE;
                            for (int i = 1; i <= n; i++) {
                                if (!visited[i]) {
                                    dfs(i);
                                    answer += sum;
                                }
                            }
                            if (answer <= k) {
                                System.out.println(answer);
                            } else {
                                System.out.println("Oh no");
                            }
                        }
                        public static void dfs(int cur) {
                            visited[cur] = true;
                            sum = Math.min(sum, fee[cur]);
                            for (int next : graph.get(cur)) {
                                if (visited[next]) {
                                    continue;
                                }
                                dfs(next);
                            }
                        }
                    }
                                ```
                    """.formatted(i), member1);
        });

        return event.getQuestions();
    }

}
