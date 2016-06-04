package com.tabordasolutions.cws.parentportal.api.messaging;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MessageService {

    public List<Message> messagesFor(long userId){
        return generateMessages();
    }

    private List generateMessages(){
        List <Message> messages = new ArrayList<Message>();


        messages.add(new Message(1, new Date(), "Welcome to the Group", generateBody()));
        messages.add(new Message(2, new Date(), "Some fun facts", generateBody()));
        messages.add(new Message(3, new Date(), "Tomorrow's Meeting is canceled", generateBody()));
        messages.add(new Message(4, new Date(), "Can you call?", generateBody()));
        messages.add(new Message(5, new Date(), "Here's what you requested", generateBody()));
        return messages;
    }

    private String generateBody(){
       return "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin sed sodales augue. Aenean ut dolor est. Nullam laoreet justo eget purus finibus, consequat condimentum elit imperdiet. Cras at mauris vel sem pharetra dignissim at sit amet risus. Pellentesque in justo tortor. Aliquam eu tempor massa. Nam pellentesque dui dignissim est facilisis congue.\n" +
               "\n" +
               "Praesent mattis, felis in rutrum fermentum, mauris diam sodales eros, sit amet vulputate leo leo vitae felis. Nulla a rutrum lectus. Morbi hendrerit ante eu nibh placerat, non sagittis urna tincidunt. Etiam rutrum tortor suscipit, vulputate erat ut, ornare nisl. Integer at vestibulum odio. Maecenas venenatis ac neque at efficitur. Vivamus ut commodo augue. Nulla at sem a lacus pharetra posuere. Morbi eget diam finibus, tristique ante vitae, sollicitudin quam. Quisque a dolor id lorem bibendum pharetra. Sed malesuada nisi quis ligula ullamcorper elementum. Suspendisse quis ornare diam. Integer porttitor felis a mi dapibus malesuada. Quisque vitae velit bibendum, malesuada orci vitae, iaculis mauris. Aliquam tempor commodo sapien rhoncus commodo.";
    }
}