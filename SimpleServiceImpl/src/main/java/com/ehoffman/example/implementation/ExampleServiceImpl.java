package com.ehoffman.example.implementation;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ehoffman.example.ExampleDTO;
import com.ehoffman.example.ExampleService;

public class ExampleServiceImpl implements ExampleService{
    private static Logger logger = LoggerFactory.getLogger(ExampleServiceImpl.class);
	
	public ExampleDTO getById(Integer id) {
		return values.get(id);
	}

	Map<Integer, ExampleDTO> values;

	public Map<Integer, ExampleDTO> getValues() {
		return values;
	}

	public void setValues(Map<Integer, ExampleDTO> values) {
		logger.info("Setting values in ExampleServiceImpl");
		this.values = values;
	}
}
