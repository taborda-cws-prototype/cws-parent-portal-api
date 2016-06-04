package com.tabordasolutions.cws.parentportal.api;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class UserSerializer extends JsonSerializer<User> {
    @Override
    public void serialize(User value, JsonGenerator generator, SerializerProvider serializers) throws IOException, JsonProcessingException {
        generator.writeStartObject();
        generator.writeFieldName("name");
        generator.writeStartObject();
        generator.writeFieldName("first_name");
        generator.writeString(value.getFirstName());
        generator.writeFieldName("last_name");
        generator.writeString(value.getLastName());
        generator.writeEndObject();
        generator.writeFieldName("address");

        generator.writeStartObject();
        generator.writeFieldName("name");
        generator.writeString(value.getInCareOf());
        generator.writeFieldName("street_address");
        generator.writeString(value.getStreetAddress1());
        generator.writeFieldName("street_address_2");
        generator.writeString(value.getStreetAddress2());
        generator.writeFieldName("state");
        generator.writeString(value.getState());
        generator.writeFieldName("city");
        generator.writeString(value.getCity());
        generator.writeFieldName("zip");
        generator.writeString(value.getZip());
        generator.writeEndObject();

        generator.writeFieldName("case_workers");
        generator.writeStartArray();
        if (value.getCaseworkers() != null && !value.getCaseworkers().isEmpty()){
            for (User caseworker : value.getCaseworkers()) {
                generator.writeObject(caseworker);
            }
        }
        generator.writeEndArray();

        generator.writeFieldName("image");
        generator.writeString(value.getImageUrl());
        generator.writeFieldName("email");
        generator.writeString(value.getEmail());
        generator.writeFieldName("password");
        generator.writeString(value.getPassword());


        generator.writeEndObject();

    }
}
