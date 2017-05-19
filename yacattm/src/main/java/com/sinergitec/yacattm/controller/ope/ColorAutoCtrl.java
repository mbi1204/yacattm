package com.sinergitec.yacattm.controller.ope;

import java.awt.Color;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sinergitec.yacattm.model.ct.ColorAuto;
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
		
	
	@GetMapping("")
	public String redirecion() {		
		return "redirect:/ope/ctColorAuto/lista";
	}
	
	@GetMapping("/lista")
	public ModelAndView ListAllColorAuto(){
		ModelAndView mav = new ModelAndView("/ope/cat/ctColorAutoV");
		mav.addObject("colorAuto" , new ColorAuto());
		mav.addObject("ListColorAuto" ,colorAutoRep.ListaColorAuto(0 ,"FOR EACH ctColorAuto WHERE ctColorAuto.cCveCia = 'AUTOTEC' NO-LOCK:"));
		
		return mav;
		
	}
	
	@PostMapping("/agregar")
	public ModelAndView agregar(@ModelAttribute("colorAuto") ColorAuto colorAuto){
		System.out.println("color->" + colorAuto.getColor());
		System.out.println("compañia->" + colorAuto.getCompania());
		//ModelAndView mav = new ModelAndView("lista");
		return null;		
	}
	
	@GetMapping("/eliminar")
	public ModelAndView eliminar(@RequestParam (name="cColor" ,required=true) String cColor){
		ColorAuto colorAuto = new ColorAuto();
		colorAuto = this.colorAutoRep.getColorAuto(1, "FOR EACH ctColorAuto WHERE ctColorAuto.cCveCia = 'AUTOTEC' AND ctColorAuto.cColor = '" + cColor + "' NO-LOCK:");
		
				System.out.println("color->" + colorAuto.toString());
				
		return null;	
	}
	
	
	

}
