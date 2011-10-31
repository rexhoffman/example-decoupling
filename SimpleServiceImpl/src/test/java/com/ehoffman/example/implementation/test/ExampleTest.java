package com.ehoffman.example.implementation.test;

import static org.fest.assertions.Assertions.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.ehoffman.example.ExampleDTO;
import com.ehoffman.example.implementation.ExampleDTOImpl;
import com.ehoffman.example.implementation.ExampleServiceImpl;

public class ExampleTest {

	@Test(groups="unit")
	public void simpleUnitTest(){
		ExampleServiceImpl service = new ExampleServiceImpl();
		ExampleDTOImpl dto = new ExampleDTOImpl();
		dto.setId(0);
		dto.setMass(new BigDecimal(1.0));
		Map<Integer, ExampleDTO> map = new HashMap<Integer, ExampleDTO>();
		map.put(0,dto);
		service.setValues(map);
		assertThat(service.getById(0)).as("Verify setters and getters").isEqualTo(dto);
	}
}
