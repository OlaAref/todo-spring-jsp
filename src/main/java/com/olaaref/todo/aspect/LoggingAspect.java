package com.olaaref.todo.aspect;

import org.springframework.stereotype.Component;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
@Component
public class LoggingAspect {
	
	private final Logger logger = Logger.getLogger(getClass().getName());
	
	@Pointcut("execution(* com.olaaref.todo.controller.*.*(..))")
	private void controllerPackage() {}
	
	@Pointcut("execution(* com.olaaref.todo.service.*.*(..))")
	private void servicePackage() {}
	
	@Pointcut("execution(* com.olaaref.todo.dao.*.*(..))")
	private void daoPackage() {}
	
	@Pointcut("execution(* com.olaaref.todo.rest.*.*(..))")
	private void restPackage() {}
	
	@Pointcut("controllerPackage() || servicePackage() || daoPackage() || restPackage()")
	private void appFlow() {}
	
	//@Before Advice
	@Before("appFlow()")
	public void beforeAdvice(JoinPoint joinPoint) {
		
		//display name of the method which called
		String methodName = joinPoint.getSignature().toShortString();
		logger.info("===== calling method : " + methodName);
		
	}
	
	//@AfterReturning Advice
	@AfterReturning(pointcut = "appFlow()",
					returning = "result")
	public void afterReturningAdvice(JoinPoint joinPoint, Object result) {
		//display returning result
		String methodName = joinPoint.getSignature().toShortString();
		logger.info("===== result of calling (" + methodName + ") : " + result);
	}
}











