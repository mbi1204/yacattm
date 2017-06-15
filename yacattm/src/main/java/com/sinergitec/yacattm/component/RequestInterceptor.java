package com.sinergitec.yacattm.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sinergitec.yacattm.model.ct.SessionUsu;

@Component
public class RequestInterceptor extends HandlerInterceptorAdapter {

	/**
	 * This is not a good practice to use sysout. Always integrate any logger
	 * with your application. We will discuss about integrating logger with
	 * spring boot application in some later article
	 */
	private static final Log LOG = LogFactory.getLog(RequestInterceptor.class);
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {

		String cURL = request.getRequestURI();
		request.setAttribute("iniTiempo", System.currentTimeMillis());

		if (cURL.equals("/") || cURL.equals("/segLoginG") || cURL.equals("/segValidaSesionP")) {
			// BEWARE : to be adapted to your actual login page			
			return true;
		}
		
		SessionUsu user = (SessionUsu) request.getSession().getAttribute("Usuario");

		if (user == null) {
			
			response.sendRedirect("/");
			return true;
		} else {
			return true;
		}

	}

	

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception arg3)
			throws Exception {
		
		long startTime  = (long) request.getAttribute("iniTiempo");
		 LOG.info("URL:"  + request.getRequestURL().toString() + " TIEMPO: " + (System.currentTimeMillis() - startTime + " ms" ));
		 
	
	}
	
	
	
}
