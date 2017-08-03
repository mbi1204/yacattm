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

import com.google.gson.Gson;
import com.sinergitec.yacattm.model.ct.Cliente;
import com.sinergitec.yacattm.model.ct.ColorAuto;
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
		cliente.setCompania(objUsuario.getCompania());
		cliente.setActivo(true);
		cliente.setCliente(0);
		
		ModelAndView mav = new ModelAndView(FORM);
		mav.addObject("cliente", cliente);		
		return mav;
		
	}
	
	@RequestMapping(value = "/actualizar", method = RequestMethod.POST)
	public ModelAndView agregar(@ModelAttribute("cliente") Cliente cliente,
			@ModelAttribute("Usuario") SessionUsu sessionUsu) {
		
		ModelAndView mav = new ModelAndView();
		
		/*nuevo*/		
		
		if (cliente.getCliente() == 0) {
			clienteRep.agregar(sessionUsu.getUsuario(), cliente);
	
			if (clienteRep.getResultado()){
				mav.setViewName(FORM);
				mav.addObject("error", this.clienteRep.getMensaje());
				mav.addObject("cliente", cliente);				
				
			}else{
				mav.setViewName(REDIRECT);
			}
			
		}else{ /*actualiza cliente*/
			
			Cliente viejo = new Cliente();
			viejo = clienteRep.getCliente(0, "FOR EACH ctCliente WHERE ctCliente.cCveCia  = '" + sessionUsu.getCompania() + "' " + 
                                                                  "AND ctCliente.iCliente = "  + cliente.getCliente()  + " NO-LOCK:");
			if (clienteRep.getResultado()){
				mav.setViewName(FORM);
				mav.addObject("error", this.clienteRep.getMensaje());
				mav.addObject("cliente", cliente);	
				
			}else{
				clienteRep.actualizar(sessionUsu.getUsuario(), viejo, cliente);
				if (clienteRep.getResultado()){
					mav.setViewName(FORM);
					mav.addObject("error", this.clienteRep.getMensaje());
					mav.addObject("cliente", cliente);				
					
				}else {					
					mav.setViewName(REDIRECT);
				}
			}
		}		

		
		return mav;

	}
	
	@GetMapping("/eliminar")
	public @ResponseBody String eliminar(@RequestParam(name = "iCliente", required = true) int iCliente,
			@ModelAttribute("Usuario") SessionUsu sessionUsu) {
		
		
		

		String Mensaje = null;
		Cliente viejos = new Cliente();

		viejos = clienteRep.getCliente(0, "FOR EACH ctCliente WHERE ctCliente.cCveCia  = '" + sessionUsu.getCompania()
				+ "' " + "AND ctCliente.iCliente = " + iCliente + " NO-LOCK:");

		if (this.clienteRep.getResultado()) { // error
			Mensaje = this.clienteRep.getMensaje();
		} else {
			this.clienteRep.eliminar(sessionUsu.getUsuario(), viejos);
			if (this.clienteRep.getResultado()){
				Mensaje = this.clienteRep.getMensaje();				
			}
		}

		if (Mensaje == null || Mensaje == "")
			Mensaje = "success";

		return Mensaje;

	}
	
	@GetMapping("/getCliente")
	public ModelAndView agregar(@ModelAttribute("iCliente") int iCliente,
			@ModelAttribute("Usuario") SessionUsu sessionUsu) {		
		
		Cliente cliente = new Cliente() ;
		
		cliente = clienteRep.getCliente(0, "FOR EACH ctCliente WHERE ctCliente.cCveCia  = '" + sessionUsu.getCompania() + "' " + 
				                                                "AND ctCliente.iCliente = "  + iCliente  + " NO-LOCK:");
		
		//System.out.println("eror ->" + clienteRep.getMensaje());
		
		ModelAndView mav = new ModelAndView(FORM);
		mav.addObject("cliente", cliente);		
		return mav;
	}
	
	

	

	
	

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	

	
	
	

}
