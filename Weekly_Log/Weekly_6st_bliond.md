## 팀 구성원, 개인 별 역할

---

[팀장] 김도율

[팀원] 박영승, 임채민, 허경주

## 팀 내부 회의 진행 회차 및 일자

---

- 18회차(2022.09.13) 디스코드 / (박영승님, 허경주님 불참)

## 현재까지 개발 과정 요약 (최소 500자 이상)

---

## 요구사항 분석

- Authenticate users (feat. JWT)
    - login
        - sns
            
            ![Untitled](https://user-images.githubusercontent.com/51396905/189843620-4332d6db-32ea-4dac-be51-8951f48cc982.png)
            
        
        ![Untitled 1](https://user-images.githubusercontent.com/51396905/189843652-c1171907-d9ab-4d01-9b10-33385a6ed507.png)
        
    - signup
        - sns 가입
            - 자체 회원 생성이 들어가고 - 연동이 되는 걸 구현
            - 간편가입
        - 이메일 인증
            - 인증번호 or 인증 링크
            - [https://www.baeldung.com/spring-email](https://www.baeldung.com/spring-email)
            - [https://victorydntmd.tistory.com/342](https://victorydntmd.tistory.com/342)
    - logout
        - 현재 유저를 get - jwt
            1. 리프레시토큰을 지워서 로그아웃을 구현한다
                1. 동시 로그인 or 다중 로그인
                    1. 범위 
    - 토큰 검증

[https://github.com/raeperd/realworld-springboot-java/tree/master/src/main/java/io/github/raeperd/realworld/domain/article](https://github.com/raeperd/realworld-springboot-java/tree/master/src/main/java/io/github/raeperd/realworld/domain/article)

- **질문** /questions/
    - 생성 :  /create → POST /questions
        - 폼
    - 수정 /modify →
    - 삭제 /delete
    
- **추천** +- post, delete
    - /question/vote/{id}
    
- **답변** /answer/
    - POST /questions/{question_id}/answers/{answer_id}
    - DELETE  /questions/{question_id}/answers/{answer_id}

### 엔티티명 변경 사항

1. 엔티티 user를 member로 변경
2. 엔티티 reply를 answer로 변경
3. 엔티티 like를 likeQuestion으로 변경
→like가 MySQL의 예약어라 안됩니다.

## 개발 과정에서 나왔던 질문 (최소 200자 이상)

---

- Q. **Question 엔티티 구현 중, participant_id ?**
    
    A. @JoinColumn 생략하면 외래키를 찾을 때 기본 전략을 사용한다.
    
    기본 전략 : 필드명 + _ + 참조하는 테이블의 컬럼명
    
    Q. 외래키를 통한 조인을 할 때 어노테이션을 어디에 쓰는가? 한쪽만이라면 어느 엔티티에, 아니면 둘 다? - 단방향, 양방향 …  [https://jeong-pro.tistory.com/231](https://jeong-pro.tistory.com/231)
    A. N:1 중 fk 보유한 N이 주인으로 한다. (mappedby 가 있는 경우 주인이 아님 - mappedby가 주인을 지정해줌) 우선 question이 주인인 걸로…
    
    Q. 부모 자식 관계..? casCade는 어떻게 줄 것인가
    
    A. 회의 중
    
    Q. SBB의 추천 기능은 ManyToMany로 설정되어 있는데 우리 erd에는 ManyToOne으로 설정되어 있다. 뭐로 해야 되지.. 
    
    A. → ManyToOne
    

## 개발 결과물 공유

---

Github Repository URL: [https://github.com/likelion-backendschool/bliond](https://github.com/likelion-backendschool/bliond)

- 필수) 팀원들과 함께 찍은 인증샷(온라인 만남시 스크린 캡쳐)도 함께 업로드 해주세요 🙂

[WBS](https://docs.google.com/spreadsheets/d/1S6HiQIrGL680Xy2h5iz_Rc4rD2nstenjXDsX2xpLk9k/edit#gid=0) 변화점 :
