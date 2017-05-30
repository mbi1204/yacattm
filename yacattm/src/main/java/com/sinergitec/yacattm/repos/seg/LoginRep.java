package com.sinergitec.yacattm.repos.seg;

import com.sinergitec.yacattm.model.ct.SessionUsu;

public interface LoginRep {
	
	/**
	 * Autor: aestrada
	 * Descripcion: Metodo para el acceso a sistema 
	 * @param cCompania
	 * @param cUsurio
	 * @param cPassword
	 */	
	
	public void getAcceso(String  cCompania, String cUsurio , String cPassword);
	
	public boolean islResultado();
	
	public String getcMensaje();
	
	public SessionUsu getUsuario();	
	
}
