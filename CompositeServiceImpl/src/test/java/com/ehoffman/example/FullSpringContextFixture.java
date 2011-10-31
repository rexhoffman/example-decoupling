package com.ehoffman.example;

import java.util.Map;

import org.ehoffman.module.Module;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FullSpringContextFixture implements Module<ApplicationContext> {

	public ApplicationContext create(Map<String, ?> dependencies) {
		return new ClassPathXmlApplicationContext(new String[]{"classpath:spring-example-context.xml","classpath:spring-another-service.xml","classpath:spring-test-context.xml"});
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