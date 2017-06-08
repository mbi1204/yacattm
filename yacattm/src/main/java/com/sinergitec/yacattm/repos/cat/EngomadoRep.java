package com.sinergitec.yacattm.repos.cat;

import java.util.List;

import com.sinergitec.yacattm.model.ct.Engomado;

public interface EngomadoRep {
	public List<Engomado> Lista (int iModo, String cQuery);
	public void agregar(String cUsuario , Engomado nuevos);
	public void actulizar(String cUsuario,Engomado viejos , Engomado nuevos);
	public Engomado getEngomado(int iModo, String cQuery);
	public Boolean getResultado();
	public String  getMensaje();

}
