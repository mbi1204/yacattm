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
import com.sinergitec.yacattm.model.ct.SessionUsu;
import com.sinergitec.yacattm.model.ope.OrdenServicio;
import com.sinergitec.yacattm.repos.ope.OrdenServRep;

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
	private static final String FORM_ADD = "/ope/gen/opeAltaOSV";
	private static final String FORM_UPD = "/ope/cat/ctUsosVehVUpdF";
	private static final String REDIRECT = "redirect:/ope/gnOrdenServicio/ordenservicio";

	private String cError;
	
	@Autowired
	private OrdenServRep ordenServRep;

	@GetMapping("")
	public String redireccion() {
		return REDIRECT;
	}

	@GetMapping("/ordenservicio")
	public ModelAndView listaAllUsosVehiculos(@ModelAttribute("Usuario") SessionUsu objUsuario) {

		ModelAndView mav = new ModelAndView(VIEW);
		OrdenServicio ordenServicio = new OrdenServicio();
		ordenServicio.setCompania(objUsuario.getCompania());
		ordenServicio.setRowid(null);
		mav.addObject("titulo", "Orden de Servicio");
		mav.addObject("ordenServicio", ordenServicio);
		mav.addObject("error", cError);
		cError = "";

		return mav;
	}
	
	@GetMapping("/consulta")
	public @ResponseBody String consulta(@ModelAttribute("Usuario") SessionUsu objUsuario,
			@RequestParam(name = "cNombre", required = true) String cNombre,
			@RequestParam(name = "cMatricula", required = true) String cMatricula,
			@RequestParam(name = "cMarca", required = true) String cMarca,
			@RequestParam(name = "cModelo", required = true) String cModelo,
			@RequestParam(name = "cColor", required = true) String cColor) {
		
		String retorno = "";
		
		retorno = new Gson().toJson(
				ordenServRep.listaAutosCliente(objUsuario.getCompania(), cNombre, cMatricula, cMarca, cModelo, cColor));
		
		if(ordenServRep.getResultado()){
			retorno = new Gson().toJson(ordenServRep.getMensaje());
		}

		return retorno;
	}
	
	@PostMapping("/agregar")
	public ModelAndView agregar(@ModelAttribute("Usuario") SessionUsu objUsuario,
			@ModelAttribute("ordenServicio") OrdenServicio ordenServicio) {

		ModelAndView mav = new ModelAndView();

		ordenServicio = this.ordenServRep.agregar(objUsuario.getUsuario(), ordenServicio);

		if (this.ordenServRep.getResultado()) {
			mav.setViewName(VIEW);
			mav.addObject("ordenServicio", ordenServicio);
			mav.addObject("error", this.ordenServRep.getMensaje());
		} else {
			mav.setViewName(REDIRECT);
			mav.addObject("iOrdenServ", ordenServicio.getOrden());
		}

		return mav;
	}

}
