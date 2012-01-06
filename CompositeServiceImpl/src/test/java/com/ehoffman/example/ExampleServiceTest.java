package com.ehoffman.example;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import org.ehoffman.example.test.extensions.MyEnforcer;
import org.ehoffman.testing.fixture.FixtureContainer;
import org.ehoffman.testng.extensions.Fixture;
import org.mockito.internal.util.MockUtil;
import org.springframework.context.ApplicationContext;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ehoffman.example.AnotherService;
import com.ehoffman.example.ExampleService;

@Listeners( MyEnforcer.class )
public class ExampleServiceTest {
	
	@Test(groups="functional")
	@Fixture(factory=CompositeContextModule.FullSpringContext.class)
	public void functional_y_Test(){
		ApplicationContext context = (ApplicationContext) FixtureContainer.getService(CompositeContextModule.class);
		AnotherService anotherService = (AnotherService) context.getBean("anotherService");
		assertThat(anotherService.getShippingCost(1)).as("Expecting shipping costs to be 10, which is MASS (1) * 10").isEqualByComparingTo(new BigDecimal(10));
	}
	
	/**
	 * Notice that we mock services using there interfaces... not the implementations.  We do not want to tie a test to an implementation of the service.
	 * We want functional testing of a service
	 */
	@Test(groups="unit")//really functional
	@Fixture(factory=CompositeContextModule.MockFixture.class) 
	public void unit_ish_Test(){
		ApplicationContext context = (ApplicationContext) FixtureContainer.getService(CompositeContextModule.class);
		AnotherService anotherService = (AnotherService) context.getBean("anotherService");
		ExampleService service = (ExampleService) context.getBean("exampleService");
		assertThat(anotherService.getShippingCost(1)).as("Expect a shipping cost of mass of the mock * 10, which should be 10").isEqualByComparingTo(new BigDecimal(10));
		verify(service, times(1)).getById(1); //verifies the method is called twice, this is destructive behavior, the next class to use this fixture, would see these usages
	}

	
  /**
   * Notice that we mock services using there interfaces... not the implementations.  We do not want to tie a test to an implementation of the service.
   * We want functional testing of a service
   */
  @Test(groups="unit")//really unit and functional
  @Fixture(factory=CompositeContextModule.ALL.class) 
  public void eitherOrTest(){
    ApplicationContext context = FixtureContainer.getService(CompositeContextModule.class);
    ExampleService service = (ExampleService) context.getBean("exampleService");
    AnotherService anotherService = (AnotherService) context.getBean("anotherService");
    assertThat(anotherService.getShippingCost(1)).as("Expect a shipping cost of mass of the mock * 10, which should be 10").isEqualByComparingTo(new BigDecimal(10));
    
    //this can all be made part of the fluent api....
    if (new MockUtil().isMock(service)){
      verify((ExampleService)context.getBean("exampleService"), times(1)).getById(1); //verifies the method is called twice, this is destructive behavior, the next class to use this fixture, would see these usages
    }
  }

	
}
