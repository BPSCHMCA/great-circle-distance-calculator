package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dc.processor.CustomerDistanceDataProcessor;

@SpringBootApplication
public class DistanceCalculatorApp implements CommandLineRunner {

	@Autowired
	private CustomerDistanceDataProcessor customerDistanceDataProcessor;

	public static void main(String[] args) {
		SpringApplication.run(DistanceCalculatorApp.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		System.out.println("Distance calculation process started");
		customerDistanceDataProcessor.processCustomerData();
		System.out.println("Process completed");
	}

}
