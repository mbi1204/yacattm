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

import com.sinergitec.yacattm.model.ct.MarcaAuto;
import com.sinergitec.yacattm.model.ct.SessionUsu;
import com.sinergitec.yacattm.repos.cat.MarcaAutoRep;

/**
 * Autor: Aestrada Fecha: 23 de junio de 2017 Descripcion: Se encarga de las
 * peticiones para el catalogo de marcas de auto
 * 
 **/

@Controller
@SessionAttributes("Usuario")
@RequestMapping("/ope/ctMarcaVehiculo")
public class MarcaAutoCtrl {

	private static final String VIEW = "/ope/cat/ctMarcaModeloV";
	private static final String FORM_ADD = "/ope/cat/ctMarcaAutoAddF";
	private static final String FORM_UPD = "/ope/cat/ctMarcaAutoUpdF";
	private static final String REDIRECT = "redirect:/ope/ctMarcaVehiculo/lista";

	private String cError;

	@Autowired
	private MarcaAutoRep marcaAutoRep;

	@GetMapping("")
	public String redireccion() {
		return REDIRECT;
	}

	@GetMapping("/lista")
	public ModelAndView listaAllMarcaVehiculos(@ModelAttribute("Usuario") SessionUsu objUsuario) {

		ModelAndView mav = new ModelAndView(VIEW);
		mav.addObject("titulo", "Marca de Vehículo");
		mav.addObject("listaMarcaAuto", marcaAutoRep.listaMarcasAuto(0,
				"FOR EACH ctAutoMarca WHERE ctAutoMarca.cCveCia = '" + objUsuario.getCompania() + "' NO-LOCK:"));

		if (this.marcaAutoRep.getResultado()) {
			cError = marcaAutoRep.getMensaje();
		}

		mav.addObject("error", cError);
		cError = "";

		return mav;
	}

	@GetMapping("/nuevo")
	public ModelAndView nuevo(@ModelAttribute("Usuario") SessionUsu objUsuario) {
		ModelAndView mav = new ModelAndView(FORM_ADD);
		MarcaAuto marcaAuto = new MarcaAuto();
		marcaAuto.setCompania(objUsuario.getCompania());
		marcaAuto.setActivo(true);
		marcaAuto.setRowid(null);
		mav.addObject("marcaAuto", marcaAuto);

		return mav;
	}

	@PostMapping("/agregar")
	public ModelAndView agregar(@ModelAttribute("Usuario") SessionUsu objUsuario,
			@ModelAttribute("marcaAuto") MarcaAuto marcaAuto) {

		ModelAndView mav = new ModelAndView();

		this.marcaAutoRep.agregar(objUsuario.getUsuario(), marcaAuto);

		if (this.marcaAutoRep.getResultado()) {
			mav.setViewName(FORM_ADD);
			mav.addObject("marcaAuto", marcaAuto);
			mav.addObject("error", this.marcaAutoRep.getMensaje());
		} else {
			mav.setViewName(REDIRECT);
		}

		return mav;
	}

	@GetMapping("/getMarcaVehiculo")
	public ModelAndView getMarcaVehiculo(@ModelAttribute("Usuario") SessionUsu objUsuario,
			@RequestParam(name = "cMarca", required = true) String cMarca) {

		ModelAndView mav = new ModelAndView();
		MarcaAuto marcaAuto = new MarcaAuto();

		marcaAuto = this.marcaAutoRep.getMarcaAuto(1, "FOR EACH ctAutoMarca WHERE ctAutoMarca.cCveCia = '"
				+ objUsuario.getCompania() + "' AND ctAutoMarca.cMarca = '" + cMarca + "' NO-LOCK:");

		if (this.marcaAutoRep.getResultado()) {

			mav.setViewName(REDIRECT);
			cError = this.marcaAutoRep.getMensaje();
			return mav;

		} else {
			mav.setViewName(FORM_UPD);
			mav.addObject("marcaAuto", marcaAuto);
			mav.addObject("error", this.marcaAutoRep.getMensaje());
			cError = null;
			return mav;
		}
	}

	@PostMapping("/actualizar")
	public ModelAndView actualizar(@ModelAttribute("Usuario") SessionUsu objUsuario,
			@ModelAttribute("marcaAuto") MarcaAuto marcaAuto) {

		ModelAndView mav = new ModelAndView();
		MarcaAuto viejo = new MarcaAuto();
		viejo = this.marcaAutoRep.getMarcaAuto(1, "FOR EACH ctAutoMarca WHERE ctAutoMarca.cCveCia = '"
				+ objUsuario.getCompania() + "' AND ctAutoMarca.cMarca = '" + marcaAuto.getMarca() + "' NO-LOCK:");

		if (this.marcaAutoRep.getResultado()) {
			mav.setViewName(FORM_UPD);
			mav.addObject("error", this.marcaAutoRep.getMensaje());
			mav.addObject("marcaAuto", marcaAuto);
		}

		this.marcaAutoRep.actulizar(objUsuario.getUsuario(), viejo, marcaAuto);

		if (this.marcaAutoRep.getResultado()) {
			mav.setViewName(FORM_UPD);
			mav.addObject("error", this.marcaAutoRep.getMensaje());
			mav.addObject("marcaAuto", marcaAuto);
		} else {
			mav.setViewName(REDIRECT);
		}

		return mav;
	}

	@GetMapping("/eliminar")
	public @ResponseBody String eliminar(@ModelAttribute("Usuario") SessionUsu objUsuario,
			@RequestParam(name = "cMarca", required = true) String cMarca) {

		String cMensaje = null;
		MarcaAuto marcaAuto = new MarcaAuto();

		marcaAuto = this.marcaAutoRep.getMarcaAuto(1, "FOR EACH ctAutoMarca WHERE ctAutoMarca.cCveCia = '"
				+ objUsuario.getCompania() + "' AND ctAutoMarca.cMarca = '" + cMarca + "' NO-LOCK:");

		if (this.marcaAutoRep.getResultado()) {
			cMensaje = this.marcaAutoRep.getMensaje();
		} else {
			this.marcaAutoRep.eliminar(objUsuario.getUsuario(), marcaAuto);
			if (this.marcaAutoRep.getResultado()) {
				cMensaje = this.marcaAutoRep.getMensaje();
			}
		}

		if (cMensaje == null || cMensaje == "")
			cMensaje = "success";

		return cMensaje;
	}

}
