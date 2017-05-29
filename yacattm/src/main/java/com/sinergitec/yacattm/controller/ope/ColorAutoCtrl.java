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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.sinergitec.yacattm.model.ct.ColorAuto;
import com.sinergitec.yacattm.repos.seg.ColorAutoRep;

/**
 * 
 * @author imendoza Fecha :16/05/2017 Descripcion: Controlador de la tabla
 *         ColorAuto
 */

@Controller
@RequestMapping("/ope/ctColorAuto")

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
	public ModelAndView ListAllColorAuto() {
		ModelAndView mav = new ModelAndView(VIEW);

		mav.addObject("colorAuto", new ColorAuto());
		mav.addObject("ListColorAuto",
				colorAutoRep.ListaColorAuto(0, "FOR EACH ctColorAuto WHERE ctColorAuto.cCveCia = 'AUTOTEC' NO-LOCK:"));
		
		if (colorAutoRep.getResultado()) {
			cError = colorAutoRep.getMensaje();
		}

		mav.addObject("error", cError);		
		cError = "";
		
		return mav;

	}

	@GetMapping("/nuevo")
	public ModelAndView nuevo() {
		ColorAuto colorAuto = new ColorAuto();
		colorAuto.setCompania("AUTOTEC");
		colorAuto.setActivo(true);
		colorAuto.setRowid(null);
		ModelAndView mav = new ModelAndView(FORM_ADD);
		mav.addObject("colorAuto", colorAuto);

		return mav;
	}

	@RequestMapping(value = "/agregar", method = RequestMethod.POST)
	public ModelAndView agregar(@ModelAttribute("colorAuto") ColorAuto colorAuto) {
		this.colorAutoRep.agregar("SISIMB", colorAuto);

		if (this.colorAutoRep.getResultado()) { // error
			ModelAndView mav = new ModelAndView(FORM_ADD);
			mav.addObject("colorAuto", colorAuto);
			mav.addObject("error", this.colorAutoRep.getMensaje());

			return mav;

		} else {
			ModelAndView mav = new ModelAndView(REDIRECT);
			return mav;
		}

	}

	@RequestMapping(value = "/actualizar", method = RequestMethod.POST)
	public ModelAndView actualizar(@ModelAttribute("colorAuto") ColorAuto colorAuto) {
		ColorAuto viejo = new ColorAuto();
		viejo = this.colorAutoRep.getColorAuto(2, "FOR EACH ctColorAuto WHERE ctColorAuto.cCveCia = '"
				+ colorAuto.getCompania() + "' AND ctColorAuto.cColor = '" + colorAuto.getColor() + "' NO-LOCK:");

		if (this.colorAutoRep.getResultado()) { // error
			ModelAndView mav = new ModelAndView(FORM_UPD);
			mav.addObject("error", this.colorAutoRep.getMensaje());
			mav.addObject("colorAuto", colorAuto);
			return mav;
		}

		this.colorAutoRep.actulizar("SISIMB", viejo, colorAuto);

		if (this.colorAutoRep.getResultado()) { // error
			ModelAndView mav = new ModelAndView(FORM_UPD);
			mav.addObject("error", this.colorAutoRep.getMensaje());
			mav.addObject("colorAuto", colorAuto);
			return mav;

		} else {
			ModelAndView mav = new ModelAndView(REDIRECT);
			return mav;

		}

	}

	@GetMapping("/eliminar")
	public @ResponseBody String eliminar(@RequestParam(name = "cColor", required = true) String cColor) {

		String cMensaje = null;
		ColorAuto colorAuto = new ColorAuto();

		colorAuto = this.colorAutoRep.getColorAuto(1,
				"FOR EACH ctColorAuto WHERE ctColorAuto.cCveCia = 'AUTOTEC' AND ctColorAuto.cColor = '" + cColor
						+ "' NO-LOCK:");

		if (this.colorAutoRep.getResultado()) { // error
			cMensaje = this.colorAutoRep.getMensaje();
		} else {

			this.colorAutoRep.eliminar("SISIMB", colorAuto);

			if (this.colorAutoRep.getResultado()) { // error
				cMensaje = this.colorAutoRep.getMensaje();

			}

		}

		if (cMensaje == null || cMensaje == "")
			cMensaje = "success";

	

		return cMensaje;

	}

	@GetMapping("/getColorAuto")
	public ModelAndView getColorAuto(@RequestParam(name = "cColor", required = true) String cColor) {
		ColorAuto colorAuto = new ColorAuto();

		colorAuto = this.colorAutoRep.getColorAuto(1,
				"FOR EACH ctColorAuto WHERE ctColorAuto.cCveCia = 'AUTOTEC' AND ctColorAuto.cColor = '" + cColor
						+ "' NO-LOCK:");

		if (this.colorAutoRep.getResultado()) { // error
			ModelAndView mav = new ModelAndView(REDIRECT);
			cError = this.colorAutoRep.getMensaje();
			return mav;
		} else {
			ModelAndView mav = new ModelAndView(FORM_UPD);
			mav.addObject("colorAuto", colorAuto);
			mav.addObject("error", this.colorAutoRep.getMensaje());
			cError = null;
			return mav;
		}

	}

}
