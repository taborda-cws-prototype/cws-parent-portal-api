package com.tabordasolutions.cws.parentportal.api.messaging;

import java.util.List;

public class MessageResource {
    MessageService messageService;

    public MessageResource(MessageService service) {
        this.messageService = service;
    }

    public List<Message> list(){
        return messageService.messagesFor();
    }
}
