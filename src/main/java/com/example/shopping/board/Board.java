package com.example.shopping.board;

public class Board {
    private Long id;          // 게시글 고유 ID
    private String title;     // 제목
    private String content;   // 내용
    private String writer;    // 작성자 ID (Member의 id를 문자열로 저장)

    public Board() {}

    public Board(Long id, String title, String content, String writer) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }
}