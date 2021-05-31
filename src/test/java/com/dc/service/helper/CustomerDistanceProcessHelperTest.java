package com.dc.service.helper;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.dc.dto.Customer;
import com.dc.service.FileReader;
import com.dc.service.FileWriter;
import com.dc.service.GreatCircleDistance;
import com.google.common.collect.ImmutableList;

import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;

@RunWith(JUnitPlatform.class)
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@DisplayName("JSON file writer tests")
public class CustomerDistanceProcessHelperTest {

	@InjectMocks
	private CustomerDistanceProcessHelper helper;

	@Mock
	private FileReader fileReader;

	@Mock
	private FileWriter fileWriter;

	@Mock
	private GreatCircleDistance greatCircleDistance;

	@Test
	public void should_return_customerData_from_givenFile() throws IOException {
		Customer customer1 = new Customer(12, "Christina McArdle", "52.986375", "-6.043701");
		Customer customer2 = new Customer(1, "Alice Cahill", "51.92893", "-10.27699");
		List<Customer> expected = ImmutableList.<Customer>of(customer1, customer2);

		InputStream inputStream = CustomerDistanceProcessHelperTest.class.getResourceAsStream("/customer-data.json");

		given(fileReader.getInputStreamReader(ArgumentMatchers.any())).willReturn(new InputStreamReader(inputStream));
		given(fileReader.mapJSONToObject(
				"{\"latitude\": \"52.986375\", \"user_id\": 12, \"name\": \"Christina McArdle\", \"longitude\": \"-6.043701\"}",
				Customer.class)).willReturn(customer1);
		given(fileReader.mapJSONToObject(
				"{\"latitude\": \"51.92893\", \"user_id\": 1, \"name\": \"Alice Cahill\", \"longitude\": \"-10.27699\"}",
				Customer.class)).willReturn(customer2);

		List<Customer> actual = helper.getCustomersData();

		assertEquals(expected, actual);
	}

	@Test
	public void should_removeCustomers_if_distanceGreaterThan100() {
		Customer customer1 = new Customer(12, "Christina McArdle", "52.986375", "-6.043701");
		Customer customer2 = new Customer(1, "Alice Cahill", "51.92893", "-10.27699");
		List<Customer> customers = new ArrayList<>();
		customers.add(customer1);
		customers.add(customer2);

		given(
				greatCircleDistance.calculateDistanceByLatitudeAndLongitude(customer1.getLatitude(), customer1.getLongitude()))
						.willReturn(101.0);
		given(
				greatCircleDistance.calculateDistanceByLatitudeAndLongitude(customer2.getLatitude(), customer2.getLongitude()))
						.willReturn(99.0);
		ReflectionTestUtils.setField(helper, "serviceAreaDistance", 100.0);

		helper.removeCustomerIfNotWithinServiceDistance(customers);

		assertTrue(customers.size() == 1);
		assertEquals(customers.get(0), customer2);
	}

	@Test
	public void should_sort_customers_by_userId() {
		Customer customer1 = new Customer(12, "Christina McArdle", "52.986375", "-6.043701");
		Customer customer2 = new Customer(1, "Alice Cahill", "51.92893", "-10.27699");
		List<Customer> customers = new ArrayList<>();
		customers.add(customer1);
		customers.add(customer2);

		helper.sortCustomerDataByUserIdAsc(customers);

		assertTrue(customer2.equals(customers.get(0)));
		assertTrue(customer1.equals(customers.get(1)));
	}

}
