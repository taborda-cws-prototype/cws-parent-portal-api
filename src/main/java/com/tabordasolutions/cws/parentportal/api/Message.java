package com.tabordasolutions.cws.parentportal.api;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;

@Entity
@Table(name = "messages")
@JsonSerialize(using=MessageSerializer.class)
public class Message {
    private long id;
    @OneToOne
    private User author;
    @OneToOne
    private User recipient;
    private Date dateCreated;
    private String subject;
    private String body;


    private Conversation conversation;

    public Message(long id, Date dateCreated, User author, User recipient, String subject, String body) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.author = author;
        this.recipient = recipient;
        this.subject = subject;
        this.body = body;
    }

    @Id
    @GeneratedValue
    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    @Column(name = "body")
    public String getBody() { return body; }

    public void setBody(String body) { this.body = body; }

    @Column(name = "date_created")
    public Date getDateCreated() { return dateCreated; }

    public void setDateCreated(Date dateCreated) { this.dateCreated = dateCreated; }

    @Transient
    public User getAuthor() { return author; }

    public void setAuthor(User author) { this.author = author; }

    @Transient
    public User getRecipient() { return recipient; }

    public void setRecipient(User recipient) { this.recipient = recipient; }

    @Column(name = "subject")
    public String getSubject() { return subject; }

    public void setSubject(String subject) { this.subject = subject; }

    @ManyToOne
    @JoinColumn(name="conversation_id")
    public Conversation getConversation() { return conversation; }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }
}
