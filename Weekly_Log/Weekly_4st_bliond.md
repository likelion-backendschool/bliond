## 팀 구성원, 개인 별 역할

김도율 : 로그인 페이지 제작 및 구현
허경주 : 로그인 페이지 제작 및 구현
김강민 : 회원가입 페이지 제작 및 구현
박영승 : 회원가입 페이지 제작 및 구현
임채민 : 회원가입 페이지 제작 및 구현

<br/>

---
<br/>


## 팀 내부 회의 진행 회차 및 일자

- 14회차(2022.08.26) 디스코드 / (전원 참석)
- 15회차(2022.08.29) 디스코드 / (김도율님 불참)


<br/>

---
<br/>


## 현재까지 개발 과정 요약 (최소 500자 이상)

- OAuth 2.0 을 사용한 로그인, 회원가입 기능을 구현 중에 있습니다.
- 각각의 역할(Role)에 따른 사용 권한 제한 기능 구현 중에 있습니다.
    - 회원 (admin / user)
    - 비회원 (비로그인) : 전체적인 기능에 제한을 둔다.
- 로그인 기능은 회원가입 구현이 끝나면 바로 진행할 예정입니다.
- 메인 페이지 일부분 구현 (상세 기능 위치)

- Git 브랜치를 정리하고 Git commit 방식과 규약을 정하였습니다.
    - `(emoji) tag: commit_msg ? #issue_number`
        
        ex) `🍋 set: 초기 패키지 셋팅 완료`
        

이번 주, 수업에 참여가 어려웠던 팀원이 많아 개발이 순조롭게 진행되지 못하여 개발 과정 요약은 이 정도로 작성하였습니다.


<br/>

---
<br/>


## 개발 과정에서 나왔던 질문 (최소 200자 이상)

**Q. 비로그인 접속을 허용하나요?**

A. 로그인 했을 때만 기능 사용 가능하도록 하면 비회원이 접속을 해도 크게 고려하지 않아도 될 거 같다.

**Q. @RestController vs @Controller 사용**

- RestController
    - REST Api 방식으로 개발을 할 경우 사용하는 것이 유리
    - Json 형태로 객체 데이터를 반환
- Controller
    - 주로 View를 반환, 때에 따라 Json 형태로 객체 데이터도 반환 가능


<br/>

---
<br/>


## 개발 결과물 공유

wbs URL : https://docs.google.com/spreadsheets/d/1S6HiQIrGL680Xy2h5iz_Rc4rD2nstenjXDsX2xpLk9k/edit#gid=0

Github Repository URL: [https://github.com/likelion-backendschool/bliond](https://github.com/likelion-backendschool/bliond)

![0829](https://user-images.githubusercontent.com/27273017/187173259-7eacb488-72bf-4b72-b33a-48c7e122d716.png)
