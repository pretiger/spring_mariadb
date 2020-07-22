package com.sample.spring.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MessageAdvice {

	private static final Logger logger=LoggerFactory.getLogger(MessageAdvice.class);
	//com.example.spring02.service.message.MessageService모든클래스.모든메소드(모든파라미터들)
	//메시지 실행전에 아래에 명명된 로그 수집
	@Before("execution(* com.sample.spring.service.message.MessageService*.*(..))")
	public void startLog(JoinPoint jp) {
		logger.info("핵심 업무 코드의 정보:"+jp.getSignature());
		logger.info("mehod:"+jp.getSignature().getName());
		logger.info("매개변수:"+Arrays.toString(jp.getArgs()));
	}
	
	//메시지 실행 전후에 시간체크
	@Around("execution(* com.sample.spring.service.message.MessageService*.*(..))")
	public Object timeLog(ProceedingJoinPoint pjp) throws Throwable {
		long start=System.currentTimeMillis();
		Object result=pjp.proceed();
		long end=System.currentTimeMillis();
		logger.info("실행시간:"+pjp.getSignature().getName()+":"+(end-start));
		logger.info("===========================");
		return result;
	}
}
