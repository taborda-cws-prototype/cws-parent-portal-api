package com.tabordasolutions.cws.parentportal.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.tabordasolutions.cws.parentportal.api.Conversation;
import com.tabordasolutions.cws.parentportal.api.Message;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConversationSerializer extends JsonSerializer<Conversation> {
    private DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

    @Override
    public void serialize(Conversation value, JsonGenerator generator, SerializerProvider serializers) throws IOException, JsonProcessingException {
        generator.writeStartObject();
        generator.writeFieldName("initializer");
        generator.writeString(value.getInitializer());
        generator.writeFieldName("sender");
        generator.writeString(value.getSender());
        generator.writeFieldName("receiver");
        generator.writeString(value.getReceiver());
        generator.writeFieldName("date");
        generator.writeString(dateAsString(value.getDateCreated()));
        generator.writeFieldName("update_date");
        generator.writeString(dateAsString(value.getDateUpdated()));
        generator.writeFieldName("subject");
        generator.writeString(value.getSubject());
        generator.writeFieldName("init_message");
        generator.writeString(messageBody(value.getBaseMessage()));
        generator.writeFieldName("read");
        generator.writeString(String.valueOf(value.isRead()));
        generator.writeFieldName("messages");
        generator.writeStartArray();
        if (value.getMessages() != null && !value.getMessages().isEmpty()){
            for (Message message : value.getMessages()) {
                generator.writeObject(message);
            }
        }
        generator.writeEndArray();

        generator.writeEndObject();

    }

    private String dateAsString(Date date){
        return date == null ? "" : df.format(date);
    }

    private String messageBody(Message message){
        return message == null ? "" : message.getBody();
    }
}
