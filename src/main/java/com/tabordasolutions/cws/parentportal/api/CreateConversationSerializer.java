package com.tabordasolutions.cws.parentportal.api;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.util.Converter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateConversationSerializer extends JsonSerializer<Conversation> {
    private DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

    @Override
    public void serialize(Conversation value, JsonGenerator generator, SerializerProvider serializers) throws IOException, JsonProcessingException {
        generator.writeStartObject();
        generator.writeFieldName("id");
        generator.writeString(Long.toString(value.getId()));
        generator.writeFieldName("initializer");
        generator.writeString(value.getInitializer());
        generator.writeFieldName("sender");
        generator.writeString(authorsNameFromBaseMessage(value));
        generator.writeFieldName("receiver");
        generator.writeString(receiversNameFromBaseMessage(value));
        generator.writeFieldName("date");
        generator.writeString(dateAsString(dateCreatedFromBaseMessage(value)));
        generator.writeFieldName("update_date");
        generator.writeString(dateAsString(dateCreatedFromBaseMessage(value)));
        generator.writeFieldName("subject");
        generator.writeString(value.getBaseMessage().getSubject());
        generator.writeFieldName("init_message");
        generator.writeString(messageBody(value.getBaseMessage()));
        generator.writeFieldName("read");
        generator.writeString(String.valueOf(value.isRead()));
        generator.writeFieldName("messages");
        generator.writeStartArray();
        if (value.getMessages() != null && !value.getMessages().isEmpty()){
            for (Message message : value.getMessages()) {
                generator.writeStartObject();

                generator.writeFieldName("sender");
                generator.writeString(String.valueOf(authorsName(message)));
                generator.writeFieldName("receiver");
                generator.writeString(String.valueOf(recipientsName(message)));
                generator.writeFieldName("content");
                generator.writeString(String.valueOf(message.getBody()));
                generator.writeFieldName("date");
                generator.writeString(dateAsString(message.getDateCreated()));

                generator.writeEndObject();
            }
        }
        generator.writeEndArray();

        generator.writeEndObject();

    }



    private Date dateCreatedFromBaseMessage(Conversation conversation){
        Date date = null;
        if (hasBaseMessage(conversation)) {
            date = conversation.getBaseMessage().getDateCreated();
        }
        return date;
    }

    private String authorsNameFromBaseMessage(Conversation conversation){
        String name = "";
       if (hasBaseMessage(conversation)) {
           name = authorsName(conversation.getBaseMessage());
       }
        return name;
    }
    private String receiversNameFromBaseMessage(Conversation conversation){
        String name = "";
        if (hasBaseMessage(conversation)) {
            name = recipientsName(conversation.getBaseMessage());
        }
        return name;
    }
    private String authorsName(Message message){
        String name = "";
        if( message != null && message.getAuthor() != null){
            name = message.getAuthor().getFullName();
        }
        return name;
    }

    private String recipientsName(Message message){
        String name = "";
        if( message != null && message.getRecipient() != null){
            name = message.getRecipient().getFullName();
        }
        return name;
    }

    private boolean hasBaseMessage(Conversation conversation){
        return conversation == null || conversation.getBaseMessage() == null;

    }
    private String dateAsString(Date date){
        return date == null ? "" : df.format(date);
    }

    private String messageBody(Message message){
        return message == null ? "" : message.getBody();
    }
}
