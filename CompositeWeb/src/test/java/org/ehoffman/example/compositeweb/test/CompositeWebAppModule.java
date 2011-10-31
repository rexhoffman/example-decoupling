package org.ehoffman.example.compositeweb.test;

import org.ehoffman.testing.module.webapp.WebAppModule;

public class CompositeWebAppModule extends WebAppModule {

  @Override
  public String getWebAppName() {
    return "compositeweb";
  }

}
