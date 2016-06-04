package com.tabordasolutions.cws.parentportal.api;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using=MessageSerializer.class)
public class Message {
    private long id;
    private String author;
    private Date dateCreated;
    private String subject;
    private String body;

    public Message(long id, Date dateCreated, String author, String subject, String body) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.author = author;
        this.subject = subject;
        this.body = body;
    }

    public long getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public Date getDateCreated() { return dateCreated; }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { this.author = author; }

    public String getSubject() { return subject; }
}
