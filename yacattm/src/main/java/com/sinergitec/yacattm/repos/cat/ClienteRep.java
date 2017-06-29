package com.sinergitec.yacattm.repos.cat;


import java.util.List;

import com.sinergitec.yacattm.model.ct.Cliente;

/**
 * 
 * @author mendoza
 *
 */
public interface ClienteRep {
	public void agregar(String cUsuario , Cliente nuevos );
	public void eliminar(String cUsuario, Cliente viejos);
	public void actualizar (String cUsuario, Cliente viejos, Cliente nuevos);
	public Cliente getCliente (int iModo, String cQuery);
	public List<Cliente> Lista (int iModo, String cQuery);
	public Boolean getResultado();
	public String getMensaje();
	
	

}
