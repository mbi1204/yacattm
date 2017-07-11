package com.sinergitec.yacattm.controller.ope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.sinergitec.yacattm.model.ct.Cliente;
import com.sinergitec.yacattm.model.ct.SessionUsu;
import com.sinergitec.yacattm.repos.cat.ClienteRep;
import com.sinergitec.yacattm.repos.cat.VehiculoRep;

@Controller
@RequestMapping("/ope/ctCliente")
@SessionAttributes("Usuario")


public class ClienteCtrl {
	static final String VIEW     = "/ope/cat/ctClienteV";
	static final String FORM     = "/ope/cat/ctClienteF";
	static final String REDIRECT = "redirect:/ope/ctCliente/lista";
	
	private String error;
	
	@Autowired
	private ClienteRep clienteRep;
	
	@Autowired
	private VehiculoRep vehiculoRep;
	

	@GetMapping("")
	public String redirecion() {
		return REDIRECT;
	}
	
	@GetMapping("/lista")
	public ModelAndView ListAllColorAuto(@ModelAttribute("Usuario") SessionUsu sessionUsu) {	
		
		ModelAndView mav = new ModelAndView(VIEW);
		mav.addObject("cliente" , new Cliente());	
		mav.addObject("ListaCli" , clienteRep.Lista(0, "FOR EACH ctCliente WHERE ctCliente.cCveCia =  '" + sessionUsu.getCompania() +   "' NO-LOCK:") );
		if (clienteRep.getResultado()){
			this.setError(clienteRep.getMensaje());			
		}
		
		
		
		mav.addObject("error", this.getError());	
	
		return mav;
	}
	
	@GetMapping("nuevo")
	public ModelAndView nuevo (@ModelAttribute("Usuario") SessionUsu objUsuario){		
		Cliente cliente = new Cliente();		
		ModelAndView mav = new ModelAndView(FORM);
		mav.addObject("cliente", cliente);		
		return mav;
		
	}

	
				  
	@GetMapping("/getVehiculos")
	public @ResponseBody String getVehiculos(@RequestParam(name = "iCliente", required = true) int iCliente ,
											 @ModelAttribute("Usuario") SessionUsu sessionUsu) {
		
		System.out.println("ENTRO  A /getVehiculos"  + iCliente);
		
		String resultado;
		
		resultado = new Gson().toJson(vehiculoRep.Lista(0, "FOR EACH ctVehiculo WHERE ctVehiculo.cCveCia = '"
				+ sessionUsu.getCompania() + "' AND ctVehiculo.iCliente = " + iCliente  + " NO-LOCK:"));

		if (vehiculoRep.getResultado()){
			this.setError(clienteRep.getMensaje());		
			resultado = this.getError();
		}		
		
		
		System.out.println(resultado);
		return resultado;		
	}
	
    @GetMapping("getVehiculo")
    public ModelAndView getVehiculo(@RequestParam(name = "iVehiculo" , required = true) int iVehiculo , 
    		                        @ModelAttribute("Usuario") SessionUsu sessionUsu ){
    	
    	System.out.println("getVehiculo" + iVehiculo );
    	
    	return null;
    	
    }
	
	

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	

	
	
	

}
