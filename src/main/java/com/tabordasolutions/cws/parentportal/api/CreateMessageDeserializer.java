package com.tabordasolutions.cws.parentportal.api;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.Date;

public class CreateMessageDeserializer extends JsonDeserializer<CreateMessageRequest> {
    @Override
    public CreateMessageRequest deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        CreateMessageRequest request;
        try {
            ObjectCodec oc = jsonParser.getCodec();
            JsonNode node = oc.readTree(jsonParser);

            request = new CreateMessageRequest();
            request.setSender(node.get("sender").asText());
            request.setReceiver(node.get("receiver").asText());
            request.setContent(node.get("content").asText());
            request.setDate(new Date());

        } catch (JsonProcessingException e) {
            System.out.println("Threw JSON Deserializing Error. Unprocessable request, skipping....");
            e.printStackTrace();
            throw e;
        } catch (IOException e) {
            System.out.println("Threw IO Exception. Unprocessable request, skipping....");
            e.printStackTrace();
            throw e;
        }
        return request;
    }
}
