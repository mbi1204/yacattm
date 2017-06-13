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

	static final String VIEW     = "/ope/cat/ctDireccionAutoV";
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
	public ModelAndView List(@ModelAttribute("Usuario") SessionUsu objUsuario) {	
		
		ModelAndView mav = new ModelAndView(VIEW);
		mav.addObject("direccionAuto", new ColorAuto());
		mav.addObject("Lista", direccionAutoRep.Lista(0, "FOR EACH ctDireccionAuto WHERE ctDireccionAuto.cCveCia = '" + objUsuario.getCompania() + "' NO-LOCK:"));
			
		
		if (direccionAutoRep.getResultado()) {
			cError = direccionAutoRep.getMensaje();
		}

		mav.addObject("error", cError);		
		cError = "";
		
		return mav;

	}
	

	@GetMapping("/nuevo")
	public ModelAndView nuevo( @ModelAttribute("Usuario") SessionUsu objUsuario) {
		DireccionAuto nuevo = new DireccionAuto();
		nuevo.setCompania(objUsuario.getCompania());
		nuevo.setActivo(true);
		nuevo.setRowid(null);
		ModelAndView mav = new ModelAndView(FORM_ADD);		
		mav.addObject("direccionAuto", nuevo);

		return mav;
	}
	
	@RequestMapping(value = "/agregar", method = RequestMethod.POST)
	public ModelAndView agregar(@ModelAttribute("direccionAuto") DireccionAuto direccionAuto , @ModelAttribute("Usuario") SessionUsu objUsuario) {
		this.direccionAutoRep.agregar(objUsuario.getUsuario(), direccionAuto);

		if (this.direccionAutoRep.getResultado()) { // error
			ModelAndView mav = new ModelAndView(FORM_ADD);
			mav.addObject("colorAuto", direccionAuto);
			mav.addObject("error", this.direccionAutoRep.getMensaje());

			return mav;

		} else {
			ModelAndView mav = new ModelAndView(REDIRECT);
			return mav;
		}

	}
	
	
	@GetMapping("/getColorAuto")
	public ModelAndView getColorAuto(@RequestParam(name = "cDireccion", required = true) String cDirecion , @ModelAttribute("Usuario") SessionUsu objUsuario) {
		DireccionAuto direccionAuto = new DireccionAuto();

		direccionAuto = this.direccionAutoRep.getDireccionAuto(1,
				"FOR EACH ctColorAuto WHERE ctColorAuto.cCveCia = '"  + objUsuario.getCompania() + "' AND ctColorAuto.cColor = '" + cDirecion
						+ "' NO-LOCK:");

		if (this.direccionAutoRep.getResultado()) { // error
			ModelAndView mav = new ModelAndView(REDIRECT);
			cError = this.direccionAutoRep.getMensaje();
			return mav;
		} else {
			
			ModelAndView mav = new ModelAndView(FORM_UPD);
			mav.addObject("direccionAuto", direccionAuto);
			mav.addObject("error", this.direccionAutoRep.getMensaje());
			cError = null;
			return mav;
		}

	}
	
	
	@RequestMapping(value = "/actualizar", method = RequestMethod.POST)
	public ModelAndView actualizar(@ModelAttribute("colorAuto") DireccionAuto direccionAuto ,@ModelAttribute("Usuario") SessionUsu objUsuario) {
		DireccionAuto viejo = new DireccionAuto();
		
		viejo = this.direccionAutoRep.getDireccionAuto(2, "FOR EACH ctColorAuto WHERE ctColorAuto.cCveCia = '"
				+ direccionAuto.getCompania() + "' AND ctColorAuto.cColor = '" + direccionAuto.getDireccion() + " ' NO-LOCK:");

		if (this.direccionAutoRep.getResultado()) { // error
			ModelAndView mav = new ModelAndView(FORM_UPD);
			mav.addObject("error", this.direccionAutoRep.getMensaje());
			mav.addObject("colorAuto", direccionAuto);
			return mav;
		}

		this.direccionAutoRep.actulizar(objUsuario.getUsuario(), viejo, direccionAuto);
		
		

		if (this.direccionAutoRep.getResultado()) { // error
			ModelAndView mav = new ModelAndView(FORM_UPD);
			mav.addObject("error", this.direccionAutoRep.getMensaje());
			mav.addObject("direccionAuto", direccionAuto);
			return mav;

		} else {
			ModelAndView mav = new ModelAndView(REDIRECT);
			return mav;

		}

	}
	
	@GetMapping("/eliminar")
	public @ResponseBody String eliminar(@RequestParam(name = "cDireccion", required = true) String cDireccion, @ModelAttribute("Usuario") SessionUsu objUsuario) {

		String cMensaje = null;
		DireccionAuto direccionAuto = new DireccionAuto();

		direccionAuto = this.direccionAutoRep.getDireccionAuto(1,
				"FOR EACH ctColorAuto WHERE ctColorAuto.cCveCia = '" + objUsuario.getCompania() +"' AND ctColorAuto.cColor = '" + cDireccion
						+ "' NO-LOCK:");

		if (this.direccionAutoRep.getResultado()) { // error
			cMensaje = this.direccionAutoRep.getMensaje();
		} else {

			this.direccionAutoRep.eliminar(objUsuario.getUsuario(), direccionAuto);
			

			if (this.direccionAutoRep.getResultado()) { // error
				cMensaje = this.direccionAutoRep.getMensaje();

			}

		}

		if (cMensaje == null || cMensaje == "")
			cMensaje = "success";

	

		return cMensaje;

	}
	
	
}
