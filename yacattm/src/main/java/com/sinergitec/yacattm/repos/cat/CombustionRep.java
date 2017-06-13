package com.sinergitec.yacattm.repos.cat;

import java.util.List;

import com.sinergitec.yacattm.model.ct.Combustion;

public interface CombustionRep {

	public void agregar(String cUsuario, Combustion nuevos);
	public void eliminar(String cUsuario,Combustion viejos);
	public void actulizar(String cUsuario,Combustion viejos , Combustion nuevos);
	public Combustion getCombustion(int iModo, String cQuery);
	public List<Combustion> listaCombustion(int iModo, String cQuery);
	public Boolean getResultado();
	public String  getMensaje();
	
}
