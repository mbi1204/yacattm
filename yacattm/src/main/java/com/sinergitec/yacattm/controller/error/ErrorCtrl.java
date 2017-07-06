package com.sinergitec.yacattm.controller.error;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ErrorCtrl {
	
	public static final String ISE_VIEW = "/error/500";
	
	@ExceptionHandler(Exception.class)
	public ModelAndView errorInterno(HttpServletRequest req, Exception e){
		ModelAndView mav = new ModelAndView(ISE_VIEW);
		mav.addObject("error",e.getMessage());
		return mav;
	}

}
