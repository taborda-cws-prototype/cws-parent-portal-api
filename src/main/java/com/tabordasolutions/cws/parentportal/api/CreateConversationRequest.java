package com.tabordasolutions.cws.parentportal.api;

public class CreateConversationRequest {
    private String subject;
    private Long receiverId;
    private String message;

    public CreateConversationRequest() { }

    public CreateConversationRequest(long receiverId, String subject, String message) {
        this.subject = subject;
        this.receiverId = receiverId;
        this.message = message;

    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        subject = subject;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
