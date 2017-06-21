package com.sinergitec.yacattm.controller.ope;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * Autor: Aestrada Fecha: 08 de junio de 2017 Descripcion: Se encarga de las
 * peticiones para el catalogo de usos de vehiculo
 * 
 **/

@Controller
@SessionAttributes("Usuario")
@RequestMapping("/ope/ctUsosVehiculo")
public class UsosVehCtrl {
	
	private static final String VIEW = "/ope/cat/ctUsosVehV";
	private static final String FORM_ADD = "/ope/cat/ctUsosVehVAddF";
	private static final String FORM_UPD = "/ope/cat/ctUsosVehVUpdF";
	private static final String REDIRECT = "redirect:/ope/ctUsosVehV/lista";

}
