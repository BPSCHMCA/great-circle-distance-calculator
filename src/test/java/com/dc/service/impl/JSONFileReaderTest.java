package com.dc.service.impl;

import java.io.IOException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.dc.dto.Customer;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(JUnitPlatform.class)
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@DisplayName("JSON file reader tests")
public class JSONFileReaderTest {

	@InjectMocks
	private JSONFileReader fileReader;

	@Mock
	private ObjectMapper objectMapper;

	@Test
	public void should_getInputStreamReader_by_url() throws IOException {
		String fileURL = "https://s3.amazonaws.com/intercom-take-home-test/customers.txt";
		assertNotNull(fileReader.getInputStreamReader(fileURL));
	}

	@Test
	public void should_give_customerObject_from_json() throws JsonParseException, JsonMappingException, IOException {
		String json = "{\"latitude\": \"52.986375\", \"user_id\": 12, \"name\": \"Christina McArdle\", \"longitude\": \"-6.043701\"}";
		Class<Customer> type = Customer.class;
		Customer expected = new Customer(12, "Christina McArdle", "52.986375", "-6.043701");

		BDDMockito.given(objectMapper.readValue(json, type)).willReturn(expected);

		Customer actual = fileReader.mapJSONToObject(json, type);

		assertEquals(expected, actual);
	}

	@Test
	public void should_throw_JsonParseException_on_mapJSONToObject()
			throws JsonParseException, JsonMappingException, IOException {
		String json = "{\"latitude\": \"52.986375\", \"user_id\": 12, \"name\": \"Christina McArdle\", \"longitude\": \"-6.043701\"}";
		Class<Customer> type = Customer.class;

		BDDMockito.given(objectMapper.readValue(json, type)).willThrow(JsonParseException.class);

		assertThrows(JsonParseException.class, () -> fileReader.mapJSONToObject(json, type));
	}
}
