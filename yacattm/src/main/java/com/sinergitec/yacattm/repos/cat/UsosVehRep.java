package com.sinergitec.yacattm.repos.cat;

import java.util.List;

import com.sinergitec.yacattm.model.ct.UsoVeh;

/**
 * Autor: Aestrada
 * Fecha: 21 de junio de 2017
 * Descripcion: Interfaz de repositorio 
 * 
 **/

public interface UsosVehRep {
	
	public void agregar(String cUsuario, UsoVeh nuevos);
	public void eliminar(String cUsuario,UsoVeh viejos);
	public void actulizar(String cUsuario,UsoVeh viejos , UsoVeh nuevos);
	public UsoVeh getUsoVehiculo(int iModo, String cQuery);
	public List<UsoVeh> listaUsosVehiculo(int iModo, String cQuery);
	public Boolean getResultado();
	public String  getMensaje();

}
