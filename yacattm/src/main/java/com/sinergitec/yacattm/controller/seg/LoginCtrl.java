package com.sinergitec.yacattm.controller.seg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sinergitec.yacattm.repos.seg.LoginRep;

/*
 * Autor: Aestrada
 * Fecha: 15 de mayo de 2017
 * Descripcion: Gestiona las peticiones hechas a la 
 * aplicacion unicamente del modulo de seguridad
 * 
 */

@Controller
public class LoginCtrl {

	@Autowired
	private LoginRep loginRep;
	
	@GetMapping("/")
	public String redirectLogin() {
		return "redirect:/segLoginG";
	}

	@GetMapping("/segLoginG")
	public String login() {
		return "segloginV";
	}

	@PostMapping("/segValidaSesionP")
	public ModelAndView valida(@ModelAttribute("compania") String cCompania, @ModelAttribute("usuario") String cUsuario,
			@ModelAttribute("password") String cPassword) {
		
		System.out.println(cCompania);
		System.out.println(cUsuario);
		System.out.println(cPassword);
		
		ModelAndView model;
		
		loginRep.getAcceso(cCompania, cUsuario, cPassword);
		
		if(loginRep.islResultado()){
			model = new ModelAndView("segloginV");
			return model;
		}
		
		model = new ModelAndView("segInicioV");

		return model;
	}

}
