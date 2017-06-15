package com.sinergitec.yacattm.controller.ope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.sinergitec.yacattm.model.ct.ColorAuto;
import com.sinergitec.yacattm.model.ct.DireccionAuto;
import com.sinergitec.yacattm.model.ct.SessionUsu;
import com.sinergitec.yacattm.repos.cat.DireccionAutoRep;

@Controller
@RequestMapping("/ope/ctDireccionAuto")
@SessionAttributes("Usuario")
public class DireccionAutoCtrl {

	static final String VIEW = "/ope/cat/ctDireccionAutoV";
	static final String REDIRECT = "redirect:/ope/ctDireccionAuto/lista";
	static final String FORM_ADD = "/ope/cat/ctDireccionAutoAddF";
	static final String FORM_UPD = "/ope/cat/ctDireccionAutoUpdF";
	private String cError;

	@Autowired
	private DireccionAutoRep direccionAutoRep;

	@GetMapping("")
	public String redirecion() {
		return REDIRECT;

	}

	@GetMapping("/lista")
	public ModelAndView Lista(@ModelAttribute("Usuario") SessionUsu sessionUsu) {

		ModelAndView mav = new ModelAndView(VIEW);
		mav.addObject("direccionAuto", new ColorAuto());
		mav.addObject("Lista", direccionAutoRep.Lista(0, "FOR EACH ctDireccionAuto WHERE ctDireccionAuto.cCveCia = '"
				+ sessionUsu.getCompania() + "' NO-LOCK:"));

		if (direccionAutoRep.getResultado()) {
			cError = direccionAutoRep.getMensaje();
		}

		mav.addObject("error", cError);
		cError = "";

		return mav;

	}

	@GetMapping("/nuevo")
	public ModelAndView nuevo(@ModelAttribute("Usuario") SessionUsu sessionUsu) {
		DireccionAuto nuevo = new DireccionAuto();
		nuevo.setCompania(sessionUsu.getCompania());
		nuevo.setActivo(true);
		nuevo.setRowid(null);
		ModelAndView mav = new ModelAndView(FORM_ADD);
		mav.addObject("direccionAuto", nuevo);

		return mav;
	}

	@RequestMapping(value = "/agregar", method = RequestMethod.POST)
	public ModelAndView agregar(@ModelAttribute("direccionAuto") DireccionAuto direccionAuto,
			@ModelAttribute("Usuario") SessionUsu sessionUsu) {
		this.direccionAutoRep.agregar(sessionUsu.getUsuario(), direccionAuto);

		ModelAndView mav = new ModelAndView();

		if (this.direccionAutoRep.getResultado()) { // error
			mav.setViewName(FORM_ADD);
			mav.addObject("colorAuto", direccionAuto);
			mav.addObject("error", this.direccionAutoRep.getMensaje());

		} else {
			mav.setViewName(REDIRECT);

		}

		return mav;
	}

	@GetMapping("/getDireccionAuto")
	public ModelAndView getColorAuto(@RequestParam(name = "cDireccion", required = true) String cDireccion,
			@ModelAttribute("Usuario") SessionUsu sessionUsu) {
		

		ModelAndView mav = new ModelAndView();
		DireccionAuto direccionAuto = new DireccionAuto();

		direccionAuto = this.direccionAutoRep.getDireccionAuto(1,
				"FOR EACH ctDireccionAuto WHERE ctDireccionAuto.cCveCia = '" + sessionUsu.getCompania()
						+ "' AND ctDireccionAuto.cDireccion = '" + cDireccion + "' NO-LOCK:");
		
		System.out.println("antes " +  direccionAuto.toString());
		
	

		if (this.direccionAutoRep.getResultado()) { // error
			System.out.println("hubo error");
			mav.setViewName(REDIRECT);
			cError = this.direccionAutoRep.getMensaje();

		} else {
			System.out.println("no error hubo error");
			mav.setViewName(FORM_UPD);
			mav.addObject("direccionAuto", direccionAuto);
			mav.addObject("error", this.direccionAutoRep.getMensaje());
			cError = null;

		}
		
		
		System.out.println(mav.getViewName());
		System.out.println(direccionAuto.toString());

		return mav;
	}

	@RequestMapping(value = "/actualizar", method = RequestMethod.POST)
	public ModelAndView actualizar(@ModelAttribute("colorAuto") DireccionAuto direccionAuto,
			@ModelAttribute("Usuario") SessionUsu sessionUsu) {

		ModelAndView mav = new ModelAndView();
		DireccionAuto viejo = new DireccionAuto();

		viejo = this.direccionAutoRep.getDireccionAuto(2,
				"FOR EACH ctDireccionAuto WHERE ctDireccionAuto.cCveCia = '" + direccionAuto.getCompania()
						+ "' AND ctDireccionAuto.cDireccion = '" + direccionAuto.getDireccion() + " ' NO-LOCK:");

		if (this.direccionAutoRep.getResultado()) { // error
			mav.setViewName(FORM_UPD);
			mav.addObject("error", this.direccionAutoRep.getMensaje());
			mav.addObject("colorAuto", direccionAuto);
			return mav;
		}

		this.direccionAutoRep.actulizar(sessionUsu.getUsuario(), viejo, direccionAuto);

		if (this.direccionAutoRep.getResultado()) { // error
			mav.setViewName(FORM_UPD);
			mav.addObject("error", this.direccionAutoRep.getMensaje());
			mav.addObject("direccionAuto", direccionAuto);

		} else {
			mav.setViewName(REDIRECT);

		}
		return mav;
	}

	@GetMapping("/eliminar")
	public @ResponseBody String eliminar(@RequestParam(name = "cDireccion", required = true) String cDireccion,
			@ModelAttribute("Usuario") SessionUsu sessionUsu) {

		String cMensaje = null;
		DireccionAuto direccionAuto = new DireccionAuto();

		direccionAuto = this.direccionAutoRep.getDireccionAuto(1, "FOR EACH ctColorAuto WHERE ctColorAuto.cCveCia = '"
				+ sessionUsu.getCompania() + "' AND ctColorAuto.cColor = '" + cDireccion + "' NO-LOCK:");

		if (this.direccionAutoRep.getResultado()) { // error
			cMensaje = this.direccionAutoRep.getMensaje();
		} else {

			this.direccionAutoRep.eliminar(sessionUsu.getUsuario(), direccionAuto);

			if (this.direccionAutoRep.getResultado()) { // error
				cMensaje = this.direccionAutoRep.getMensaje();

			}

		}

		if (cMensaje == null || cMensaje == "")
			cMensaje = "success";

		return cMensaje;

	}

}
