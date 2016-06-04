package com.tabordasolutions.cws.parentportal.api.messaging;

import java.util.Date;

public class Message {
    long id;
    String author;
    Date date;
    String subject;
    String body;

    public Message(long id, Date date, String author, String subject, String body) {
        this.id = id;
        this.date = date;
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

    public Date getDate() { return date; }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { this.author = author; }

    public String getSubject() { return subject; }
}
