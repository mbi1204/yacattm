package com.sinergitec.yacattm.controller.ope;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.sinergitec.yacattm.model.ct.SessionUsu;

/**
 * Autor: Aestrada Fecha: 26 de junio de 2017 Descripcion: Se encarga de las
 * peticiones de la orden de servicio
 * 
 **/

@Controller
@SessionAttributes("Usuario")
@RequestMapping("/ope/gnOrdenServicio")
public class OrdenServicioCtrl {

	private static final String VIEW = "/ope/gen/opeAltaOSV";
	private static final String FORM_ADD = "/ope/cat/ctUsosVehVAddF";
	private static final String FORM_UPD = "/ope/cat/ctUsosVehVUpdF";
	private static final String REDIRECT = "redirect:/ope/gnOrdenServicio/ordenservicio";

	private String cError;

	@GetMapping("")
	public String redireccion() {
		return REDIRECT;
	}

	@GetMapping("/ordenservicio")
	public ModelAndView listaAllUsosVehiculos(@ModelAttribute("Usuario") SessionUsu objUsuario) {

		ModelAndView mav = new ModelAndView(VIEW);
		mav.addObject("titulo", "Orden de Servicio");
		mav.addObject("error", cError);
		cError = "";

		return mav;
	}

}
