package com.sample.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session=request.getSession();
		if(session.getAttribute("userid") == null) {
//			System.out.println("========확인UserID========== : "+session.getAttribute("userid"));
			response.sendRedirect(request.getContextPath()+"/member/login.do?message="+"Not Logging!");
			return false;
		} else {
			return true;
		}
	}
}