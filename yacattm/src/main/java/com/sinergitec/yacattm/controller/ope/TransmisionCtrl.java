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

import com.sinergitec.yacattm.model.ct.SessionUsu;
import com.sinergitec.yacattm.model.ct.Transmision;
import com.sinergitec.yacattm.repos.cat.TransmisionRep;

/**
 * Autor: Aestrada Fecha: 08 de junio de 2017 Descripcion: Se encarga de las
 * peticiones para el catalogo de transmision
 * 
 **/

@Controller
@SessionAttributes("Usuario")
@RequestMapping("/ope/ctTransmision")
public class TransmisionCtrl {

	private static final String VIEW = "/ope/cat/ctTransmisionV";
	private static final String FORM_ADD = "/ope/cat/ctTransmisionAddF";
	private static final String FORM_UPD = "/ope/cat/ctTransmisionUpdF";
	private static final String REDIRECT = "redirect:/ope/ctTransmision/lista";

	private String cError;

	@Autowired
	private TransmisionRep transmisionRep;

	@GetMapping("")
	public String redireccion() {
		return REDIRECT;
	}

	@GetMapping("/lista")
	public ModelAndView listaAllTransmisiones(@ModelAttribute("Usuario") SessionUsu objUsuario) {

		ModelAndView mav = new ModelAndView(VIEW);
		mav.addObject("titulo", "Tipo de Vehículo");
		mav.addObject("listaTrans", transmisionRep.listaTransmision(0,
				"FOR EACH ctTrasmAuto WHERE ctTrasmAuto.cCveCia = '" + objUsuario.getCompania() + "' NO-LOCK:"));

		if (this.transmisionRep.getResultado()) {
			cError = transmisionRep.getMensaje();
		}

		mav.addObject("error", cError);
		cError = "";

		return mav;
	}

	@GetMapping("/nuevo")
	public ModelAndView nuevo(@ModelAttribute("Usuario") SessionUsu objUsuario) {
		ModelAndView mav = new ModelAndView(FORM_ADD);
		Transmision transmision = new Transmision();
		transmision.setCompania(objUsuario.getCompania());
		transmision.setActivo(true);
		transmision.setRowid(null);
		mav.addObject("transmision", transmision);

		return mav;
	}

	@PostMapping("/agregar")
	public ModelAndView agregar(@ModelAttribute("Usuario") SessionUsu objUsuario,
			@ModelAttribute("transmision") Transmision transmision) {

		ModelAndView mav = new ModelAndView();

		this.transmisionRep.agregar(objUsuario.getUsuario(), transmision);

		if (this.transmisionRep.getResultado()) {
			mav.setViewName(FORM_ADD);
			mav.addObject("transmision", transmision);
			mav.addObject("error", this.transmisionRep.getMensaje());
		} else {
			mav.setViewName(REDIRECT);
		}

		return mav;
	}

	@GetMapping("/getTransmision")
	public ModelAndView getTipoAuto(@ModelAttribute("Usuario") SessionUsu objUsuario,
			@RequestParam(name = "cTransmision", required = true) String cTransmision) {

		ModelAndView mav = new ModelAndView();
		Transmision transmision = new Transmision();

		transmision = this.transmisionRep.getTransmision(1, "FOR EACH ctTrasmAuto WHERE ctTrasmAuto.cCveCia = '"
				+ objUsuario.getCompania() + "' AND ctTrasmAuto.cTrasmision = '" + cTransmision + "' NO-LOCK:");

		if (this.transmisionRep.getResultado()) {

			mav.setViewName(REDIRECT);
			cError = this.transmisionRep.getMensaje();
			return mav;

		} else {
			mav.setViewName(FORM_UPD);
			mav.addObject("transmision", transmision);
			mav.addObject("error", this.transmisionRep.getMensaje());
			cError = null;
			return mav;
		}
	}

	@PostMapping("/actualizar")
	public ModelAndView actualizar(@ModelAttribute("Usuario") SessionUsu objUsuario,
			@ModelAttribute("transmision") Transmision transmision) {

		ModelAndView mav = new ModelAndView();
		Transmision viejo = new Transmision();
		viejo = this.transmisionRep.getTransmision(1,
				"FOR EACH ctTrasmAuto WHERE ctTrasmAuto.cCveCia = '" + objUsuario.getCompania()
						+ "' AND ctTrasmAuto.cTrasmision = '" + transmision.getTransmision() + "' NO-LOCK:");

		if (this.transmisionRep.getResultado()) {
			mav.setViewName(FORM_UPD);
			mav.addObject("error", this.transmisionRep.getMensaje());
			mav.addObject("transmision", transmision);
		}

		this.transmisionRep.actulizar(objUsuario.getUsuario(), viejo, transmision);

		if (this.transmisionRep.getResultado()) {
			mav.setViewName(FORM_UPD);
			mav.addObject("error", this.transmisionRep.getMensaje());
			mav.addObject("transmision", transmision);
		} else {
			mav.setViewName(REDIRECT);
		}

		return mav;
	}

	@GetMapping("/eliminar")
	public @ResponseBody String eliminar(@ModelAttribute("Usuario") SessionUsu objUsuario,
			@RequestParam(name = "cTransmision", required = true) String cTransmision) {

		String cMensaje = null;
		Transmision transmision = new Transmision();

		transmision = this.transmisionRep.getTransmision(1, "FOR EACH ctTrasmAuto WHERE ctTrasmAuto.cCveCia = '"
				+ objUsuario.getCompania() + "' AND ctTrasmAuto.cTrasmision = '" + cTransmision + "' NO-LOCK:");

		if (this.transmisionRep.getResultado()) {
			cMensaje = this.transmisionRep.getMensaje();
		} else {
			this.transmisionRep.eliminar(objUsuario.getUsuario(), transmision);
			if (this.transmisionRep.getResultado()) {
				cMensaje = this.transmisionRep.getMensaje();
			}
		}

		if (cMensaje == null || cMensaje == "")
			cMensaje = "success";

		return cMensaje;
	}

}
