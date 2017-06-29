package com.sinergitec.yacattm.repos.cat;

import java.util.List;

import com.sinergitec.yacattm.model.ct.DireccionAuto;
import com.sinergitec.yacattm.model.ct.EstatusOS;

/**
 * 
 * @author mendoza
 *
 */
public interface DireccionAutoRep {
	
	public List<DireccionAuto> Lista (int iModo, String cQuery);
	public void agregar(String cUsuario , DireccionAuto nuevos);
	public void actulizar(String cUsuario,DireccionAuto viejos , DireccionAuto nuevos);
	public void eliminar(String cUsuario,DireccionAuto viejos);
	public DireccionAuto getDireccionAuto(int iModo, String cQuery);
	public Boolean getResultado();
	public String  getMensaje();

}
