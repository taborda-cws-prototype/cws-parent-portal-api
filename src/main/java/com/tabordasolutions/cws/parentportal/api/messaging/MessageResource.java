package com.tabordasolutions.cws.parentportal.api.messaging;

import java.util.Arrays;
import java.util.List;

public class MessageResource {
    public List<Message> list(){
        return Arrays.asList(new Message());
    }
}
