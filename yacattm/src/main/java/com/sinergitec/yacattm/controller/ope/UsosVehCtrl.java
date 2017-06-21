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
import com.sinergitec.yacattm.model.ct.UsoVeh;
import com.sinergitec.yacattm.repos.cat.UsosVehRep;

/**
 * Autor: Aestrada Fecha: 08 de junio de 2017 Descripcion: Se encarga de las
 * peticiones para el catalogo de usos de vehiculo
 * 
 **/

@Controller
@SessionAttributes("Usuario")
@RequestMapping("/ope/ctUsosVehiculo")
public class UsosVehCtrl {
	
	private static final String VIEW = "/ope/cat/ctUsosVehV";
	private static final String FORM_ADD = "/ope/cat/ctUsosVehVAddF";
	private static final String FORM_UPD = "/ope/cat/ctUsosVehVUpdF";
	private static final String REDIRECT = "redirect:/ope/ctUsosVehV/lista";
	
	private String cError;

	@Autowired
	private UsosVehRep usosVehRep;

	@GetMapping("")
	public String redireccion() {
		return REDIRECT;
	}
	
	@GetMapping("/lista")
	public ModelAndView listaAllUsosVehiculos(@ModelAttribute("Usuario") SessionUsu objUsuario) {

		ModelAndView mav = new ModelAndView(VIEW);
		mav.addObject("titulo", "Uso de Vehículo");
		mav.addObject("listaUsosVeh", usosVehRep.listaUsosVehiculo(0,
				"FOR EACH ctUsoAuto WHERE ctUsoAuto.cCveCia = '" + objUsuario.getCompania() + "' NO-LOCK:"));

		if (this.usosVehRep.getResultado()) {
			cError = usosVehRep.getMensaje();
		}

		mav.addObject("error", cError);
		cError = "";

		return mav;
	}
	
	@GetMapping("/nuevo")
	public ModelAndView nuevo(@ModelAttribute("Usuario") SessionUsu objUsuario) {
		ModelAndView mav = new ModelAndView(FORM_ADD);
		UsoVeh usoVeh = new UsoVeh();
		usoVeh.setCompania(objUsuario.getCompania());
		usoVeh.setActivo(true);
		usoVeh.setRowid(null);
		mav.addObject("usosVeh", usoVeh);

		return mav;
	}
	
	@PostMapping("/agregar")
	public ModelAndView agregar(@ModelAttribute("Usuario") SessionUsu objUsuario,
			@ModelAttribute("usoVeh") UsoVeh usoVeh) {

		ModelAndView mav = new ModelAndView();

		this.usosVehRep.agregar(objUsuario.getUsuario(), usoVeh);

		if (this.usosVehRep.getResultado()) {
			mav.setViewName(FORM_ADD);
			mav.addObject("usoVeh", usoVeh);
			mav.addObject("error", this.usosVehRep.getMensaje());
		} else {
			mav.setViewName(REDIRECT);
		}

		return mav;
	}
	
	@GetMapping("/getUsoVehiculo")
	public ModelAndView getUsoVehiculo(@ModelAttribute("Usuario") SessionUsu objUsuario,
			@RequestParam(name = "cUso", required = true) String cUso) {

		ModelAndView mav = new ModelAndView();
		UsoVeh usoVeh = new UsoVeh();

		usoVeh = this.usosVehRep.getUsoVehiculo(1, "FOR EACH ctUsoAuto WHERE ctUsoAuto.cCveCia = '"
				+ objUsuario.getCompania() + "' AND ctUsoAuto.cUso = '" + cUso + "' NO-LOCK:");

		if (this.usosVehRep.getResultado()) {

			mav.setViewName(REDIRECT);
			cError = this.usosVehRep.getMensaje();
			return mav;

		} else {
			mav.setViewName(FORM_UPD);
			mav.addObject("usoVeh", usoVeh);
			mav.addObject("error", this.usosVehRep.getMensaje());
			cError = null;
			return mav;
		}
	}
	
	@PostMapping("/actualizar")
	public ModelAndView actualizar(@ModelAttribute("Usuario") SessionUsu objUsuario,
			@ModelAttribute("usoVeh") UsoVeh usoVeh) {

		ModelAndView mav = new ModelAndView();
		UsoVeh viejo = new UsoVeh();
		viejo = this.usosVehRep.getUsoVehiculo(1,
				"FOR EACH ctUsoAuto WHERE ctUsoAuto.cCveCia = '" + objUsuario.getCompania()
						+ "' AND ctUsoAuto.cUso = '" + usoVeh.getUso() + "' NO-LOCK:");

		if (this.usosVehRep.getResultado()) {
			mav.setViewName(FORM_UPD);
			mav.addObject("error", this.usosVehRep.getMensaje());
			mav.addObject("usoVeh", usoVeh);
		}

		this.usosVehRep.actulizar(objUsuario.getUsuario(), viejo, usoVeh);

		if (this.usosVehRep.getResultado()) {
			mav.setViewName(FORM_UPD);
			mav.addObject("error", this.usosVehRep.getMensaje());
			mav.addObject("usoVeh", usoVeh);
		} else {
			mav.setViewName(REDIRECT);
		}

		return mav;
	}
	
	@GetMapping("/eliminar")
	public @ResponseBody String eliminar(@ModelAttribute("Usuario") SessionUsu objUsuario,
			@RequestParam(name = "cUso", required = true) String cUso) {

		String cMensaje = null;
		UsoVeh usoVeh = new UsoVeh();

		usoVeh = this.usosVehRep.getUsoVehiculo(1, "FOR EACH ctUsoAuto WHERE ctUsoAuto.cCveCia = '"
				+ objUsuario.getCompania() + "' AND ctUsoAuto.cUso = '" + cUso + "' NO-LOCK:");

		if (this.usosVehRep.getResultado()) {
			cMensaje = this.usosVehRep.getMensaje();
		} else {
			this.usosVehRep.eliminar(objUsuario.getUsuario(), usoVeh);
			if (this.usosVehRep.getResultado()) {
				cMensaje = this.usosVehRep.getMensaje();
			}
		}

		if (cMensaje == null || cMensaje == "")
			cMensaje = "success";

		return cMensaje;
	}

}
