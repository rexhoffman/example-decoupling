package org.ehoffman.example.test.extensions;


import java.util.Arrays;

import org.ehoffman.testing.fixture.FixtureInterceptor;
import org.ehoffman.testing.testng.BrokenInterceptor;
import org.ehoffman.testing.testng.ExtensibleTestNGListener;
import org.ehoffman.testing.testng.GroupsInterceptor;
import org.ehoffman.testing.testng.LogBackInterceptor;
import org.ehoffman.testng.extensions.Broken;

/**
 * This is a reference implementation of the an {@link ExtensibleTestNGListener}.  It captures logging, provides fixture
 * framework support, ignores tests set with a {@link Broken} annotation, and checks that all tests belong to a fixed set
 * of groups.
 * 
 * @author rexhoffman
 */
public class MyEnforcer extends ExtensibleTestNGListener {
  private static boolean ideMode          = Boolean.valueOf(System.getProperty("java.class.path").contains("org.testng.eclipse"));
  private static boolean integrationPhase = Boolean.valueOf(System.getProperty("integration_phase"));
  private static boolean runBrokenTests   = false;

  static {
    ExtensibleTestNGListener.setInterceptors(MyEnforcer.class, 
        Arrays.asList(new LogBackInterceptor(), 
                      new BrokenInterceptor(runBrokenTests, Broken.class, ideMode), 
                      new GroupsInterceptor(new String[] { "unit", "functional" }, new String[] { "remote-integration" }, integrationPhase, ideMode),
                      new FixtureInterceptor()));
  }

  public static boolean isIntegrationPhase() {
    return integrationPhase;
  }

  public static boolean isIdeMode() {
    return ideMode;
  }

  public static boolean isRunBrokenTests() {
    return runBrokenTests;
  }
}
