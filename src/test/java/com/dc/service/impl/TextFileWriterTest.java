package com.dc.service.impl;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.dc.dto.Customer;
import com.google.common.collect.ImmutableList;

import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;

@RunWith(JUnitPlatform.class)
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@DisplayName("JSON file writer tests")
public class TextFileWriterTest {

	@InjectMocks
	private TextFileWriter fileWriter;

	@Test
	public void should_writeCustomerData_to_givenFile() throws IOException {
		String outputFile = "test.txt";
		File file = new File(outputFile);
		List<Customer> customers = ImmutableList.<Customer>of(new Customer(1, "Test User", "123", "6"));

		if (file.exists()) {
			file.delete();
		}

		fileWriter.writeCustomersDataToFile(customers, outputFile);

		assertTrue(new File(outputFile).exists());

		file.delete();
	}

}
