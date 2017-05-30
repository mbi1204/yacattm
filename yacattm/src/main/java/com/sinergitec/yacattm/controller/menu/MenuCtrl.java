package com.sinergitec.yacattm.controller.menu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("Usuario")
@RequestMapping("/menu")
public class MenuCtrl {
	
	@GetMapping("")
	public String redireccion(){
		return "redirect:/menu/inicio";
	}
	
	@GetMapping("/inicio")
	public ModelAndView inicio(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("segInicioV");
		 return mav;
	}
	
	

}
