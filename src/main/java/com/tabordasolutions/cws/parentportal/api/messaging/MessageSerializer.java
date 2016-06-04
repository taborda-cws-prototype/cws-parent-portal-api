package com.tabordasolutions.cws.parentportal.api.messaging;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageSerializer extends JsonSerializer<Message> {
    private DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

    @Override
    public void serialize(Message value, JsonGenerator generator, SerializerProvider serializers) throws IOException, JsonProcessingException {
        generator.writeStartObject();
        generator.writeFieldName("author");
        generator.writeString(value.getAuthor());
        generator.writeFieldName("content");
        generator.writeString(value.getBody());
        generator.writeFieldName("date");
        generator.writeString(dateAsString(value.getDateCreated()));
        generator.writeEndObject();
    }

    private String dateAsString(Date date){
        return date == null ? "" : df.format(date);
    }
}
