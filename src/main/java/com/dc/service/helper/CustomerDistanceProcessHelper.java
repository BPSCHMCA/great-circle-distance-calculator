package com.dc.service.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dc.dto.Customer;
import com.dc.service.FileReader;
import com.dc.service.FileWriter;
import com.dc.service.GreatCircleDistance;

@Component
public class CustomerDistanceProcessHelper {

	@Autowired
	private FileReader fileReader;

	@Autowired
	private FileWriter fileWriter;

	@Autowired
	private GreatCircleDistance greatCircleDistance;
	
	@Value("${input.file}")
	private String customerDataFileURL;
	
	@Value("${output.file}")
	private String outputFile;
	
	@Value("${service_distance}")
	private double serviceAreaDistance;

	public List<Customer> getCustomersData() throws IOException {
		List<Customer> customersData = new ArrayList<>();
		InputStreamReader inputStreamReader = fileReader.getInputStreamReader(customerDataFileURL);
		BufferedReader br = new BufferedReader(inputStreamReader);
		String line;
		while (Objects.nonNull(line = br.readLine())) {
			customersData.add(fileReader.mapJSONToObject(line, Customer.class));
		}
		return customersData;
	}

	public void removeCustomerIfNotWithinServiceDistance(List<Customer> customersData) {
		customersData
				.removeIf(customer -> greatCircleDistance.calculateDistanceByLatitudeAndLongitude(customer.getLatitude(),
						customer.getLongitude()) > serviceAreaDistance);
	}

	public void sortCustomerDataByUserIdAsc(List<Customer> customersData) {
		customersData.sort((customer1, customer2) -> customer1.getUserId().compareTo(customer2.getUserId()));
	}

	public void writeCustomersData(List<Customer> customers) throws IOException {
		fileWriter.writeCustomersDataToFile(customers, outputFile);
	}

}
