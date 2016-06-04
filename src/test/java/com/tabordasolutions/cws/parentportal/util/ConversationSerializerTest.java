package com.tabordasolutions.cws.parentportal.util;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tabordasolutions.cws.parentportal.api.Conversation;
import com.tabordasolutions.cws.parentportal.api.Message;
import com.tabordasolutions.cws.parentportal.util.ConversationSerializer;

import io.dropwizard.jackson.Jackson;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.*;

public class ConversationSerializerTest {
    private DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    ConversationSerializer serializer;
    JsonGenerator jsonGenerator;
    StringWriter stringWriter;
    Conversation conversation;
    String subject;
    Message originalMessage ;
    Date createDate ;
    Date modifiedDate ;
    String initializer ;
    String sender ;
    String receiver ;

    @Before
    public void setup() throws IOException {
        serializer = new ConversationSerializer();
        stringWriter = new StringWriter();
        jsonGenerator = new JsonFactory().createGenerator(stringWriter);

        subject = "Subject line";
        originalMessage = new Message(1, new Date(), "Barney", subject, "the body");
        createDate = new Date();
        modifiedDate = new Date();
        initializer = "Fred";
        sender = "Barney";
        receiver = "Fred";
        conversation = new Conversation();
        conversation.setBaseMessage(originalMessage);
        conversation.setDateCreated(createDate);
        conversation.setDateUpdated(modifiedDate);
        conversation.setInitializer(initializer);
        conversation.setReceiver(receiver);
        conversation.setSender(sender);
        conversation.setRead(true);
        conversation.setSubject(subject);
    }

    @Test
    public void testSerializesEmptyConversation() throws IOException {
        conversation = new Conversation();
        serializer.serialize(conversation, jsonGenerator, null);
        jsonGenerator.flush();
        String json = stringWriter.toString();
        assertTrue("Expected json to contain intializer value", json.contains("\"initializer\":null,"));
        assertTrue("Expected json to contain sender value",  json.contains("\"sender\":null"));
        assertTrue("Expected json to contain receiver value",  json.contains("\"receiver\":null"));
        assertTrue("Expected json to contain date value",  json.contains("\"date\":\"\""));
        assertTrue("Expected json to contain update_date value",  json.contains("\"update_date\":\"\""));
        assertTrue("Expected json to contain subject value",  json.contains("\"subject\":null"));
        assertTrue("Expected json to contain init_message value",  json.contains("\"init_message\":\"\""));
        assertTrue("Expected json to contain read value",  json.contains("\"read\":\"false\""));
        assertTrue("Expected json to contain messages",  json.contains("\"messages\":[]"));
    }

    @Test
    public void testSerializesConversation() throws IOException {

        serializer.serialize(conversation, jsonGenerator, null);
        jsonGenerator.flush();
        String json = stringWriter.toString();

        assertTrue("Expected json to contain intializer value", json.contains("\"initializer\":\"" + initializer + "\""));
        assertTrue("Expected json to contain sender value",  json.contains("\"sender\":\"" + sender + "\""));
        assertTrue("Expected json to contain receiver value",  json.contains("\"receiver\":\"" + receiver + "\""));
        assertTrue("Expected json to contain date value",  json.contains("\"date\":\"" + df.format(createDate)+ "\""));
        assertTrue("Expected json to contain update_date value",  json.contains("\"update_date\":\"" + df.format(modifiedDate)+ "\""));
        assertTrue("Expected json to contain subject value",  json.contains("\"subject\":\"" + subject +"\""));
        assertTrue("Expected json to contain init_message value",  json.contains("\"init_message\":\"" + originalMessage.getBody() + "\""));
        assertTrue("Expected json to contain read value",  json.contains("\"read\":\"true\""));
        assertTrue("Expected json to contain messages",  json.contains("\"messages\":["));
    }
}