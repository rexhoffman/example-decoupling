package com.ehoffman.example;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.ehoffman.module.Module;
import org.ehoffman.module.ModuleGroup;
import org.ehoffman.module.ModuleProvider;
import org.ehoffman.module.PrototypeModule;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class CompositeContextModule implements ModuleProvider<ApplicationContext>{

  @Override
  public String getModuleType() {
    return CompositeContextModule.class.getSimpleName();
  }

  public static class FullSpringContext extends CompositeContextModule implements Module<ApplicationContext>{
    public ApplicationContext create(Map<String, ?> dependencies) {
      return new ClassPathXmlApplicationContext(new String[]{"classpath:spring-example-context.xml","classpath:spring-another-service.xml","classpath:spring-test-context.xml"});
    }
  }

  public static class MockFixture extends CompositeContextModule implements PrototypeModule<ApplicationContext> {
    public ApplicationContext create(Map<String, ?> dependencies) {
      ExampleService service = mock(ExampleService.class);
      ExampleDTO dto = mock(ExampleDTO.class);
      when(dto.getId()).thenReturn(1);
      when(dto.getMass()).thenReturn(new BigDecimal("1.0"));
      when(dto.getVolume()).thenReturn(new BigDecimal("10"));
      when(service.getById(1)).thenReturn(dto);
     
      GenericApplicationContext genericContext = new GenericApplicationContext();
      genericContext.getBeanFactory().registerSingleton("exampleService", service);
      
      //Due to the lack of a propertyPlaceHolderConfigurer in the spring context we create an alais of the example service to named as the replacement string spring's propertyplaceholderconfigurer would normally
      genericContext.getBeanFactory().registerAlias("exampleService","${exampleServiceName}");  
      genericContext.refresh();
      ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:spring-another-service.xml"}, genericContext);
      return context;
    }
  }
  
  public static class ALL extends CompositeContextModule implements ModuleGroup<ApplicationContext>{

    @Override
    public List<Class<? extends ModuleProvider<?>>> getModuleClasses() {
      List<Class<? extends ModuleProvider<?>>> list = new ArrayList<Class<? extends ModuleProvider<?>>>();
      list.add(FullSpringContext.class);
      list.add(MockFixture.class);
      return list;
    }
  }
 
  
  public boolean requiresRemote() {
    return false;
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
