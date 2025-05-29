package com.example.shopping.member;

public class Board {
    private String title;
    private String content;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Board(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Board() {
    }
}
