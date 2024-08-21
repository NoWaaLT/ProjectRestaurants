package com.orioninc.ProjectRestaurants.aspect;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppPointcuts {

  @Pointcut("within(@org.springframework.web.bind.annotation.RestController *) ")
  public void controllerPointcut() {}

  @Pointcut("within(@org.springframework.stereotype.Service *) ")
  public void servicePointcut() {}

  @Pointcut("within(@org.springframework.stereotype.Repository *) ")
  public void repositoryPointcut() {}

  @Pointcut("within(com.orioninc.ProjectRestaurant..*)" +
          " || within(com.orioninc.ProjectRestaurant.service..*)" +
          " || within(com.orioninc.ProjectRestaurant.controller..*)")
  public void applicationPackagePointcut() {
    // Method is empty as this is just a Pointcut, the implementations are in the advices.
  }

  @Pointcut("applicationPackagePointcut() && controllerPointcut() || servicePointcut() || repositoryPointcut()")
  public void mainPointcut() {}
}
