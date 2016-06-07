package com.tabordasolutions.cws.parentportal.api;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class UserDeserializer extends JsonDeserializer<User> {

	@Override
	public User deserialize(JsonParser jsonParser, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		ObjectCodec oc = jsonParser.getCodec();
		JsonNode node = oc.readTree(jsonParser);  
		Long id = node.get("id").asLong(Long.MIN_VALUE);
		id = id.longValue() != Long.MIN_VALUE ? id : null;
		JsonNode nameNode = node.get("name");
		String firstName = nameNode.get("first_name").asText();
		String lastName = nameNode.get("last_name").asText();
		
		JsonNode addressNode = node.get("address");
		String inCareOf = addressNode.get("name").asText();
		String streetAddress1 = addressNode.get("street_address").asText();
		String streetAddress2 = addressNode.get("street_address_2").asText();
		String state = addressNode.get("state").asText();
		String city = addressNode.get("city").asText();
		String zip = addressNode.get("zip").asText();
		

		//TODO : handle case workers
		String image = node.get("image").asText();
		String email = node.get("email").asText();
		String password = node.get("password").asText();
		String newPassword = node.get("npass") != null ? node.get("npass").asText() : null;
		User.Builder builder = new User.Builder();
		return builder.id(id)
				.firstName(firstName)
				.lastName(lastName)
				.inCareOf(inCareOf)
				.streetAddress1(streetAddress1)
				.streetAddress2(streetAddress2)
				.state(state)
				.city(city)
				.zip(zip)
				.imageUrl(image)
				.email(email)
				.password(password)
				.newPassword(newPassword)
				.build();
	}

}
