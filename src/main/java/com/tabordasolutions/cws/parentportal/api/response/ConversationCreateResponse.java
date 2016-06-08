package com.tabordasolutions.cws.parentportal.api.response;

import com.tabordasolutions.cws.parentportal.api.Conversation;

public class ConversationCreateResponse {
    private Conversation Conversation;
    private boolean success;

    public ConversationCreateResponse(com.tabordasolutions.cws.parentportal.api.Conversation conversation, boolean success) {
        Conversation = conversation;
        this.success = success;
    }
    public com.tabordasolutions.cws.parentportal.api.Conversation getConversation() {
        return Conversation;
    }

    public void setConversation(com.tabordasolutions.cws.parentportal.api.Conversation conversation) {
        Conversation = conversation;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
