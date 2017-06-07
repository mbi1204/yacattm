package com.sinergitec.yacattm.repos.seg;

import java.util.List;

import com.sinergitec.yacattm.model.ct.EstatusOS;

/**
 * Autor: Aestrada
 * Fecha: 07 de junio de 2017
 * Descripcion: Interfaz de repositorio 
 * 
 **/

public interface EstatusOSRep {
	
	public void agregar(String cUsuario, EstatusOS nuevos);
	public void eliminar(String cUsuario,EstatusOS viejos);
	public void actulizar(String cUsuario,EstatusOS viejos , EstatusOS nuevos);
	public EstatusOS getColorAuto(int iModo, String cQuery);
	public List<EstatusOS> ListaColorAuto(int iModo, String cQuery);
	public Boolean getResultado();
	public String  getMensaje();

}
