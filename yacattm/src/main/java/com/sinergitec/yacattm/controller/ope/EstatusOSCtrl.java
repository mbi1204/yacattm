package com.sinergitec.yacattm.controller.ope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.sinergitec.yacattm.model.ct.SessionUsu;
import com.sinergitec.yacattm.repos.cat.EstatusOSRep;

/**
 * Autor: Aestrada
 * Fecha: 08 de junio de 2017
 * Descripcion: Se encarga de las peticiones para
 * el catalogo estatus de OS
 * 
 **/

@Controller
@SessionAttributes("Usuario")
@RequestMapping("/ope/ctEstatusOS")
public class EstatusOSCtrl {
	
	private static final String TPT = "/plantilla";
	private static final String VIEW = "/ope/cat/ctEstatusOSV";
	private static final String REDIRECT = "redirect:/ope/ctEstatusOS/lista";
	/*public static final String VIEW = "plantilla";
	public static final String VIEW = "plantilla";*/
	private String cError;
	
	@Autowired
	private EstatusOSRep estatusOSRep;
	
	@GetMapping("")
	public String redireccion(){
		return REDIRECT;
	}
	
	@GetMapping("/lista")
	public ModelAndView listaAllEstatusOS(@ModelAttribute("Usuario") SessionUsu objUsuario){
		
		ModelAndView mav = new ModelAndView(TPT);
		mav.addObject("titulo", "Estatus Orden de Servicio");
		mav.addObject("contenido", VIEW);
		mav.addObject("listaEstatusOS",
				estatusOSRep.listaEstatusOS(0, "FOR EACH ctEstatusOS WHERE ctEstatusOS.cCveCia = '"+ objUsuario.getCompania() +"' NO-LOCK:"));
		
		if (estatusOSRep.getResultado()) {
			cError = estatusOSRep.getMensaje();
		}

		mav.addObject("error", cError);		
		cError = "";
		
		
		return mav;
	}
	
}
