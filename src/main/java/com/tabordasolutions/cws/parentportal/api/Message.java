package com.tabordasolutions.cws.parentportal.api;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;

@JsonSerialize(using=MessageSerializer.class)
public class Message {
    @Id
    @GeneratedValue
    private long id;
    private User author;
    private User recipient;
    @Column(name = "date_created")
    private Date dateCreated;
    @Column(name = "subject")
    private String subject;
    @Column(name = "body")
    private String body;
    @ManyToOne
    @JoinColumn(name="conversation_id")
    private Conversation conversation;

    public Message(long id, Date dateCreated, User author, User recipient, String subject, String body) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.author = author;
        this.recipient = recipient;
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

    public User getAuthor() { return author; }

    public void setAuthor(User author) { this.author = author; }

    public User getRecipient() { return recipient; }

    public void setRecipient(User recipient) { this.recipient = recipient; }

    public String getSubject() { return subject; }

    public Conversation getConversation() { return conversation; }
}
