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

import com.google.gson.Gson;
import com.sinergitec.yacattm.model.ct.ModeloAuto;
import com.sinergitec.yacattm.model.ct.SessionUsu;
import com.sinergitec.yacattm.repos.cat.ModeloAutoRep;

/**
 * Autor: Aestrada Fecha: 24 de junio de 2017 Descripcion: Se encarga de las
 * peticiones para el catalogo de Modelos de Autos
 * 
 **/

@Controller
@SessionAttributes("Usuario")
@RequestMapping("/ope/ctModeloVehiculo")
public class ModeloAutoCtrl {

	private static final String FORM_ADD = "/ope/cat/ctModeloAutoAddF";
	private static final String FORM_UPD = "/ope/cat/ctModeloAutoUpdF";
	private static final String REDIRECT = "redirect:/ope/ctMarcaVehiculo/lista";

	private String cError;

	@Autowired
	private ModeloAutoRep modeloAutoRep;

	@GetMapping("")
	public String redireccion() {
		return REDIRECT;
	}

	@GetMapping("/lista")
	public @ResponseBody String lista(@ModelAttribute("Usuario") SessionUsu objUsuario,
			@RequestParam(name = "cMarca", required = true) String cMarca) {

		String cMensaje = null;

		cMensaje = new Gson()
				.toJson(this.modeloAutoRep.listaModeloAuto(0, "FOR EACH ctAutoModelo WHERE ctAutoModelo.cCveCia = '"
						+ objUsuario.getCompania() + "'" + "AND ctAutoModelo.cMarca = '" + cMarca + "' NO-LOCK:"));

		if (this.modeloAutoRep.getResultado()) {
			cMensaje = new Gson().toJson(this.modeloAutoRep.getMensaje());
		}

		return cMensaje;
	}

	@GetMapping("/nuevo")
	public ModelAndView nuevo(@ModelAttribute("Usuario") SessionUsu objUsuario,
			@RequestParam(name = "cMarca", required = true) String cMarca) {
		ModelAndView mav = new ModelAndView(FORM_ADD);
		ModeloAuto modeloAuto = new ModeloAuto();
		modeloAuto.setCompania(objUsuario.getCompania());
		modeloAuto.setMarca(cMarca);
		modeloAuto.setActivo(true);
		modeloAuto.setRowid(null);
		mav.addObject("modeloAuto", modeloAuto);

		return mav;
	}

	@PostMapping("/agregar")
	public ModelAndView agregar(@ModelAttribute("Usuario") SessionUsu objUsuario,
			@ModelAttribute("modeloAuto") ModeloAuto modeloAuto) {

		ModelAndView mav = new ModelAndView();

		this.modeloAutoRep.agregar(objUsuario.getUsuario(), modeloAuto);

		if (this.modeloAutoRep.getResultado()) {
			mav.setViewName(FORM_ADD);
			mav.addObject("marcaAuto", modeloAuto);
			mav.addObject("error", this.modeloAutoRep.getMensaje());
		} else {
			mav.setViewName(REDIRECT);
		}

		return mav;
	}

	@GetMapping("/getModeloVehiculo")
	public ModelAndView getModeloVehiculo(@ModelAttribute("Usuario") SessionUsu objUsuario,
			@RequestParam(name = "cMarca", required = true) String cMarca,
			@RequestParam(name = "cModelo", required = true) String cModelo) {

		ModelAndView mav = new ModelAndView();
		ModeloAuto modeloAuto = new ModeloAuto(); 

		modeloAuto = this.modeloAutoRep.getModeloAuto(1,
				"FOR EACH ctAutoModelo WHERE ctAutoModelo.cCveCia = '" + objUsuario.getCompania()
						+ "' AND ctAutoModelo.cMarca = '" + cMarca + "' AND ctAutoModelo.cModelo = '" + cModelo
						+ "' NO-LOCK:");

		if (this.modeloAutoRep.getResultado()) {

			mav.setViewName(REDIRECT);
			cError = this.modeloAutoRep.getMensaje();
			return mav;

		} else {
			mav.setViewName(FORM_UPD);
			mav.addObject("modeloAuto", modeloAuto);
			mav.addObject("error", this.modeloAutoRep.getMensaje());
			cError = null;
			return mav;
		}
	}

	@PostMapping("/actualizar")
	public ModelAndView actualizar(@ModelAttribute("Usuario") SessionUsu objUsuario,
			@ModelAttribute("modeloAuto") ModeloAuto modeloAuto) {

		ModelAndView mav = new ModelAndView();
		ModeloAuto viejo = new ModeloAuto();
		viejo = this.modeloAutoRep.getModeloAuto(1,
				"FOR EACH ctAutoModelo WHERE ctAutoModelo.cCveCia = '" + objUsuario.getCompania()
						+ "' AND ctAutoModelo.cMarca = '" + modeloAuto.getMarca() + "' AND ctAutoModelo.cModelo = '"
						+ modeloAuto.getModelo() + "' NO-LOCK:");

		if (this.modeloAutoRep.getResultado()) {
			mav.setViewName(FORM_UPD);
			mav.addObject("error", this.modeloAutoRep.getMensaje());
			mav.addObject("modeloAuto", modeloAuto);
		}

		this.modeloAutoRep.actulizar(objUsuario.getUsuario(), viejo, modeloAuto);

		if (this.modeloAutoRep.getResultado()) {
			mav.setViewName(FORM_UPD);
			mav.addObject("error", this.modeloAutoRep.getMensaje());
			mav.addObject("modeloAuto", modeloAuto);
		} else {
			mav.setViewName(REDIRECT);
		}

		return mav;
	}

	@GetMapping("/eliminar")
	public @ResponseBody String eliminar(@ModelAttribute("Usuario") SessionUsu objUsuario,
			@RequestParam(name = "cMarca", required = true) String cMarca,
			@RequestParam(name = "cModelo", required = true) String cModelo) {

		String cMensaje = null;
		ModeloAuto modeloAuto = new ModeloAuto();

		modeloAuto = this.modeloAutoRep.getModeloAuto(1,
				"FOR EACH ctAutoModelo WHERE ctAutoModelo.cCveCia = '" + objUsuario.getCompania()
						+ "' AND ctAutoModelo.cMarca = '" + cMarca + "' AND ctAutoModelo.cModelo = '" + cModelo
						+ "' NO-LOCK:");

		if (this.modeloAutoRep.getResultado()) {
			cMensaje = this.modeloAutoRep.getMensaje();
		} else {
			this.modeloAutoRep.eliminar(objUsuario.getUsuario(), modeloAuto);
			if (this.modeloAutoRep.getResultado()) {
				cMensaje = this.modeloAutoRep.getMensaje();
			}
		}

		if (cMensaje == null || cMensaje == "")
			cMensaje = "success";

		return cMensaje;
	}

}
