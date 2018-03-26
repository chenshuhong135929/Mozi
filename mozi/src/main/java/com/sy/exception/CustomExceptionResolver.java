package com.sy.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常处理器
 * 
 * @author DDM
 * 
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {

	/**
	 * 系统抛出的异常
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		// 错误信息
		String message = null;

		CustomException customException = null;
		if (ex instanceof CustomException) {
			customException = (CustomException) ex;
		} else {
			customException = new CustomException("未知错误");
		}
		message = customException.getMessage();

		ModelAndView modelAndView = new ModelAndView();
		// 将错误信息传到页面
		modelAndView.addObject("message", message);

		// 指向错误页面
		modelAndView.setViewName("forward:/WEB-INF/error/error.jsp");

		return modelAndView;
	}
}
