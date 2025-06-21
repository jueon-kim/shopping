## CRUD 게시판

### 주제: 회원가입 + 게시판  
* Spring Boot 3.4.5 기반의 웹 애플리케이션을 개발하였습니다. <br>
* Thymeleaf와 MySQL을 이용한 데이터 저장, HTML/CSS 기반으로 구현 하였습니다. <br>
* Java 17 환경에서 Gradle로 MVC 아키텍처를 중심으로 회원가입, 로그인, 게시판 CRUD 기능을 구현했습니다.


## Skill
| skill    |                |
|----------|-----------------------|
| Backend  | Java17, SpringBoot    | 
| Frontend | HTML, CSS3, Thymeleaf | 
| Database | MySQL                 | 
| OS & IDE | Mac, IntelliJ         |   


## Member JDBC
| 메서드 이름                     | 설명 | 
|----------------------------| ----------- |
| save(Member member)        | 회원가입         |
| login(String id, String pw) | 로그인         |
| findById(String id)        | 아이디로 회원 조회   |
| findbyAll()                | 전체 회원 목록 조회  |
| update(Member member)      | 회원 정보 수정    |


## Board JDBC
| 메서드 이름                      | 설명    | 
| --------------------------- | ----------- |
| boardsave      | 게시글 저장      | 
| findboard                 | 전체 게시글 조회   |
| findById           | ID로 게시글 조회  |
| update         | 게시글 수정       |
| findByWriter | 작성자 이름으로 조회 |

## Member Controller
| 메서드 이름           | 설명        |
| ---------------- | ------------|
| index       | 메인 페이지       | 
| loginPage      | 로그인 폼      |
| joinpage       | 회원가입 폼     | 
| boardwriteForm | 게시글 작성 폼   |
| join        | 회원가입 처리       |
| login      | 로그인 처리       |
| logout      | 로그아웃 처리       |
| mypage      | 마이페이지 이동     | 
| memberList  | 전체 회원 리스트 조회 |


## Board Controller
| 메서드 이름        |  설명      | 
| ------------- | -------- |
| boardWriteForm | 게시글 작성 폼  | 
| boardsave | 게시글 저장  |
| boardlist | 게시글 목록  |
| editBoardForm | 게시글 수정 폼  |
| boardUpdate   | 게시글 수정 |


# DB
## 게시판 DB 
| colum     | 설명        |
|-----------|-----------|
| title     | 제목        |
| content   | 내용        |
| write     | 작성자 이름    |
| member_id | 작성자 회원 ID |


## 회원 DB

| colum | 설명   |
|-------|------|
| name  | 이름   |
| id    | 아이디  |
| pw    | 비밀번호 |


### 추가 작엄 
* 2025.06.20 UI 변경, Readme.file 업데이트 계획 추가
* 2025.06.21 삭제 기능 추가 

### 업데이트 계획 version2

* 쇼핑몰 제작을 한다.
* 접근 제어, 권한등을 업데이트, 상품 등록 수정 삭제, 정책(할인 이벤트) 

1. JPA 적용 - 도메인 중심 설계 및 데이터 연동
2. Spring Security - 회원가입, 로그인 인증/인가 처리
3. UX/UI - React 기반 컴포넌트 구조 설계 및 상태 관리
5. API & DB 설계 - RESTful API 설계, 아키텍처 및 ERD 구성 
6. 배포 AWS - EC2 화룡ㅇ한 클라우드 환경 구성 
