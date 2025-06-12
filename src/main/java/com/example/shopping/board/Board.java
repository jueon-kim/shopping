package com.example.shopping.board;

public class Board {
    private String title;
    private String content;
    private Long id;

    public Board() {

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

    public Long getId() {
        return id;
    }



    public void setId(Long id) {
        this.id = id;

    }

    public Board(String title, String content, Long id) {
        this.title = title;
        this.content = content;
        this.id = id;
        }
}
