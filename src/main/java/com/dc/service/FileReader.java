package com.dc.service;

import java.io.IOException;
import java.io.InputStreamReader;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface FileReader {

	InputStreamReader getInputStreamReader(String fileURL) throws IOException;

	<T> T mapJSONToObject(String json, Class<T> t) throws JsonParseException, JsonMappingException, IOException;

}
