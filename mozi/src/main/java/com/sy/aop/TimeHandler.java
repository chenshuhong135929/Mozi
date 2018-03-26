package com.sy.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class TimeHandler {
	
	public void log(JoinPoint point) {
		 HttpSession session = getSession();
		System.out.println(
				"@After：目标方法为：" + point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName());
		String urlname = point.getSignature().getName();
		
	}

	public static HttpSession getSession() { 
	    HttpSession session = null; 
	    try { 
	        session = getRequest().getSession(); 
	    } catch (Exception e) {} 
	        return session; 
	} 
	    
	public static HttpServletRequest getRequest() { 
	    ServletRequestAttributes attrs =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes(); 
	    return attrs.getRequest(); 
	} 
}
