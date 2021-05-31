package com.dc.service.impl;

import org.springframework.stereotype.Service;

import com.dc.constant.Constants;
import com.dc.service.GreatCircleDistance;

@Service
public class GreatCircleDistanceImpl implements GreatCircleDistance {

	@Override
	public double calculateDistanceByLatitudeAndLongitude(double latitude, double longitude) {
		double x1 = Math.toRadians(Constants.OFFICE_LATITUDE);
		double y1 = Math.toRadians(Constants.OFFICE_LONGITUDE);

		double x2 = Math.toRadians(latitude);
		double y2 = Math.toRadians(longitude);

		return 6371 * Math.acos(Math.sin(x1) * Math.sin(x2) + Math.cos(x1) * Math.cos(x2) * Math.cos(y1 - y2));
	}

}
