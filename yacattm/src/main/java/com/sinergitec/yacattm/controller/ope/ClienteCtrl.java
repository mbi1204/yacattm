package com.sinergitec.yacattm.controller.ope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.sinergitec.yacattm.model.ct.Cliente;
import com.sinergitec.yacattm.model.ct.SessionUsu;
import com.sinergitec.yacattm.repos.cat.ClienteRep;

@Controller
@RequestMapping("/ope/ctCliente")
@SessionAttributes("Usuario")


public class ClienteCtrl {
	static final String VIEW = "/ope/cat/ctClienteV";
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
		mav.addObject("Lista" , clienteRep.Lista(0, "FOR EACH ctCliente WHERE ctCliente.cCveCia =  '" + sessionUsu.getCompania() +   "' NO-LOCK:") );
		if (clienteRep.getResultado()){
			this.setError(clienteRep.getMensaje());			
		}
		
		mav.addObject("error", this.getError());	
	
		return mav;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	

	
	
	

}
