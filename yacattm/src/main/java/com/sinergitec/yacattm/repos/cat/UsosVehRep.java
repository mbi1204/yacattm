package com.sinergitec.yacattm.repos.cat;

import java.util.List;

import com.sinergitec.yacattm.model.ct.UsosVeh;

/**
 * Autor: Aestrada
 * Fecha: 21 de junio de 2017
 * Descripcion: Interfaz de repositorio 
 * 
 **/

public interface UsosVehRep {
	
	public void agregar(String cUsuario, UsosVeh nuevos);
	public void eliminar(String cUsuario,UsosVeh viejos);
	public void actulizar(String cUsuario,UsosVeh viejos , UsosVeh nuevos);
	public UsosVeh getUsoVehiculo(int iModo, String cQuery);
	public List<UsosVeh> listaUsosVehiculo(int iModo, String cQuery);
	public Boolean getResultado();
	public String  getMensaje();

}
