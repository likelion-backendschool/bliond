## 팀 구성원, 개인 별 역할

[팀장] 김도율

[팀원] 박영승, 임채민, 허경주

<br/>

---

<br/>

## 팀 내부 회의 진행 회차 및 일자

- 16회차(2022.09.04) 디스코드 / (박영승님 불참)
- 17회차(2022.09.05) 디스코드 / (김도율님 불참)

<br/>

---

<br/>

## 현재까지 개발 과정 요약 (최소 500자 이상)

- 디렉토리 규칙, 구성 및 생성
- User 도메인 관련 코드 작성
- 회원가입과 로그인 기능을 위한 OAuth2 공부 중
    - Google OAuth2 api - 클라이언트 ID 생성
        
        ![Untitled (3)](https://user-images.githubusercontent.com/27273017/188537194-18412860-99c6-468d-ab08-b15f03080d9b.png)
        
- JWT 사용을 위한 공부 중
- 사용될 API 정리 및 ERD 수정
- commit 메세지 규칙 규정
→ 보기 쉽게 깔끔하게 정리하되, 너무 정리에 신경쓰느라 개발에 지장이 가지 않았음 좋겠다.

<br/>

---

<br/>

## 개발 과정에서 나왔던 질문 (최소 200자 이상)

Q. 테스트 데이터의 경우에 BeforeAll가 아닌 현재 profile 구성 정보(TestInitData를 별도로 만들어서)를 통해서 진행하는 이유가 있을까요 ?

→ @BeforeAll는 매 테스트마다 실행해야 되기 때문에 번거롭다.
TestInitData로 초기 테스트 데이터를 설정해놓으면 관리하기도 편하고 깔끔하기 때문에 별도로 만들었다.  ( TestInitData는 @Transactional을 타지 않음. ) @BeforeAll이나 TestInitData은 동일한 기능이다.
    

Q. JWT access token과 refresh token 관리는 어떻게 하는 것이 좋을까요?

→ access token은 클라이언트 측에서 저장하거나 관리할 필요가 없다. 따라서 refresh token만 관리하면 되는데, 이는 쿠키나 DB 등을 통해 저장할 수 있고, DB에 저장하는 것이 보안에 유리하기 때문에 DB에 저장할 계획이다.

<br/>

---

<br/>

## 개발 결과물 공유

Github Repository URL: [https://github.com/likelion-backendschool/bliond](https://github.com/likelion-backendschool/bliond)

- 필수) 팀원들과 함께 찍은 인증샷(온라인 만남시 스크린 캡쳐)도 함께 업로드 해주세요 🙂

![Untitled (2)](https://user-images.githubusercontent.com/27273017/188537019-8d718b3b-4a83-4851-8756-b9854a52e5cb.png)


[WBS](https://docs.google.com/spreadsheets/d/1S6HiQIrGL680Xy2h5iz_Rc4rD2nstenjXDsX2xpLk9k/edit#gid=0)
