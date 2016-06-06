package com.tabordasolutions.cws.parentportal.api;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "conversations")
@JsonSerialize(using=ConversationSerializer.class)
public class Conversation {
    private long id;

    private String initializer;
    private String sender;
    private String receiver;
    private Date dateCreated;
    private Date dateUpdated;
    private String subject;

    private Message baseMessage;
    boolean read;

    private Set<Message> messages;

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Transient
    public String getInitializer() {
        return initializer;
    }

    public void setInitializer(String initializer) {
        this.initializer = initializer;
    }

    @Transient
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    @Transient
    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    @Column(name = "date_created")
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Column(name = "date_updated")
    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    @Transient
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Transient
    public Message getBaseMessage() {
        return baseMessage;
    }

    public void setBaseMessage(Message baseMessage) {
        this.baseMessage = baseMessage;
    }

    @Column(name = "read")
    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    @OneToMany( mappedBy="conversation", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @ElementCollection(targetClass=Message.class)
    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }
}
