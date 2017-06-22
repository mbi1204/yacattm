package com.sinergitec.yacattm.repos.cat;

import java.util.List;

import com.sinergitec.yacattm.model.ct.MarcaAuto;


/**
 * Autor: Aestrada
 * Fecha: 22 de junio de 2017
 * Descripcion: Interfaz de repositorio 
 * 
 **/

public interface MarcaRep {

	public void agregar(String cUsuario, MarcaAuto nuevos);
	public void eliminar(String cUsuario,MarcaAuto viejos);
	public void actulizar(String cUsuario,MarcaAuto viejos , MarcaAuto nuevos);
	public MarcaAuto getMarcaAuto(int iModo, String cQuery);
	public List<MarcaAuto> listaMarcasAuto(int iModo, String cQuery);
	public Boolean getResultado();
	public String  getMensaje();
	
}
