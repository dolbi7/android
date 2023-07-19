package com.example.board;

public class BoardVO {

    private String seqno;
    private String writer;
    private String title;
    private String regdate;
    private String content;
    private String hitno;

    public String getSeqno(){
        return this.seqno;
    }

    public String getTitle(){
        return this.title;
    }

    public String getWriter(){
        return this.writer;
    }

    public String getRegdate(){
        return this.regdate;
    }

    public String getContent(){
        return this.content;
    }

    public String getHitno(){
        return this.hitno;
    }

}
