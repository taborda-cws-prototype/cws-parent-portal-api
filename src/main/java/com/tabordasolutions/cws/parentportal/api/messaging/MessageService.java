package com.tabordasolutions.cws.parentportal.api.messaging;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MessageService {

    public List<Message> messagesFor(){
        return Arrays.asList(new Message(1, new Date(), "", ""));
    }
}
