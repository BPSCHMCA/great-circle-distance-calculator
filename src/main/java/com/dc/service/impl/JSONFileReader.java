package com.dc.service.impl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dc.service.FileReader;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class JSONFileReader implements FileReader {

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public InputStreamReader getInputStreamReader(String fileURL) throws IOException {
		URL url = new URL(fileURL);
		return new InputStreamReader(url.openConnection().getInputStream());
	}

	public <T> T mapJSONToObject(String json, Class<T> t) throws JsonParseException, JsonMappingException, IOException {
		return objectMapper.readValue(json, t);
	}
}
