package com.sinergitec.yacattm.repos.cat;

import java.util.List;

import com.sinergitec.yacattm.model.ct.Transmision;


/**
 * Autor: Aestrada
 * Fecha: 21 de junio de 2017
 * Descripcion: Interfaz de repositorio 
 * 
 **/

public interface TransmisionRep {
	
	public void agregar(String cUsuario, Transmision nuevos);
	public void eliminar(String cUsuario,Transmision viejos);
	public void actulizar(String cUsuario,Transmision viejos , Transmision nuevos);
	public Transmision getTransmision(int iModo, String cQuery);
	public List<Transmision> listaTransmision(int iModo, String cQuery);
	public Boolean getResultado();
	public String  getMensaje();

}
