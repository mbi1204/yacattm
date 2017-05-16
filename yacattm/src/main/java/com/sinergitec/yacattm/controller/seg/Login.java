package com.sinergitec.yacattm.controller.seg;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*
 * Autor: Aestrada
 * Fecha: 15 de mayo de 2017
 * Descripcion: Gestiona las peticiones hechas a la 
 * aplicacion unicamente del modulo de seguridad
 * 
 */

@Controller
public class Login {
	
	@GetMapping("/")
	public String redirectLogin(){
		return"redirect:/login";
	}
	
	@GetMapping("/login")
	public String login(){
		return "login";
	}

}
