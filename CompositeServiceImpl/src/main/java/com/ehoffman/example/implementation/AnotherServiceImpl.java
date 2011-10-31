package com.ehoffman.example.implementation;

import java.math.BigDecimal;
import java.math.MathContext;

import com.ehoffman.example.AnotherService;
import com.ehoffman.example.ExampleDTO;
import com.ehoffman.example.ExampleService;

public class AnotherServiceImpl implements AnotherService {

	public enum SHIPPING_METRIC {MASS, VOLUME};
	
	private SHIPPING_METRIC shipBy;
    private ExampleService exampleService;
	
	public ExampleService getExampleService() {
		return exampleService;
	}

	public void setExampleService(ExampleService exampleService) {
		this.exampleService = (ExampleService)exampleService;
	}

	public SHIPPING_METRIC getShipBy() {
		return shipBy;
	}

	public void setShipBy(SHIPPING_METRIC shipBy) {
		this.shipBy = shipBy;
	}

	public BigDecimal getShippingCost(Integer id) {
		ExampleDTO dto = exampleService.getById(id);
		return (getShipBy().equals(SHIPPING_METRIC.MASS)?dto.getMass():dto.getVolume()).multiply(new BigDecimal(10), MathContext.UNLIMITED);
	}
}
