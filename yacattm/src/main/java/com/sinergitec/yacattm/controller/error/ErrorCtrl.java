package com.sinergitec.yacattm.controller.error;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorCtrl {
	
	public static final String ISE_VIEW = "/error/500";
	
	@ExceptionHandler(Exception.class)
	public String errorInterno(){
		return ISE_VIEW;
	}

}
