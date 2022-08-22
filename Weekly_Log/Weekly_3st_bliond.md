## 팀 구성원, 개인 별 역할

---

김도율 : 로그인 페이지 제작 및 구현
허경주 : 로그인 페이지 제작 및 구현
김강민 : 회원가입 페이지 제작 및 구현
박영승 : 회원가입 페이지 제작 및 구현
임채민 : 회원가입 페이지 제작 및 구현

## 팀 내부 회의 진행 회차 및 일자

---

- 9회차(2022.08.16) 디스코드 / (허경주 불참)
- 10회차(2022.08.17) 디스코드 / (전원 참석)
- 11회차(2022.08.18) 디스코드 / (전원 참석)
- 12회차(2022.08.19) 디스코드 / (전원 참석)
- 13회차(2022.08.22) 디스코드 / (전원 참석)

## 현재까지 개발 과정 요약 (최소 500자 이상)

---

- 본격 구현을 시작하기 전 미숙한 부분을 우선 공부하고 진행하자는 의견이 나왔다. git 브랜치 전략, 기능별 구현, 스프링부트 공부를 하고 감이 잡혔을 때 구현해야 충돌 없는 수월한 진행이 이루어질 거 같아 각자 부족한 부분을 공부하는 중이다.
- 수업 시간 중 진행되는 SBB를 선행하여 학습을 해보고, CRUD의 기본을 공부하였다.
- 주말에 spring data jpa의 n+1문제에 대해서 공부했다. join fetch를 이용해서 해결할 수 있으며, many to one, one to one 의 관계에서는 지연로딩 (fetch = LAZY)로 설정하는 것을 권장한다.
- 아쉬웠던 점 : 첫 시작이 어려워 진행이 원활하게 이루어지지 않았지만 멘토님 과의 면담으로 대략적인 감을 잡을 수 있었다.

## 개발 과정에서 나왔던 질문 (최소 200자 이상)

---

- **Entity -** `@Setter` / `@Builder`
    - Entity에서 `@Setter` 의 사용을 지양하고 `@Builder` 를 사용하는 것이 더 좋다.
    - 엔티티의 경우 값을 함부로 변경하여 일관성과 안전성을 해치면 안되기 때문이다. 따라서 대신  Lombok의 `@Builder` 어노테이션으로 자동 생성된 builder 패턴을 이용한다.
- **Create, Modify at  과 같은 시간 관련 객체**
    - LocalDateTime 대신 Java.Time.Instant 를 고려해볼까? 했는데 그냥 크게 의미는 없는 것 같다.
    
    참고 레퍼런스: [https://stackoverflow.com/questions/32437550/whats-the-difference-between-instant-and-localdatetime](https://stackoverflow.com/questions/32437550/whats-the-difference-between-instant-and-localdatetime)
    
- **Git branch 전략**
    - git-flow
        
        ![https://velog.velcdn.com/images/aydenote/post/fb7ca171-7f57-4b70-9982-5da047292a26/image.png](https://velog.velcdn.com/images/aydenote/post/fb7ca171-7f57-4b70-9982-5da047292a26/image.png)
        
        - **master** : 기준이 되는 브랜치로 제품을 배포하는 브랜치
        - **develop** : 개발 브랜치로 개발자들이 이 브랜치를 기준으로 각자 작업한 기능들을 Merge
        - **feature** : 단위 기능을 개발하는 브랜치로 기능 개발이 완료되면 develop 브랜치에 Merge
        - **release** : 배포를 위해 master 브랜치로 보내기 전에 먼저 QA(품질검사)를 하기위한 브랜치
        - **hotfix** : master 브랜치로 배포를 했는데 버그가 생겼을 때 긴급 수정하는 브랜치
    - github-flow
        
        ![Untitled](https://user-images.githubusercontent.com/51396905/185873972-83aecff0-5ceb-43f3-8cae-81b2b28231f4.png)
        
        **main(master) 브랜치는 언제든 배포 가능 상태**
        
        - 항상 최신 상태며, stable하다
        - 엄격한 role과 함께 사용
        - merge 하기 전 충분한 테스트 필요
        - **새 브랜치를 만들 때 네이밍 정확성 필요**
            - Git-flow와 다르게 feature나 develop브랜치가 업으므로 브랜치의 이름은 어떤 작업을 하고있는지 자세하게 작성
            - 커밋메시지 또한 명확하게 작성
        - **원격 브랜치로 수시로 push**
            - 원격 저장소에 작업을 수시로 올려 다른 사람들과 공유
        - **pull request 생성**
            - 피드백이 필요할 때 pr을 통해 자신의 코드를 공유하고 리뷰 받기
            - merge될 준비과 완료 되었다면 main 브랜치로 반영 요구
- **commit**
    - git 터미널로 commit 하는 것보다 인텔리제이에서 지원하는 GUI로 commit 하는 게 IDE 자체에서 한번 더 검수를 하므로, 효과적일 수도 있다.
    - commit 메세지도 통일하는 것이 좋을 거 같아 블로그를 정독 후 재회의 예정이다.

## git

git rebase [중심 브랜치] [이사 갈 브랜치]

git rebase : 이사갈때
git rebase -i : 본인이 선택한 브랜치의 최신 커밋들 몇개를 재배치 혹은 특정 커밋 제거

## Spring Data JPA

[https://www.baeldung.com/spring-data-repositories](https://www.baeldung.com/spring-data-repositories)

---

**명한 멘토님 면담**

Q. 프론트를 리액트로 구현하게 된다면 main에다 넣고 하나?
-> 백이랑 프론트랑 밖에다 따로 만들어서 구현하신다.(아예 분리)

Q. 분리하게 된다면 어떻게 연동하게 되나?
-> 엑시오스나 리액트는 터미널에서 실행할 수 있으니 괜찮
-> 프론트 시작하고 백 시작하고 연결 지점이 엑시오스

Q. 파일을 따로 놓아서 생기는 문제는 따로 없나
-> 없다 따로 서버를 두고 있는 상태에서 시작하는 거라 상관없음

Q. 도메인 별로 패키지를 두고 안에 때려넣는 식으로 구현했는데 구조를 어떻게 잡고 가는 게 낫나? 도메인 별로 하나, 컨트롤러, 서비스 별로 하나?
-> 요즘에는 컨트롤러는 따로 빼서 하는 게 나을 거 같다. (서비스와 레포는 도메인 별로 해도 되지만 컨트롤러는 자주 바뀔 수 있으니 따로 빼는 것도 나쁘지 않음. 사실상 크게 상관은 없다.) 멘토님은 장고를 도메인 별로 하긴 했지만 스프링도 도메인 별로 하고 있다

Q. 여럿이서 진행하다보니 깃 관리... 깃플로우 방식 같은 방법이 나와있는데 멘토님의 추천 방법이나 팁은?
-> 배포 브랜치랑 DEV 브랜치랑 나누고 DEV 브랜치에 다 올린 다음 최종본은 프로덕트로 올린다. 머지는 DEV에서 다 일어나고 실수해도 프로덕트엔 영향이 없으니 그런 식으로 해도..
-> 풀 리퀘스트를 DEV으로 해서 검사 받아서 해도 될 것

Q. 프론트는 리액트 말고 다른 추천 방법이 있나?
-> 리액트로 진행해도 될 거 같다.

Q. 프론트를 우선으로 구현하고 백을 구현하는 게 좋을지
백을 우선으로 할지, 아니면 둘다 동시에 구현해야 될지
-> 아예 분리를 해서 전에
API를 뽑을 때도 규칙이 있어야 소통할 수 있으니까... 이런 것도 정해야 될 텐데 프론트랑 백을 같이 해야 될 거 같다 (백을 중심으로 하는 걸 추천)
서비스와 레포는 일반 타임리프나 API를 쓰나 차이가 없지만 서비스에서 DTO를 써야 서비스와 컨트롤러를 일단 어느정도 만든 다음에 백을 중심으로 나가는 걸 추천

Q. 기능 별로 구분해서 구현하게 되면 각각 변수명이 다를 텐데 이런 건 수시로 소통해서 맞춰야 하나?
-> DB는 첨부터 통일해서 시작하는 게 좋다. 추가 사항도 미리 말해서 통일

Q. 처음 코드 작성할 때 막막하고 어디서 시작해야 될지 모를 때는 우선 컨트롤러를 작성해보는 게 낫나?
-> 레포지터리 만든 다음 테스트 만들고 서비스도 테스트하고 컨트롤러 구현하면 될 거 같다 (역순으로)
-> 감이 안와서 컨트롤러부터 했지만 방향을 바꿨다 (반대로 하니까 알찬 기분..)단 테스트가 동반되어야 한다. (간단하게라도) 기능별로(회원, 게시글 등등)
서비스와 레포지터리는 크게 다른 점이 없으니 걔네들을 빨리 만들고 컨트롤러 만들면 될 거 같다.
->레포는 크게 만들 게 없고 나중에 리스트 조회 뽑을 때나 필요하기 때문에 기본적인 것만 쫙 만들면 진행이 수월할 거 같다.

Q. 프론트는 리액트가 JS 파일인데 얘를 타입스크립트 TSS ?를 사용하면 변수 사용할 때 편한 거 같은데.. TSS에서 JS로 변환이 쉽게 되는 거 같은데 타입스크립트를 쓰는게 괜찮을까, JS로 작성하는 게 괜찮을까?
-> 타입스크립트는 공부를 해야 되기 때문에 시간이 된다면 타입스크립트로, 시간이 없으면 JS로 해도 무방 (5명이면 시간적으론 여유있을 거 같다..)

## 개발 결과물 공유

---

Github Repository URL: [https://github.com/likelion-backendschool/bliond](https://github.com/likelion-backendschool/bliond) 

[WBS](https://docs.google.com/spreadsheets/d/1S6HiQIrGL680Xy2h5iz_Rc4rD2nstenjXDsX2xpLk9k/edit#gid=0) 변화점 : 일정이 미뤄졌다. 스프링 및 구현에 필요한 공부를 진행하기로 결정했다.

![Untitled 1](https://user-images.githubusercontent.com/51396905/185874014-7e5b8d5e-63f1-4f20-9879-fda63e7e9f3d.png)

2022-08-16(TUE)

![Untitled 2](https://user-images.githubusercontent.com/51396905/185874048-9618dde3-8c0d-4d90-bfa1-9e181c18d552.png)
2022-08-17(WED)

![Untitled 3](https://user-images.githubusercontent.com/51396905/185874068-5016632a-cf21-4a30-8e7b-e0d749145519.png)
2022-08-18(THU)

![Untitled 4](https://user-images.githubusercontent.com/51396905/185874083-e39974f3-2d6f-4b93-9218-5eddc5edabbe.png)
2022-08-22(MON)
