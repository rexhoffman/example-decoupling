package org.ehoffman.example.compositeweb.test;

import static org.ehoffman.testing.fest.webdriver.WebElementAssert.*;

import org.ehoffman.example.test.extensions.MyEnforcer;
import org.ehoffman.testing.fixture.FixtureContainer;
import org.ehoffman.testing.module.webapp.Application;
import org.ehoffman.testing.module.webdriver.WebDriverLocalModule;
import org.ehoffman.testng.extensions.Fixture;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(MyEnforcer.class)
public class WebappTest {

	@Test(groups="remote-integration")
	@Fixture(factory={WebDriverLocalModule.Firefox.class, CompositeWebAppModule.class})
	public void testWebApp(){
		Application application = FixtureContainer.getService(CompositeWebAppModule.class);
		WebDriver driver = FixtureContainer.getService(WebDriverLocalModule.Firefox.class);
		application.getDefaultRootUrl();
		driver.navigate().to(application.getDefaultRootUrl());
		WebElement element = driver.findElement(By.tagName("body"));
		assertThat(element).isDisplayed().textContains("[id=1, mass=1, name=A pound of feathers, volume=10]");
	}
	
}
