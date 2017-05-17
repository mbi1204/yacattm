package com.sinergitec.yacattm.controller.ope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sinergitec.yacattm.repos.seg.ColorAutoRep;

/**
 * 
 * @author imendoza
 *Fecha      :16/05/2017
 *Descripcion: Controlador de la tabla ColorAuto 
 */

@Controller
@RequestMapping("/ope/ctColorAuto")

public class ColorAutoCtrl {
	@Autowired	
	private ColorAutoRep colorAutoRep;
		
	/*
	@GetMapping("/ope/ctColorAutoG")
	public String login() {
		return "/ope/cat/ctColorAutoV";
	}*/
	
	@GetMapping("/lista")
	public ModelAndView ListAllColorAuto(){
		ModelAndView mav = new ModelAndView("/ope/cat/ctColorAutoV");
		mav.addObject("colorAuto" ,colorAutoRep.ListaColorAuto("AUTOTEC", true));
		return mav;
		
	}

}
