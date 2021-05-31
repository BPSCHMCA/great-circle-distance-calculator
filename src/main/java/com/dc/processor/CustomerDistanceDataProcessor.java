package com.dc.processor;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dc.dto.Customer;
import com.dc.service.helper.CustomerDistanceProcessHelper;

@Component
public class CustomerDistanceDataProcessor {

	@Autowired
	private CustomerDistanceProcessHelper customerDistanceProcessHelper;

	public void processCustomerData() {
		try {
			List<Customer> customersData = customerDistanceProcessHelper.getCustomersData();
			customerDistanceProcessHelper.removeCustomerIfNotWithinServiceDistance(customersData);
			customerDistanceProcessHelper.sortCustomerDataByUserIdAsc(customersData);
			customerDistanceProcessHelper.writeCustomersData(customersData);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
