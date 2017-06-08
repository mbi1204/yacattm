package com.sinergitec.yacattm.controller.ope;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Autor: Aestrada
 * Fecha: 08 de junio de 2017
 * Descripcion: Se encarga de las peticiones para
 * el catalogo estatus de OS
 * 
 **/

@Controller
@RequestMapping("/ope/ctEstatusOS")
public class EstatusOSCtrl {
	
	public static final String VIEW = "/ope/cat/ctEstatusOSV";
	public static final String REDIRECT = "reditect:/ope/ctEstatusOS/lista";
	/*public static final String VIEW = "plantilla";
	public static final String VIEW = "plantilla";*/
	

	@GetMapping("")
	public String redireccion(){
		return REDIRECT;
	}
	
	@GetMapping("/lista")
	public ModelAndView listaAllEstatusOS(){
		
		
		
		return null;
	}
	
}
