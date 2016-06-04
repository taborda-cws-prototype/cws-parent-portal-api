package com.tabordasolutions.cws.parentportal.api;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tabordasolutions.cws.parentportal.api.Message;
import com.tabordasolutions.cws.parentportal.api.MessageSerializer;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class MessageSerializerTest {
    private DateFormat df;
    MessageSerializer serializer;
    JsonGenerator jsonGenerator;
    StringWriter stringWriter;
    Message message;

    long id;
    Date createDate ;
    String author ;
    String subject ;
    String body ;

    public MessageSerializerTest() {
        df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    }

    @Before
    public void setup() throws IOException {
        serializer = new MessageSerializer();
        stringWriter = new StringWriter();
        jsonGenerator = new JsonFactory().createGenerator(stringWriter);

        id = 5;
        subject = "Subject line";
        createDate = new Date();
        author = "Fred";
        body = "message body text";
        message = new Message(id, createDate, author, subject, body);
    }

    @Test
    public void testSerializesEmptyConversation() throws IOException {
        message = new Message(0,null,null,null,null);
        serializer.serialize(message, jsonGenerator, null);
        jsonGenerator.flush();
        String json = stringWriter.toString();
        assertTrue("Expected json to contain date value",  json.contains("\"date\":\"\""));
        assertTrue("Expected json to contain sender value",  json.contains("\"author\":null"));
        assertTrue("Expected json to contain receiver value",  json.contains("\"content\":null"));
    }

    @Test
    public void testSerializesConversation() throws IOException {
        serializer.serialize(message, jsonGenerator, null);
        jsonGenerator.flush();
        String json = stringWriter.toString();

        assertTrue("Expected json to contain date value",  json.contains("\"date\":\"" + df.format(createDate)+ "\""));
        assertTrue("Expected json to contain sender value",  json.contains("\"author\":\"" + author + "\""));
        assertTrue("Expected json to contain subject value",  json.contains("\"content\":\"" + body +"\""));
    }
}