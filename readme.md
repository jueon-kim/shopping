## CRUD 게시판

* 주제: 회원가입 + 게시판  
  Spring Boot 3.4.5 기반의 웹 애플리케이션을 개발하였습니다. <br>
Thymeleaf와 MySQL을 이용한 데이터 저장, HTML/CSS 기반으로 구현 하였습니다. <br>
Java 17 환경에서 Gradle로 MVC 아키텍처를 중심으로 회원가입, 로그인, 게시판 CRUD 기능을 구현했습니다.

## Skill
| Skill    | version               |
|----------|-----------------------|
| Backend  | Java17, SpringBoot    | 
| Frontend | HTML, CSS3, Thymeleaf | 
| Database | MySQL                 | 
| Toll     | Mac, IntelliJ         |   


## Member JDBC
| 메서드 이름                     | API 설명      | 설명                      |
|----------------------------| ----------- |-------------------------|
| save(Member member)        | 회원가입        | 사용자가 입력한 정보를 DB에 저장.    |
| login(String id, String pw) | 로그인         | ID와 비밀번호가 맞는지 확인.       |
| findById(String id)        | 아이디로 회원 조회  | 아이디를 기준으로 회원 정보를 찾음.    |
| findbyAll()                | 전체 회원 목록 조회 | DB에 있는 모든 회원 정보를 가져온다.  |
| update(Member member)      | 회원 정보 수정    | 사용자의 이름, 비밀번호, 전화번호를 수정해요. |


## Board JDBC
| 메서드 이름                      | API 설명      | 설명                    |
| --------------------------- | ----------- |-----------------------|
| boardsave(Board board)      | 게시글 저장      | 게시글 제목, 내용, 작성자를 DB에 저장. |
| findboard()                 | 전체 게시글 조회   | DB에 저장된 모든 게시글을 불러옴   |
| findById(Long id)           | ID로 게시글 조회  | 게시글 ID를 기준으로 특정 게시글을 찾음. |
| update(Board board)         | 게시글 수정      | 게시글 제목과 내용을 수정        |
| findByWriter(String writer) | 작성자 이름으로 조회 | 작성자 이름으로 작성한 게시글을 찾음  |

## Member Controller
| 메서드 이름           | API 설명       | 설명                       |
| ---------------- | ------------ |--------------------------|
| index(...)       | 메인 페이지       | 로그인한 회원 정보를 세션에서 확인      |
| loginPage()      | 로그인 폼 이동     | 로그인 화면으로 이동              |
| joinpage()       | 회원가입 폼 이동    | 회원가입 화면으로 이동             |
| boardwriteForm() | 게시글 작성 폼 이동  | 게시글 작성 폼 화면으로 이동해요.      |
| join(...)        | 회원가입 처리      | 회원 정보를 DB에 저장, 중복 체크.    |
| login(...)       | 로그인 처리       | 입력한 ID/PW로 로그인하고 세션에 저장해요. |
| logout(...)      | 로그아웃 처리      | 세션을 종료해서 로그아웃.           |
| mypage(...)      | 마이페이지 이동     | 로그인한 사용자의 정보와 작성한 게시글을 보여줌. |
| memberList(...)  | 전체 회원 리스트 조회 | 관리자용: 전체 회원 목록을 보여줌      |


## Board Controller
| 메서드 이름             | API 설명      | 설명                       |
| ------------------ | ----------- |--------------------------|
| boardWriteForm()   | 게시글 작성 폼 이동 | 게시글 작성 화면으로 이동.          |
| boardsave(...)     | 게시글 저장 처리   | 로그인한 작성자 정보를 포함해 게시글을 저장. |
| boardlist(...)     | 게시글 목록 조회   | 저장된 모든 게시글을 리스트로 보여줌     |
| editBoardForm(...) | 게시글 수정 폼 이동 | 특정 ID의 게시글을 찾아서 수정 화면에 보여줌. |
| boardUpdate(...)   | 게시글 수정 처리   | 사용자가 입력한 내용으로 게시글을 수정해요. |


## DB
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


* 추가 작엄 
* 2025.06.20 UI 변경 
* 