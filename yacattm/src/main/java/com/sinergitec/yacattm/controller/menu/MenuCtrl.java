package com.sinergitec.yacattm.controller.menu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.sinergitec.yacattm.model.ct.SessionUsu;

/**
 * Autor: Aestrada
 * Fecha: 15 de mayo de 2017
 * Descripcion: Gestiona las peticiones hechas a la 
 * aplicacion carga la vista principal y su session
 * 
 **/

@Controller
@SessionAttributes("Usuario")
@RequestMapping("/menu")
public class MenuCtrl {
	
	public static final String VIEW = "plantilla";
	
	@GetMapping("")
	public String redireccion(){
		return "redirect:/menu/inicio";
	}
	
	@GetMapping("/inicio")
	public ModelAndView inicio(@ModelAttribute("Usuario") SessionUsu objUsuario){
		ModelAndView mav = new ModelAndView();
		mav.addObject("contenido", "/segInicioV");
		mav.addObject("titulo", "Inicio");
		mav.setViewName(VIEW);
		 return mav;
	}
	
	

}
