package com.sinergitec.yacattm.repos.cat;

import java.util.List;

import com.sinergitec.yacattm.model.ct.TipoAuto;

/**
 * Autor: Aestrada
 * Fecha: 14 de junio de 2017
 * Descripcion: Interfaz de repositorio 
 * 
 **/

public interface TipoAutoRep {
	
	public void agregar(String cUsuario, TipoAuto nuevos);
	public void eliminar(String cUsuario,TipoAuto viejos);
	public void actulizar(String cUsuario,TipoAuto viejos , TipoAuto nuevos);
	public TipoAuto getTipoAuto(int iModo, String cQuery);
	public List<TipoAuto> listaTipoAuto(int iModo, String cQuery);
	public Boolean getResultado();
	public String  getMensaje();

}
