package com.sinergitec.yacattm.repos.cat;

import java.util.List;

import com.sinergitec.yacattm.model.ct.ModeloAuto;


/**
 * Autor: Aestrada
 * Fecha: 23 de junio de 2017
 * Descripcion: Interfaz de repositorio 
 * 
 **/


public interface ModeloAutoRep {
	
	public void agregar(String cUsuario, ModeloAuto nuevos);
	public void eliminar(String cUsuario,ModeloAuto viejos);
	public void actulizar(String cUsuario,ModeloAuto viejos , ModeloAuto nuevos);
	public ModeloAuto getModeloAuto(int iModo, String cQuery);
	public List<ModeloAuto> listaModeloAuto(int iModo, String cQuery);
	public Boolean getResultado();
	public String  getMensaje();
	
}
