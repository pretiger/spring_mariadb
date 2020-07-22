package com.sample.spring.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAdvice {

private static final Logger logger=LoggerFactory.getLogger(LogAdvice.class);
	
	//포인트컷 - 실행시점, Around - 실행전후
	//Before, After, Around
	//컨트롤러, 서비스, dao의 모든 method 실행 전후에 logPrint method가 호출
	//execution (리턴 자료형 class.method(매개변수))
//	@Before(
//	@After(
//	@Around(
//	"execution(* com.sample.spring.controller..*Controller.*(..))"
//	+" or execution(* com.sample.spring.service..*Impl.*(..))")
	public Object logPrint(ProceedingJoinPoint joinPoint) throws Throwable {
		//요청 전에 처리할 코드
		long start = System.currentTimeMillis();
		//메인 코드 (핵심업무)
		Object result=joinPoint.proceed();
		//메인 코드가 끝난 후에 처리할 코드
		//핵심업무를 실행한 클래스와 method의 정보 확인
		String type=joinPoint.getSignature().getDeclaringTypeName();
		String name="";
		if(type.indexOf("Controller") > -1) {
			name="Controller \t: ";
		} else if(type.indexOf("Service") > -1) {
			name="ServiceImpl \t: ";
		}
		//method name
		logger.info(name+type+"."+joinPoint.getSignature().getName()+"()");
		//매개변수
		logger.info(Arrays.toString(joinPoint.getArgs()));
		//핵심로직으로 이동
		long end=System.currentTimeMillis();
		long time=end-start;
		logger.info("실행시간:"+time);
		return result;
	}
}
