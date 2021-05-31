package com.dc.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dc.dto.Customer;
import com.dc.service.FileWriter;

@Service
public class TextFileWriter implements FileWriter {

	public File writeCustomersDataToFile(List<Customer> customers, String outputFile) throws IOException {
		File file = new File(outputFile);
		PrintWriter printWriter = new PrintWriter(new java.io.FileWriter(file));
		customers.forEach(customer -> printWriter.printf("{\"name\": \"%s\", \"user_id\": %d}\n", customer.getName(),
				customer.getUserId()));
		printWriter.close();
		return file;
	}

}
