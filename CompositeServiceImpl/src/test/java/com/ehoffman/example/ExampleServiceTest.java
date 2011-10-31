package com.ehoffman.example;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import org.ehoffman.example.test.extensions.MyEnforcer;
import org.ehoffman.testing.fixture.FixtureContainer;
import org.ehoffman.testng.extensions.Fixture;
import org.springframework.context.ApplicationContext;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ehoffman.example.AnotherService;
import com.ehoffman.example.ExampleService;

@Listeners( MyEnforcer.class )
public class ExampleServiceTest {
	
	/**
	 * Notice that we mock services using there interfaces... not the implementations.  We do not want to tie a test to an implementation of the service.
	 * We want functional testing of a service
	 */
	@Test(groups="functional")
	@Fixture(factory=FullSpringContextFixture.class)
	public void simpleServiceTest(){
		ApplicationContext context = (ApplicationContext) FixtureContainer.getService(MockFixture.class);
		AnotherService anotherService = (AnotherService) context.getBean("anotherService");
		assertThat(anotherService.getShippingCost(1)).as("Expecting shipping costs to be 10, which is MASS (1) * 10").isEqualByComparingTo(new BigDecimal(10));
	}
	
	/**
	 * Notice that we mock services using there interfaces... not the implementations.  We do not want to tie a test to an implementation of the service.
	 * We want functional testing of a service
	 */
	@Test(groups="unit")
	@Fixture(factory=MockFixture.class, destructive=true) //only destructive if verify() is used by the tests which use this fixture 
	public void replacementTest(){
		ApplicationContext context = (ApplicationContext) FixtureContainer.getService(MockFixture.class);
		AnotherService anotherService = (AnotherService) context.getBean("anotherService");
		assertThat(anotherService.getShippingCost(0)).as("Expect a shipping cost of mass of the mock * 10, which should be 12").isEqualByComparingTo(new BigDecimal(12));
		verify((ExampleService)context.getBean("exampleService"), times(2)).getById(0); //verifies the method is called twice, this is destructive behavior, the next class to use this fixture, would see these usages
	}
}
