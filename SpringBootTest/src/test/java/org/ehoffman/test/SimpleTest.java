package org.ehoffman.test;

import static org.assertj.core.api.Assertions.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.ehoffman.rest.Application;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class SimpleTest {

    @Test
    public void simpleTest() throws Exception {
        SpringApplication app = new SpringApplication(Application.class);
        ConfigurableApplicationContext context = app.run();
        System.out.println("Beans:");
        new ArrayList<String>(Arrays.asList(context.getBeanDefinitionNames())).forEach(name -> System.out.println(name));
        try (Scanner scanner = new Scanner(new URL("http://127.0.0.1:8080").openStream(), "UTF-8")) {
            assertThat(scanner.useDelimiter("\\A").next()).isEqualTo(new org.ehoffman.rest.Endpoint().index());
        }
    }
}
