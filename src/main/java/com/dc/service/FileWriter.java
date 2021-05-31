package com.dc.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.dc.dto.Customer;

public interface FileWriter {

	public File writeCustomersDataToFile(List<Customer> customers, String outputFile) throws IOException;

}
