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

import com.sinergitec.yacattm.model.ct.EstatusOS;
import com.sinergitec.yacattm.model.ct.SessionUsu;
import com.sinergitec.yacattm.repos.cat.EstatusOSRep;

/**
 * Autor: Aestrada Fecha: 08 de junio de 2017 Descripcion: Se encarga de las
 * peticiones para el catalogo estatus de OS
 * 
 **/

@Controller
@SessionAttributes("Usuario")
@RequestMapping("/ope/ctEstatusOS")
public class EstatusOSCtrl {

	private static final String VIEW = "/ope/cat/ctEstatusOSV";
	private static final String FORM_ADD = "/ope/cat/ctEstatusOSAddF";
	private static final String FORM_UPD = "/ope/cat/ctEstatusOSUpdF";
	private static final String REDIRECT = "redirect:/ope/ctEstatusOS/lista";

	private String cError;

	@Autowired
	private EstatusOSRep estatusOSRep;

	@GetMapping("")
	public String redireccion() {
		return REDIRECT;
	}

	@GetMapping("/lista")
	public ModelAndView listaAllEstatusOS(@ModelAttribute("Usuario") SessionUsu objUsuario) {

		ModelAndView mav = new ModelAndView(VIEW);
		mav.addObject("titulo", "Estatus Orden de Servicio");
		mav.addObject("listaEstatusOS", estatusOSRep.listaEstatusOS(0,
				"FOR EACH ctEstatusOS WHERE ctEstatusOS.cCveCia = '" + objUsuario.getCompania() + "' NO-LOCK:"));

		if (this.estatusOSRep.getResultado()) {
			cError = estatusOSRep.getMensaje();
		}

		mav.addObject("error", cError);
		cError = "";

		return mav;
	}

	@GetMapping("/nuevo")
	public ModelAndView nuevo(@ModelAttribute("Usuario") SessionUsu objUsuario) {
		ModelAndView mav = new ModelAndView(FORM_ADD);
		EstatusOS estatusOS = new EstatusOS();
		estatusOS.setCompania(objUsuario.getCompania());
		estatusOS.setActivo(true);
		estatusOS.setRowid(null);
		mav.addObject("estatusOS", estatusOS);

		return mav;
	}

	@PostMapping("/agregar")
	public ModelAndView agregar(@ModelAttribute("Usuario") SessionUsu objUsuario,
			@ModelAttribute("estatusOS") EstatusOS estatusOS) {

		ModelAndView mav = new ModelAndView();

		this.estatusOSRep.agregar(objUsuario.getUsuario(), estatusOS);

		if (this.estatusOSRep.getResultado()) {
			mav.setViewName(FORM_ADD);
			mav.addObject("estatusOS", estatusOS);
			mav.addObject("error", this.estatusOSRep.getMensaje());
		} else {
			mav.setViewName(REDIRECT);
		}

		return mav;
	}

	@GetMapping("/getEstatusOS")
	public ModelAndView getEstatusOS(@ModelAttribute("Usuario") SessionUsu objUsuario,
			@RequestParam(name = "cEstatus", required = true) String cEstatus) {

		ModelAndView mav = new ModelAndView();
		EstatusOS estatusOS = new EstatusOS();

		estatusOS = this.estatusOSRep.getEstatusOS(1, "FOR EACH ctEstatusOS WHERE ctEstatusOS.cCveCia = '"
				+ objUsuario.getCompania() + "' AND ctEstatusOS.cEstatus = '" + cEstatus + "' NO-LOCK:");

		if (this.estatusOSRep.getResultado()) {

			mav.setViewName(REDIRECT);
			cError = this.estatusOSRep.getMensaje();
			return mav;

		} else {
			mav.setViewName(FORM_UPD);
			mav.addObject("estatusOS", estatusOS);
			mav.addObject("error", this.estatusOSRep.getMensaje());
			cError = null;
			return mav;
		}
	}

	@PostMapping("/actualizar")
	public ModelAndView actualizar(@ModelAttribute("Usuario") SessionUsu objUsuario,
			@ModelAttribute("estatusOS") EstatusOS estatusOS) {

		ModelAndView mav = new ModelAndView();
		EstatusOS viejo = new EstatusOS();
		viejo = this.estatusOSRep.getEstatusOS(1, "FOR EACH ctEstatusOS WHERE ctEstatusOS.cCveCia = '"
				+ objUsuario.getCompania() + "' AND ctEstatusOS.cEstatus = '" + estatusOS.getEstatus() + "' NO-LOCK:");

		if (this.estatusOSRep.getResultado()) {
			mav.setViewName(FORM_UPD);
			mav.addObject("error", this.estatusOSRep.getMensaje());
			mav.addObject("estatusOS", estatusOS);
		}

		this.estatusOSRep.actulizar(objUsuario.getUsuario(), viejo, estatusOS);

		if (this.estatusOSRep.getResultado()) {
			mav.setViewName(FORM_UPD);
			mav.addObject("error", this.estatusOSRep.getMensaje());
			mav.addObject("estatusOS", estatusOS);
		} else {
			mav.setViewName(REDIRECT);
		}

		return mav;
	}

	@GetMapping("/eliminar")
	public @ResponseBody String eliminar(@ModelAttribute("Usuario") SessionUsu objUsuario,
			@RequestParam(name = "cEstatus", required = true) String cEstatus) {

		String cMensaje = null;
		EstatusOS estatusOS = new EstatusOS();

		estatusOS = this.estatusOSRep.getEstatusOS(1, "FOR EACH ctEstatusOS WHERE ctEstatusOS.cCveCia = '"
				+ objUsuario.getCompania() + "' AND ctEstatusOS.cEstatus = '" + cEstatus + "' NO-LOCK:");

		if (this.estatusOSRep.getResultado()) {
			cMensaje = this.estatusOSRep.getMensaje();
		} else {
			this.estatusOSRep.eliminar(objUsuario.getUsuario(), estatusOS);
			if (this.estatusOSRep.getResultado()) {
				cMensaje = this.estatusOSRep.getMensaje();
			}
		}

		if (cMensaje == null || cMensaje == "")
			cMensaje = "success";

		return cMensaje;
	}

}