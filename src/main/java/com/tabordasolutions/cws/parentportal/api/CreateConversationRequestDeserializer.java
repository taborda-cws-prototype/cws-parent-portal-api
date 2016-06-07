package com.tabordasolutions.cws.parentportal.api;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class CreateConversationRequestDeserializer extends JsonDeserializer<CreateConversationRequest> {
    @Override
    public CreateConversationRequest deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);

        Long id = node.get("receiver").asLong(Long.MIN_VALUE);
        id = id.longValue() != Long.MIN_VALUE ? id : null;

        String subject = node.get("subject").asText();
        String message = node.get("init_message").asText();
        CreateConversationRequest request = new CreateConversationRequest();
        request.setSubject(subject);
        request.setMessage(message);
        request.setReceiverId(id);
        return request;
    }
}
