package com.ehoffman.example;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Map;

import org.ehoffman.module.Module;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import com.ehoffman.example.ExampleDTO;
import com.ehoffman.example.ExampleService;

public class MockFixture implements Module<ApplicationContext> {

	public ApplicationContext create(Map<String, ?> dependencies) {
		ExampleService service = mock(ExampleService.class);
        ExampleDTO dto = mock(ExampleDTO.class);
        when(dto.getId()).thenReturn(0);
        when(dto.getMass()).thenReturn(new BigDecimal("1.2"));
        when(dto.getVolume()).thenReturn(new BigDecimal("10"));
		when(service.getById(0)).thenReturn(dto);
		assertThat(service.getById(0).getVolume()).as("Sanity Check:  Expect mock to return a mock which has a volumn of 10").isEqualByComparingTo(new BigDecimal(10));

		GenericApplicationContext genericContext = new GenericApplicationContext();
		genericContext.getBeanFactory().registerSingleton("exampleService", service);
		
		//Due to the lack of a propertyPlaceHolderConfigurer in the spring context we create an alais of the example service to named as the replacement string spring's propertyplaceholderconfigurer would normally
		genericContext.getBeanFactory().registerAlias("exampleService","${exampleServiceName}");  
		genericContext.refresh();
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:spring-another-service.xml"}, genericContext);
		return context;
	}

	public boolean requiresRemote() {
		return false;
	}

	public String getModuleType() {
		return ApplicationContext.class.getSimpleName();
	}

	public Class<? extends ApplicationContext> getTargetClass() {
		return ApplicationContext.class;
	}

	public Map<String, Class<?>> getDependencyDefinition() {
		return null;
	}


	public void destroy() {
	}

}
