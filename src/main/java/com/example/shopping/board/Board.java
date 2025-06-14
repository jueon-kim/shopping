package com.example.shopping.board;

public class Board {
    private String title;
    private String content;
    private String write;
    private Long id;
    private String memberid;

    public String getMemberid() {
        return memberid;
    }

    public void setMember_id(String member_id) {
        this.memberid = member_id;
    }

    public String getWrite() {
        return write;
    }

    public void setWrite(String write) {
        this.write = write;
    }

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

    public Board(String title, String content, Long id, String write, String memberid) {
        this.title = title;
        this.content = content;
        this.id = id;
        this.write = write;
        this.memberid = memberid;
        }
}
