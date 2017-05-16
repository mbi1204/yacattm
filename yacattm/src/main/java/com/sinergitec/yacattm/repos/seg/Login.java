package com.sinergitec.yacattm.repos.seg;

public interface Login {
	
	/**
	 * Autor: aestrada
	 * Descripcion: Metodo para el acceso a sistema 
	 * @param cCompania
	 * @param cUsurio
	 * @param cPassword
	 */	
	
	public void getAcceso(String  cCompania, String cUsurio , String cPassword);
	
	public boolean islResultado();
	
	public void setlResultado(boolean lResultado);
	
	public String getcMensaje();
	
	public void setcMensaje(String cMensaje);
	
}
