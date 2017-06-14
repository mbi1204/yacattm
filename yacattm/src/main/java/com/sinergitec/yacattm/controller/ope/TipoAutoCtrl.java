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
import com.sinergitec.yacattm.model.ct.TipoAuto;
import com.sinergitec.yacattm.repos.cat.TipoAutoRep;


/**
 * Autor: Aestrada Fecha: 08 de junio de 2017 Descripcion: Se encarga de las
 * peticiones para el catalogo de tipo de auto
 * 
 **/

@Controller
@SessionAttributes("Usuario")
@RequestMapping("/ope/ctTipoAuto")
public class TipoAutoCtrl {

	private static final String VIEW = "/ope/cat/ctTipoAutoV";
	private static final String FORM_ADD = "/ope/cat/ctTipoAutoAddF";
	private static final String FORM_UPD = "/ope/cat/ctTipoAutoUpdF";
	private static final String REDIRECT = "redirect:/ope/ctTipoAuto/lista";

	private String cError;

	@Autowired
	private TipoAutoRep tipoAutoRep;

	@GetMapping("")
	public String redireccion() {
		return REDIRECT;
	}
	
	@GetMapping("/lista")
	public ModelAndView listaAllTipoAuto(@ModelAttribute("Usuario") SessionUsu objUsuario) {

		ModelAndView mav = new ModelAndView(VIEW);
		mav.addObject("titulo", "Tipo de Vehículo");
		mav.addObject("listaTipo", tipoAutoRep.listaTipoAuto(0,
				"FOR EACH ctTipoAuto WHERE ctTipoAuto.cCveCia = '" + objUsuario.getCompania() + "' NO-LOCK:"));

		if (this.tipoAutoRep.getResultado()) {
			cError = tipoAutoRep.getMensaje();
		}

		mav.addObject("error", cError);
		cError = "";

		return mav;
	}
	
	@GetMapping("/nuevo")
	public ModelAndView nuevo(@ModelAttribute("Usuario") SessionUsu objUsuario) {
		ModelAndView mav = new ModelAndView(FORM_ADD);
		TipoAuto tipoAuto = new TipoAuto();
		tipoAuto.setCompania(objUsuario.getCompania());
		tipoAuto.setActivo(true);
		tipoAuto.setRowid(null);
		mav.addObject("tipoAuto", tipoAuto);

		return mav;
	}
	
	@PostMapping("/agregar")
	public ModelAndView agregar(@ModelAttribute("Usuario") SessionUsu objUsuario,
			@ModelAttribute("tipoAuto") TipoAuto tipoAuto) {

		ModelAndView mav = new ModelAndView();

		this.tipoAutoRep.agregar(objUsuario.getUsuario(), tipoAuto);

		if (this.tipoAutoRep.getResultado()) {
			mav.setViewName(FORM_ADD);
			mav.addObject("tipoAuto", tipoAuto);
			mav.addObject("error", this.tipoAutoRep.getMensaje());
		} else {
			mav.setViewName(REDIRECT);
		}

		return mav;
	}
	
	@GetMapping("/getTipoAuto")
	public ModelAndView getTipoAuto(@ModelAttribute("Usuario") SessionUsu objUsuario,
			@RequestParam(name = "cTipo", required = true) String cTipo) {

		ModelAndView mav = new ModelAndView();
		TipoAuto tipoAuto = new TipoAuto();

		tipoAuto = this.tipoAutoRep.getTipoAuto(1, "FOR EACH ctTipoAuto WHERE ctTipoAuto.cCveCia = '"
				+ objUsuario.getCompania() + "' AND ctTipoAuto.cTipo = '" + cTipo + "' NO-LOCK:");

		if (this.tipoAutoRep.getResultado()) {

			mav.setViewName(REDIRECT);
			cError = this.tipoAutoRep.getMensaje();
			return mav;

		} else {
			mav.setViewName(FORM_UPD);
			mav.addObject("tipoAuto", tipoAuto);
			mav.addObject("error", this.tipoAutoRep.getMensaje());
			cError = null;
			return mav;
		}
	}
	
	@PostMapping("/actualizar")
	public ModelAndView actualizar(@ModelAttribute("Usuario") SessionUsu objUsuario,
			@ModelAttribute("tipoAuto") TipoAuto tipoAuto) {

		ModelAndView mav = new ModelAndView();
		TipoAuto viejo = new TipoAuto();
		viejo = this.tipoAutoRep.getTipoAuto(1, "FOR EACH ctTipoAuto WHERE ctTipoAuto.cCveCia = '"
				+ objUsuario.getCompania() + "' AND ctTipoAuto.cTipo = '" + tipoAuto.getTipo() + "' NO-LOCK:");

		if (this.tipoAutoRep.getResultado()) {
			mav.setViewName(FORM_UPD);
			mav.addObject("error", this.tipoAutoRep.getMensaje());
			mav.addObject("tipoAuto", tipoAuto);
		}

		this.tipoAutoRep.actulizar(objUsuario.getUsuario(), viejo, tipoAuto);

		if (this.tipoAutoRep.getResultado()) {
			mav.setViewName(FORM_UPD);
			mav.addObject("error", this.tipoAutoRep.getMensaje());
			mav.addObject("tipoAuto", tipoAuto);
		} else {
			mav.setViewName(REDIRECT);
		}

		return mav;
	}
	
	@GetMapping("/eliminar")
	public @ResponseBody String eliminar(@ModelAttribute("Usuario") SessionUsu objUsuario,
			@RequestParam(name = "cTipo", required = true) String cTipo) {

		String cMensaje = null;
		TipoAuto tipoAuto = new TipoAuto();

		tipoAuto = this.tipoAutoRep.getTipoAuto(1, "FOR EACH ctTipoAuto WHERE ctTipoAuto.cCveCia = '"
				+ objUsuario.getCompania() + "' AND ctTipoAuto.cTipo = '" + cTipo + "' NO-LOCK:");

		if (this.tipoAutoRep.getResultado()) {
			cMensaje = this.tipoAutoRep.getMensaje();
		} else {
			this.tipoAutoRep.eliminar(objUsuario.getUsuario(), tipoAuto);
			if (this.tipoAutoRep.getResultado()) {
				cMensaje = this.tipoAutoRep.getMensaje();
			}
		}

		if (cMensaje == null || cMensaje == "")
			cMensaje = "success";

		return cMensaje;
	}
	
}
