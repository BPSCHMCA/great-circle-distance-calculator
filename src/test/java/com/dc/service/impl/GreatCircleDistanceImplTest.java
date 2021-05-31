package com.dc.service.impl;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;

@RunWith(JUnitPlatform.class)
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@DisplayName("Great circle distance formula tests")
public class GreatCircleDistanceImplTest {

	@InjectMocks
	private GreatCircleDistanceImpl greatCircleDistanceImpl;

	@Test
	public void should_calculateDistance_by_latitudeAndLongitude() throws IOException {
		double latitude = 52.986375;
		double longitude = -6.043701;
		double expected = 41.76872550088835;

		double actual = greatCircleDistanceImpl.calculateDistanceByLatitudeAndLongitude(latitude, longitude);

		Assertions.assertEquals(expected, actual);
	}
}
