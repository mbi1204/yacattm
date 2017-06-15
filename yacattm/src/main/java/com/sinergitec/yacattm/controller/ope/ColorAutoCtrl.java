package com.sinergitec.yacattm.controller.ope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.sinergitec.yacattm.model.ct.ColorAuto;
import com.sinergitec.yacattm.model.ct.SessionUsu;
import com.sinergitec.yacattm.repos.cat.ColorAutoRep;

/**
 * 
 * @author imendoza Fecha :16/05/2017 Descripcion: Controlador de la tabla
 *         ColorAuto
 */

@Controller
@RequestMapping("/ope/ctColorAuto")
@SessionAttributes("Usuario")

public class ColorAutoCtrl {

	static final String VIEW = "/ope/cat/ctColorAutoV";
	static final String FORM_ADD = "/ope/cat/ctColorAutoAddF";
	static final String FORM_UPD = "/ope/cat/ctColorAutoUpdF";
	static final String REDIRECT = "redirect:/ope/ctColorAuto/lista";
	private String cError;

	@Autowired
	private ColorAutoRep colorAutoRep;

	@GetMapping("")
	public String redirecion() {
		return REDIRECT;
	}

	@GetMapping("/lista")
	public ModelAndView ListAllColorAuto(@ModelAttribute("Usuario") SessionUsu sessionUsu) {

		ModelAndView mav = new ModelAndView(VIEW);
		mav.addObject("colorAuto", new ColorAuto());
		mav.addObject("ListColorAuto", colorAutoRep.ListaColorAuto(0,
				"FOR EACH ctColorAuto WHERE ctColorAuto.cCveCia = '" + sessionUsu.getCompania() + "' NO-LOCK:"));

		if (colorAutoRep.getResultado()) {
			cError = colorAutoRep.getMensaje();
		}

		mav.addObject("error", cError);
		cError = "";

		return mav;

	}

	@GetMapping("/nuevo")
	public ModelAndView nuevo(@ModelAttribute("Usuario") SessionUsu objUsuario) {
		ColorAuto colorAuto = new ColorAuto();
		colorAuto.setCompania(objUsuario.getCompania());
		colorAuto.setActivo(true);
		colorAuto.setRowid(null);
		ModelAndView mav = new ModelAndView(FORM_ADD);
		mav.addObject("colorAuto", colorAuto);

		return mav;
	}

	@RequestMapping(value = "/agregar", method = RequestMethod.POST)
	public ModelAndView agregar(@ModelAttribute("colorAuto") ColorAuto colorAuto,
			@ModelAttribute("Usuario") SessionUsu sessionUsu) {
		this.colorAutoRep.agregar(sessionUsu.getUsuario(), colorAuto);
		ModelAndView mav = new ModelAndView();

		if (this.colorAutoRep.getResultado()) { // error
			mav.setViewName(FORM_ADD);
			mav.addObject("colorAuto", colorAuto);
			mav.addObject("error", this.colorAutoRep.getMensaje());
		} else {
			mav.setViewName(REDIRECT);
		}

		return mav;

	}

	@RequestMapping(value = "/actualizar", method = RequestMethod.POST)
	public ModelAndView actualizar(@ModelAttribute("colorAuto") ColorAuto colorAuto,
			@ModelAttribute("Usuario") SessionUsu sessionUsu) {
		ColorAuto viejo = new ColorAuto();
		ModelAndView mav = new ModelAndView();
		viejo = this.colorAutoRep.getColorAuto(2, "FOR EACH ctColorAuto WHERE ctColorAuto.cCveCia = '"
				+ colorAuto.getCompania() + "' AND ctColorAuto.cColor = '" + colorAuto.getColor() + " ' NO-LOCK:");

		if (this.colorAutoRep.getResultado()) { // error
			mav.setViewName(FORM_UPD);
			mav.addObject("error", this.colorAutoRep.getMensaje());
			mav.addObject("colorAuto", colorAuto);
			return mav;
		}

		this.colorAutoRep.actulizar(sessionUsu.getUsuario(), viejo, colorAuto);

		if (this.colorAutoRep.getResultado()) { // error
			mav.setViewName(FORM_UPD);
			mav.addObject("error", this.colorAutoRep.getMensaje());
			mav.addObject("colorAuto", colorAuto);

		} else {
			mav.setViewName(REDIRECT);
		}

		return mav;

	}

	@GetMapping("/eliminar")
	public @ResponseBody String eliminar(@RequestParam(name = "cColor", required = true) String cColor,
			@ModelAttribute("Usuario") SessionUsu sessionUsu) {

		String cMensaje = null;
		ColorAuto colorAuto = new ColorAuto();

		colorAuto = this.colorAutoRep.getColorAuto(1, "FOR EACH ctColorAuto WHERE ctColorAuto.cCveCia = '"
				+ sessionUsu.getCompania() + "' AND ctColorAuto.cColor = '" + cColor + "' NO-LOCK:");

		if (this.colorAutoRep.getResultado()) { // error
			cMensaje = this.colorAutoRep.getMensaje();
		} else {

			this.colorAutoRep.eliminar(sessionUsu.getUsuario(), colorAuto);

			if (this.colorAutoRep.getResultado()) { // error
				cMensaje = this.colorAutoRep.getMensaje();

			}

		}

		if (cMensaje == null || cMensaje == "")
			cMensaje = "success";

		return cMensaje;

	}

	@GetMapping("/getColorAuto")
	public ModelAndView getColorAuto(@RequestParam(name = "cColor", required = true) String cColor,
			@ModelAttribute("Usuario") SessionUsu sessionUsu) {
		ModelAndView mav = new ModelAndView();
		ColorAuto colorAuto = new ColorAuto();

		colorAuto = this.colorAutoRep.getColorAuto(1, "FOR EACH ctColorAuto WHERE ctColorAuto.cCveCia = '"
				+ sessionUsu.getCompania() + "' AND ctColorAuto.cColor = '" + cColor + "' NO-LOCK:");

		if (this.colorAutoRep.getResultado()) { // error
			mav.setViewName(REDIRECT);
			cError = this.colorAutoRep.getMensaje();

		} else {
			mav.setViewName(FORM_UPD);
			mav.addObject("colorAuto", colorAuto);
			mav.addObject("error", this.colorAutoRep.getMensaje());
			cError = null;
		}
		return mav;

	}

}
