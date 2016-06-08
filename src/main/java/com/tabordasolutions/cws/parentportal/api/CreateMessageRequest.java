package com.tabordasolutions.cws.parentportal.api;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Date;

@JsonDeserialize(using=CreateMessageDeserializer.class)
public class CreateMessageRequest {
    long conversationId;
    String sender;
    String receiver;
    String content;
    Date date;

    public Message buildMessage(User sender, User receiver, Conversation conversation){
        Message message = new Message();
        message.setAuthor(sender);
        message.setBody(content);
        message.setConversation(conversation);
        message.setDateCreated(new Date());
        message.setRecipient(receiver);
        message.setSubject(conversation.getSubject());
        return message;
    }

    public long getConversationId() {
        return conversationId;
    }

    public void setConversationId(long conversationId) {
        this.conversationId = conversationId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
