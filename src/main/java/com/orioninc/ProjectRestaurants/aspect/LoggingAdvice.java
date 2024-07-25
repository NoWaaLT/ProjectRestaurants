package com.orioninc.ProjectRestaurants.aspect;

import com.orioninc.ProjectRestaurants.auth.AuthenticationFacadeImpl;

import lombok.AllArgsConstructor;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

import java.util.Arrays;

// @Order(0)
@Aspect
@Component
@AllArgsConstructor
public class LoggingAdvice {
  private final Logger log = LoggerFactory.getLogger(this.getClass());

  AuthenticationFacadeImpl authenticationFacade;

  //  @Around("com.orioninc.ProjectRestaurants.aspect.AppPointcuts.mainPointcut()")
  //  public Object logAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
  //
  //    if (!log.isDebugEnabled()) {
  //      return proceedingJoinPoint.proceed();
  //    }
  //
  //    String className = proceedingJoinPoint.getTarget().getClass().getName();
  //    String methodName =
  //        ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod().getName();
  //    String methodArgs = Stream.of(proceedingJoinPoint.getArgs()).toList().toString();
  //    long startTime = System.nanoTime();
  //    Object response = proceedingJoinPoint.proceed();
  //    long endTime = System.nanoTime();
  //    long elapsedTime = endTime - startTime;
  //    Throwable throwable = null;
  //
  //    LoggerMessage loggerMessage =
  //        LoggerMessage.builder()
  //            .className(className)
  //            .methodName(methodName)
  //            .methodArgs(methodArgs)
  //            .elapsedTimeInMillis(TimeUnit.NANOSECONDS.toMillis(elapsedTime))
  //            .elapsedTimeInMicros(TimeUnit.NANOSECONDS.toMicros(elapsedTime))
  //                .throwable(null)
  //            .result(response)
  //            .build();
  //
  //    log.debug("LogAspect :  {}", loggerMessage);
  //
  //    return response;
  //  }

  @Around("com.orioninc.ProjectRestaurants.aspect.AppPointcuts.mainPointcut()")
  public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

//    if (log.isDebugEnabled()) {
//      log.debug(
//          "Enter (Debug): {}.{}() with argument[s] = {}",
//          joinPoint.getSignature().getDeclaringTypeName(),
//          joinPoint.getSignature().getName(),
//          Arrays.toString(joinPoint.getArgs()));
//    }

    try {
      Object result = joinPoint.proceed();
      if (log.isDebugEnabled()) {
        log.debug(
            "Exit: {}.{}() with result = {}",
            joinPoint.getSignature().getDeclaringTypeName(),
            joinPoint.getSignature().getName(),
            result);
      }
      return result;
    } catch (IllegalArgumentException e) {
      log.error(
          "Illegal argument: {} in {}.{}()",
          Arrays.toString(joinPoint.getArgs()),
          joinPoint.getSignature().getDeclaringTypeName(),
          joinPoint.getSignature().getName());
      throw e;
    }
  }

//  @AfterThrowing(
//      pointcut = "com.orioninc.ProjectRestaurants.aspect.AppPointcuts.mainPointcut()",
//      throwing = "e")
//  public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
//
//    String className = joinPoint.getSignature().getDeclaringTypeName();
//    String methodName = ((MethodSignature) joinPoint.getSignature()).getMethod().getName();
//    Throwable throwable = e.getCause();
//
//    LoggerMessage loggerMessage =
//        LoggerMessage.builder()
//            .className(className)
//            .methodName(methodName)
//            .throwable(throwable)
//            .build();
//
//    log.error("LogAspect (Exception) : {}", loggerMessage);
//  }

  @AfterThrowing(pointcut = "com.orioninc.ProjectRestaurants.aspect.AppPointcuts.mainPointcut()", throwing = "e")
  public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
    log.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
            joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL");
  }
}
