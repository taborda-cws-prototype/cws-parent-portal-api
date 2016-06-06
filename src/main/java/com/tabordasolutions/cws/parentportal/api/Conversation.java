package com.tabordasolutions.cws.parentportal.api;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "conversations")
@JsonSerialize(using=ConversationSerializer.class)
public class Conversation {
    @Id
    @GeneratedValue
    private long id;

    @Transient
    private String initializer;
    @Transient
    private String sender;
    @Transient
    private String receiver;
    @Column(name = "date_created")
    private Date dateCreated;
    @Column(name = "date_updated")
    private Date dateUpdated;
    @Transient
    private String subject;

    @Transient
    private Message baseMessage;
    @Column(name = "read")
    boolean read;

    @OneToMany(mappedBy="conversation")
    private List<Message> messages;

    public String getInitializer() {
        return initializer;
    }

    public void setInitializer(String initializer) {
        this.initializer = initializer;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Message getBaseMessage() {
        return baseMessage;
    }

    public void setBaseMessage(Message baseMessage) {
        this.baseMessage = baseMessage;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
