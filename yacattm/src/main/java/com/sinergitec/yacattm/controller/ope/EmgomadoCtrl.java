package com.sinergitec.yacattm.controller.ope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sinergitec.yacattm.model.ct.Engomado;
import com.sinergitec.yacattm.repos.cat.EngomadoRep;

@Controller
@RequestMapping("/ope/ctEngomado")
public class EmgomadoCtrl {
	
	private String cError;
	
	@Autowired
	private EngomadoRep engomadoRep;
	
	@GetMapping("")
	public String redirecion() {
		return "redirect:/ope/ctEngomado/lista";
	}
	
	@GetMapping("/lista")
	public ModelAndView lista (){
		ModelAndView mav = new ModelAndView("/ope/cat/ctEngomadoV");		
		mav.addObject("lista", engomadoRep.Lista(0, "FOR EACH ctEngomado WHERE ctEngomado.cCveCia = 'AUTOTEC' NO-LOCK BY ctEngomado.iOrden:"));
		
		if (engomadoRep.getResultado()) {
			mav.addObject("error",  engomadoRep.getMensaje());
		}		
		return mav;		
	}
	
	
	@GetMapping("/nuevo")
	public ModelAndView nuevo(){
		ModelAndView mav = new ModelAndView("/ope/cat/ctEngomadoAddF");
		Engomado engomado = new Engomado();
		engomado.setCompania("AUTOTEC");
		engomado.setActivo(true);
		engomado.setRowid(null);
		mav.addObject("engomado" , engomado);
		
		
		return mav;
	}
	
	@PostMapping("/agregar")
	public ModelAndView agregar (@ModelAttribute("engomado") Engomado engomado){
		
		System.out.println("aghregar->" + engomado.toString());
		
		engomadoRep.agregar("SISIMB", engomado);
		
		if (this.engomadoRep.getResultado()) { // error
			ModelAndView mav = new ModelAndView("/ope/cat/ctEngomadoAddF");
			mav.addObject("engomado", engomado);
			mav.addObject("error", this.engomadoRep.getMensaje());

			return mav;

		} else {
			ModelAndView mav = new ModelAndView("redirect:/ope/ctEngomado/lista");
			return mav;
		}
		
				
	}
	
	@GetMapping("/getEngomado")
	public ModelAndView getEngomado(@RequestParam(name = "cEngomado", required = true) String cEngomado){
		
		System.out.println(cEngomado);
		Engomado engomado = new Engomado();
		
		engomado = engomadoRep.getEngomado(1, "FOR EACH ctEngomado WHERE ctEngomado.cCveCia = 'AUTOTEC' AND ctEngomado.cEngomado = '" 
		 + cEngomado	+ "' NO-LOCK:");
		
		if (this.engomadoRep.getResultado()) { // error
			ModelAndView mav = new ModelAndView("redirect:/ope/ctEngomado/lista");
			cError = this.engomadoRep.getMensaje();
			return mav;
		} else {
			ModelAndView mav = new ModelAndView("/ope/cat/ctEngomadoUpdF");
			mav.addObject("engomado", engomado);
			mav.addObject("error", this.engomadoRep.getMensaje());
			cError = null;
			return mav;
		}
		
	}
	
	@PostMapping("/actualizar")
	public ModelAndView actualiar(@ModelAttribute("engomado") Engomado engomado){
		
		Engomado viejo = new Engomado();
		
		viejo = engomadoRep.getEngomado(1, "FOR EACH ctEngomado WHERE ctEngomado.cCveCia = 'AUTOTEC' AND ctEngomado.cEngomado = '" 
		 + engomado.getEngomado()	+ "' NO-LOCK:");
		
		System.out.println("Viejo -> " +  viejo.toString());
		
		System.out.println("Nuevo -> " + engomado.toString());
		
		if (this.engomadoRep.getResultado()) { // error
			ModelAndView mav = new ModelAndView("/ope/cat/ctEngomadoUpdF");
			mav.addObject("error", this.engomadoRep.getMensaje());
			mav.addObject("engomado", engomado);
			return mav;
		}
		
		this.engomadoRep.actulizar("SISIMB", viejo, engomado);
		
		if (this.engomadoRep.getResultado()) { // error
			ModelAndView mav = new ModelAndView("/ope/cat/ctEngomadoUpdF");
			mav.addObject("error", this.engomadoRep.getMensaje());
			mav.addObject("engomado", engomado);
			return mav;

		} else {
			ModelAndView mav = new ModelAndView("redirect:/ope/ctEngomado/lista");
			return mav;

		}
		
	}
	

}
