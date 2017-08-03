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
import com.sinergitec.yacattm.model.ct.MarcaAuto;
import com.sinergitec.yacattm.model.ct.SessionUsu;
import com.sinergitec.yacattm.model.ct.Vehiculo;
import com.sinergitec.yacattm.repos.cat.ColorAutoRep;
import com.sinergitec.yacattm.repos.cat.CombustionRep;
import com.sinergitec.yacattm.repos.cat.DireccionAutoRep;
import com.sinergitec.yacattm.repos.cat.EngomadoRep;
import com.sinergitec.yacattm.repos.cat.MarcaAutoRep;
import com.sinergitec.yacattm.repos.cat.ModeloAutoRep;
import com.sinergitec.yacattm.repos.cat.TipoAutoRep;
import com.sinergitec.yacattm.repos.cat.TransmisionRep;
import com.sinergitec.yacattm.repos.cat.UsosVehRep;
import com.sinergitec.yacattm.repos.cat.VehiculoRep;

@Controller
@RequestMapping("/ope/ctVehiculo")
@SessionAttributes("Usuario")


public class VehiculoCtrl {
	
	private String error;
	
	@Autowired
	private VehiculoRep vehiculoRep;
	
	@Autowired
	private UsosVehRep usosVehRep;
	
	@Autowired
	private DireccionAutoRep direccionAutoRep;
	
	@Autowired
	private TransmisionRep transmisionRep;
	
	@Autowired
	private CombustionRep combustionRep;

	@Autowired
	private TipoAutoRep tipoAutoRep;
	
	@Autowired
	private ColorAutoRep colorAutoRep;
	
	@Autowired
	private MarcaAutoRep marcaAutoRep;
	
	@Autowired
	private ModeloAutoRep modeloAutoRep;
	
	
	@Autowired
	private EngomadoRep engomadoRep;
	
	
	static final String FORM     = "/ope/cat/ctVehiculoF";
	static final String REDIRECT = "redirect:/ope/ctCliente/lista";
	
	@GetMapping("nuevo")
	public ModelAndView nuevo (@RequestParam(name = "iCliente", required = true) int iCliente ,
			                   @ModelAttribute("Usuario") SessionUsu objUsuario){		
		
		Vehiculo vehiculo = new Vehiculo();
		
		vehiculo.setCompania(objUsuario.getCompania());
		vehiculo.setCliente(iCliente);
		vehiculo.setVehiculo(0);
		vehiculo.setAnio(0000);
		
		
		ModelAndView mav = new ModelAndView(FORM);
		mav.addObject("vehiculo", vehiculo);		
		/*listas*/
		mav.addObject("listUsosVeh"    , usosVehRep.listaUsosVehiculo(0,"FOR EACH ctUsoAuto WHERE ctUsoAuto.cCveCia = '"           + objUsuario.getCompania() + "' NO-LOCK:"));
		mav.addObject("listDireccion"  , direccionAutoRep.Lista(0, "FOR EACH ctDireccionAuto WHERE ctDireccionAuto.cCveCia = '"    + objUsuario.getCompania() + "' NO-LOCK:"));
		mav.addObject("listTransmision", transmisionRep.listaTransmision(0,"FOR EACH ctTrasmAuto WHERE ctTrasmAuto.cCveCia = '"    + objUsuario.getCompania() + "' NO-LOCK:"));
		mav.addObject("listSisComb"    , combustionRep.listaCombustion(0, "FOR EACH ctSisCombAuto WHERE ctSisCombAuto.cCveCia = '" + objUsuario.getCompania() + "' NO-LOCK:"));
		mav.addObject("listTipo"       , tipoAutoRep.listaTipoAuto(0, "FOR EACH ctTipoAuto WHERE ctTipoAuto.cCveCia = '"           + objUsuario.getCompania() + "' NO-LOCK:"));
		mav.addObject("ListColor"      , colorAutoRep.ListaColorAuto(0, "FOR EACH ctColorAuto WHERE ctColorAuto.cCveCia = '"       + objUsuario.getCompania() + "' NO-LOCK:"));
		mav.addObject("ListMarcaAuto"  , marcaAutoRep.listaMarcasAuto(0, "FOR EACH ctAutoMarca WHERE ctAutoMarca.cCveCia = '"      + objUsuario.getCompania() + "' NO-LOCK:"));
		mav.addObject("ListEngomado"   , engomadoRep.Lista(0, 	"FOR EACH ctEngomado WHERE ctEngomado.cCveCia = '"                 + objUsuario.getCompania() + "' NO-LOCK BY ctEngomado.iOrden:"));
		
	//	mav.addObject("ListModelo"    , modeloAutoRep.listaModeloAuto(0, "FOR EACH ctAutoModelo WHERE ctAutoModelo.cCveCia = '"   + objUsuario.getCompania() + "' NO-LOCK:" ));
		
		/*listas*/
		return mav;
	
	}
	
	
	  
	@GetMapping("/getVehiculos")
	public @ResponseBody String getVehiculos(@RequestParam(name = "iCliente", required = true) int iCliente,
			@ModelAttribute("Usuario") SessionUsu sessionUsu) {

		String resultado = null;

		resultado = new Gson().toJson(vehiculoRep.Lista(0, "FOR EACH ctVehiculo WHERE ctVehiculo.cCveCia = '"
				+ sessionUsu.getCompania() + "' AND ctVehiculo.iCliente = " + iCliente + " NO-LOCK:"));

		if (vehiculoRep.getResultado()) {
			this.setError(vehiculoRep.getMensaje());
			resultado = this.getError();
		}

		return resultado;
	}

	
	@RequestMapping(value = "/actualizar", method = RequestMethod.POST)
	public ModelAndView agregar(@ModelAttribute("Vehiculo") Vehiculo vehiculo,
			@ModelAttribute("Usuario") SessionUsu sessionUsu) {
		
		ModelAndView mav = new ModelAndView();
		
		if (vehiculo.getVehiculo() == 0 ){  /*nuevo*/
			vehiculoRep.agregar(sessionUsu.getUsuario(), vehiculo);
			
			System.out.println(this.vehiculoRep.getMensaje());
		
			

			if (vehiculoRep.getResultado()){
				mav.setViewName(FORM);
				mav.addObject("listUsosVeh"    , usosVehRep.listaUsosVehiculo(0,"FOR EACH ctUsoAuto WHERE ctUsoAuto.cCveCia = '"           + sessionUsu.getCompania() + "' NO-LOCK:"));
				mav.addObject("listDireccion"  , direccionAutoRep.Lista(0, "FOR EACH ctDireccionAuto WHERE ctDireccionAuto.cCveCia = '"    + sessionUsu.getCompania() + "' NO-LOCK:"));
				mav.addObject("listTransmision", transmisionRep.listaTransmision(0,"FOR EACH ctTrasmAuto WHERE ctTrasmAuto.cCveCia = '"    + sessionUsu.getCompania() + "' NO-LOCK:"));
				mav.addObject("listSisComb"    , combustionRep.listaCombustion(0, "FOR EACH ctSisCombAuto WHERE ctSisCombAuto.cCveCia = '" + sessionUsu.getCompania() + "' NO-LOCK:"));
				mav.addObject("listTipo"       , tipoAutoRep.listaTipoAuto(0, "FOR EACH ctTipoAuto WHERE ctTipoAuto.cCveCia = '"           + sessionUsu.getCompania() + "' NO-LOCK:"));
				mav.addObject("ListColor"      , colorAutoRep.ListaColorAuto(0, "FOR EACH ctColorAuto WHERE ctColorAuto.cCveCia = '"       + sessionUsu.getCompania() + "' NO-LOCK:"));
				mav.addObject("ListMarcaAuto"  , marcaAutoRep.listaMarcasAuto(0, "FOR EACH ctAutoMarca WHERE ctAutoMarca.cCveCia = '"      + sessionUsu.getCompania() + "' NO-LOCK:"));
				mav.addObject("ListModeloAuto" , modeloAutoRep.listaModeloAuto(0, "FOR EACH ctAutoModelo WHERE ctAutoModelo.cCveCia = '"      + sessionUsu.getCompania() + 
						                                                         "' AND ctAutoModelo.cMarca = '" + vehiculo.getMarca() + "' NO-LOCK:"));
				mav.addObject("ListEngomado"   , engomadoRep.Lista(0, 	"FOR EACH ctEngomado WHERE ctEngomado.cCveCia = '"                 + sessionUsu.getCompania() + "' NO-LOCK BY ctEngomado.iOrden:"));
				
				mav.addObject("error", this.vehiculoRep.getMensaje());
				mav.addObject("vehiculo", vehiculo);				
				
			}else{
				mav.setViewName(REDIRECT);
			}
			
		}else { /*actualizar*/
			Vehiculo viejo = new Vehiculo();
			vehiculoRep.getVehiculo(0,"FOR EACH ctVehiculo WHERE ctVehiculo.cCveCia  = '" + sessionUsu.getCompania() + "' " + 
                                      "AND ctVehiculo.iVehiculo = "  + vehiculo.getVehiculo() + " NO-LOCK:" );
			if (vehiculoRep.getResultado()){
				mav.setViewName(FORM);
				mav.addObject("error", this.vehiculoRep.getMensaje());
				mav.addObject("vehiculo", vehiculoRep);	
				
			}else{
				vehiculoRep.actualizar(sessionUsu.getUsuario(), viejo, vehiculo);
				if (vehiculoRep.getResultado()){
					mav.setViewName(FORM);
					mav.addObject("error", this.vehiculoRep.getMensaje());
					mav.addObject("vehiculo", vehiculo);				
					
				}else {					
					mav.setViewName(REDIRECT);
				}
				
			}
			
			
		}
		

		
		return mav;
	}
	
	
	@GetMapping("/eliminar")
	public @ResponseBody String eliminar(@RequestParam(name = "iVehiculo", required = true) int iVehiculo,
			@ModelAttribute("Usuario") SessionUsu sessionUsu) {
		
		String Mensaje = null;
		
		Vehiculo viejos = new Vehiculo();
		
		System.out.println("antes de getVehiculo");
		viejos = vehiculoRep.getVehiculo(1 , "FOR EACH ctVehiculo WHERE ctVehiculo.cCveCia = '"  + sessionUsu.getCompania()  + "'"  + 
                                                                   "AND ctVehiculo.iVehiculo = "  + iVehiculo + " NO-LOCK:" );
		System.out.println("depuesdel  de getVehiculo");
		
		System.out.println("Elemento->" +  viejos.toString());
		
		if (this.vehiculoRep.getResultado()) { // error
			
			System.out.println("if");
			Mensaje = this.vehiculoRep.getMensaje();
		} else {
			
			System.out.println("else");
			this.vehiculoRep.eliminar(sessionUsu.getUsuario(), viejos);
			
			if (this.vehiculoRep.getResultado()){
				Mensaje = this.vehiculoRep.getMensaje();				
			}
		}

		if (Mensaje == null || Mensaje == "")
			Mensaje = "success";
		
		System.out.println(Mensaje);
		
		return Mensaje;		
		
	}
	




	public String getError() {
		return error;
	}



	public void setError(String error) {
		this.error = error;
	}
	
	
	

}
