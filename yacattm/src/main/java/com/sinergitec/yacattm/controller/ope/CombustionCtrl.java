package com.sinergitec.yacattm.controller.ope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.sinergitec.yacattm.model.ct.Combustion;
import com.sinergitec.yacattm.model.ct.SessionUsu;
import com.sinergitec.yacattm.repos.cat.CombustionRep;

/**
 * Autor: Aestrada Fecha: 08 de junio de 2017 Descripcion: Se encarga de las
 * peticiones para el catalogo de sistemas de combustion
 * 
 **/

@Controller
@SessionAttributes("Usuario")
@RequestMapping("/ope/ctSisCombAuto")
public class CombustionCtrl {
	
	private static final String VIEW = "/ope/cat/ctSisCombAutoV";
	private static final String FORM_ADD = "/ope/cat/ctSisCombAutoAddF";
	private static final String FORM_UPD = "/ope/cat/ctSisCombAutoUpdF";
	private static final String REDIRECT = "redirect:/ope/ctSisCombAuto/lista";
	
	private String cError;
	
	@Autowired
	private CombustionRep combustionRep;
	
	@GetMapping("")
	public String redireccion() {
		return REDIRECT;
	}
	
	@GetMapping("/lista")
	public ModelAndView lista(@ModelAttribute("Usuario") SessionUsu objUsuario){
		
		ModelAndView mav = new ModelAndView(VIEW);
		mav.addObject("titulo", "Sistema de Combustión");
		mav.addObject("listaSisComb", combustionRep.listaCombustion(0,
				"FOR EACH ctSisCombAuto WHERE ctSisCombAuto.cCveCia = '" + objUsuario.getCompania() + "' NO-LOCK:"));

		if (this.combustionRep.getResultado()) {
			cError = combustionRep.getMensaje();
		}

		mav.addObject("error", cError);
		cError = "";

		return mav;
		
	}
	
	@GetMapping("/nuevo")
	public ModelAndView nuevo(@ModelAttribute("Usuario") SessionUsu objUsuario) {
		ModelAndView mav = new ModelAndView(FORM_ADD);
		Combustion combustion = new Combustion();
		combustion.setCompania(objUsuario.getCompania());
		combustion.setActivo(true);
		combustion.setRowid(null);
		mav.addObject("combustion", combustion);

		return mav;
	}

	@PostMapping("/agregar")
	public ModelAndView agregar(@ModelAttribute("Usuario") SessionUsu objUsuario,
			@ModelAttribute("combustion") Combustion combustion) {

		ModelAndView mav = new ModelAndView();

		this.combustionRep.agregar(objUsuario.getUsuario(), combustion);

		if (this.combustionRep.getResultado()) {
			mav.setViewName(FORM_ADD);
			mav.addObject("estatusOS", combustion);
			mav.addObject("error", this.combustionRep.getMensaje());
		} else {
			mav.setViewName(REDIRECT);
		}

		return mav;
	}

	@GetMapping("/getCombustion")
	public ModelAndView getCombustion(@ModelAttribute("Usuario") SessionUsu objUsuario,
			@RequestParam(name = "cSistema", required = true) String cSistema) {

		ModelAndView mav = new ModelAndView();
		Combustion combustion = new Combustion();

		combustion = this.combustionRep.getCombustion(1, "FOR EACH ctSisCombAuto WHERE ctSisCombAuto.cCveCia = '"
				+ objUsuario.getCompania() + "' AND ctSisCombAuto.cSistema = '" + cSistema + "' NO-LOCK:");

		if (this.combustionRep.getResultado()) {

			mav.setViewName(REDIRECT);
			cError = this.combustionRep.getMensaje();
			return mav;

		} else {
			mav.setViewName(FORM_UPD);
			mav.addObject("combustion", combustion);
			mav.addObject("error", this.combustionRep.getMensaje());
			cError = null;
			return mav;
		}
	}

	@PostMapping("/actualizar")
	public ModelAndView actualizar(@ModelAttribute("Usuario") SessionUsu objUsuario,
			@ModelAttribute("combustion") Combustion combustion) {

		ModelAndView mav = new ModelAndView();
		Combustion viejo = new Combustion();
		viejo = this.combustionRep.getCombustion(1, "FOR EACH ctSisCombAuto WHERE ctSisCombAuto.cCveCia = '"
				+ objUsuario.getCompania() + "' AND ctSisCombAuto.cSistema = '" + combustion.getSistema() + "' NO-LOCK:");

		if (this.combustionRep.getResultado()) {
			mav.setViewName(FORM_UPD);
			mav.addObject("error", this.combustionRep.getMensaje());
			mav.addObject("combustion", combustion);
			return mav;
		}

		this.combustionRep.actulizar(objUsuario.getUsuario(), viejo, combustion);

		if (this.combustionRep.getResultado()) {
			mav.setViewName(FORM_UPD);
			mav.addObject("error", this.combustionRep.getMensaje());
			mav.addObject("combustion", combustion);
			//return mav;
		} else {
			mav.setViewName(REDIRECT);
			//return mav;
		}
		
		return mav;
	}

	@GetMapping("/eliminar")
	public @ResponseBody String eliminar(@ModelAttribute("Usuario") SessionUsu objUsuario,
			@RequestParam(name = "cSistema", required = true) String cSistema) {

		String cMensaje = null;
		Combustion combustion = new Combustion();

		combustion = this.combustionRep.getCombustion(1, "FOR EACH ctSisCombAuto WHERE ctSisCombAuto.cCveCia = '"
				+ objUsuario.getCompania() + "' AND ctSisCombAuto.cSistema = '" + cSistema + "' NO-LOCK:");

		if (this.combustionRep.getResultado()) {
			cMensaje = this.combustionRep.getMensaje();
		} else {
			this.combustionRep.eliminar(objUsuario.getUsuario(), combustion);
			if (this.combustionRep.getResultado()) {
				cMensaje = this.combustionRep.getMensaje();
			}
		}

		if (cMensaje == null || cMensaje == "")
			cMensaje = "success";

		return cMensaje;
	}

}
